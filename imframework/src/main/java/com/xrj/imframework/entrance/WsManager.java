package com.xrj.imframework.entrance;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.BuildConfig;
import android.text.TextUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.xrj.imframework.bean.AuthResponseBean;
import com.xrj.imframework.bean.ChatSendBean;
import com.xrj.imframework.bean.GroupSendBean;
import com.xrj.imframework.bean.UserGroupResponseBean;
import com.xrj.imframework.bean.WsStatus;
import com.xrj.imframework.inter.DataCorrectCallBack;
import com.xrj.imframework.req.CallbackDataWrapper;
import com.xrj.imframework.req.CallbackWrapper;
import com.xrj.imframework.utils.L;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xrj on 2018/5/16.
 * 初始化主入口
 */
public class WsManager {
    private static WsManager mInstance;

    private final String TAG = this.getClass().getSimpleName();
    private DataCorrectCallBack dataCorrectCallBack;
    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String DEF_TEST_URL = "ws://192.168.101.218:8060/";//测试服默认地址
    private static final String DEF_RELEASE_URL = "ws://118.25.40.163:8088";//正式服默认地址
    private static final String DEF_URL = BuildConfig.DEBUG ? DEF_TEST_URL : DEF_RELEASE_URL;
    private String url;
    private final int SUCCESS_HANDLE = 0x01;
    private final int ERROR_HANDLE = 0x02;
    private static final long HEARTBEAT_INTERVAL = 60000;//心跳间隔
    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;
    private Context mcontext;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Map<Long, CallbackWrapper> callbacks = new HashMap<>();
    private String groupnum;
    private static final int REQUEST_TIMEOUT = 10000;//请求超时时间
    private AtomicLong seqId = new AtomicLong(SystemClock.uptimeMillis());//每个请求的唯一标识

    private String token="eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0bmFtZSIsInVzZXJJZCI6IjIiLCJuYW1lIjoi5L-u5pS55pi156ewMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExIiwiZXhwIjoxNTM5OTUxMzc2fQ.RPiLlSerWPPSBfp0PYC9kz8vidYhoEAn7dC9Fc0GEMwoGO2AAk0tWZdT3sYzlPXJau4_HSGft4f4ywgEMLp9KxAvwRozg5KIZ606r4psUQfP-O4zOiS4HwKHpBXvKrn_r0q_sQXnEdw7BNJXt7w66UCb-r4gc-KVtycq-RHdfkI";
    //private String group_id;

    private WsManager() {
    }

    public Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_HANDLE:
                    CallbackDataWrapper successWrapper = (CallbackDataWrapper) msg.obj;
                    successWrapper.getCallback().onSuccess(successWrapper.getData());
                    break;
                case ERROR_HANDLE:
                    CallbackDataWrapper errorWrapper = (CallbackDataWrapper) msg.obj;
                    errorWrapper.getCallback().onFail((String) errorWrapper.getData());
                    break;
            }
        }
    };


    public static WsManager getInstance() {
        if (mInstance == null) {
            synchronized (WsManager.class) {
                if (mInstance == null) {
                    mInstance = new WsManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context mcontext,String ipstr,String username,String password) {
        this.mcontext = mcontext;
        //this.group_id=password;
        initconn(ipstr,username,password);
    }

    private void initconn(String ipstr,String username,String password) {
        try {
            /**
             * configUrl其实是缓存在本地的连接地址
             * 这个缓存本地连接地址是app启动的时候通过http请求去服务端获取的,
             * 每次app启动的时候会拿当前时间与缓存时间比较,超过6小时就再次去服务端获取新的连接地址更新本地缓存
             */
            //String configUrl = "ws://118.25.40.163:8088";
            String configUrl = "ws://192.168.1.78:8833?username="+username+"&password="+password;
            url = TextUtils.isEmpty(ipstr) ? DEF_URL : ipstr;
            L.i(url);
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener = new WsListener())//添加回调监听
                    .addHeader("opera-token", "token")
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            //Logger.t(TAG).d("第一次连接");
            L.i("第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
            L.i("第一次连接" + e.toString());
        }
    }

    private void doAuth() {
        L.i("是否执行授权登录");
       String authCmd = "{\"cmd\":3,\"token\":\""+token+"\"}";
       ws.sendText(authCmd);
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            L.i("onTextMessage:" + text);
            JSONObject jsonObject = new JSONObject(text);
            int content = jsonObject.getInt("command");
            switch (content) {
                case 11://消息接收
                    Intent ints = new Intent();
                    ints.putExtra("data", text);
                    ints.setAction("com.xrjframework.demo.websocket.data");
                    mcontext.sendBroadcast(ints);
                    break;
                case 10://退出群组
                    Intent intgroup = new Intent();
                    intgroup.putExtra("data", text);
                    intgroup.setAction("com.xrjframework.demo.websocket.quitgroup");
                    mcontext.sendBroadcast(intgroup);
                    break;
                case 9://加入群组
                    EnterGroup(text);
                    break;
                case 13://心跳检测

                    break;
                case 4://鉴权
                    AuthCmds(text);
                    break;
                case 18://获取在线用户
                    Intent intuser = new Intent();
                    intuser.putExtra("data", text);
                    intuser.setAction("com.xrjframework.demo.websocket.onlineuser");
                    mcontext.sendBroadcast(intuser);
                    break;
//                case 6://申请加入群组
//                    ApplyGroup();
//                    break;
            }
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Intent ints = new Intent();
            ints.setAction("com.xrjframework.demo.websocket.onconnected");
            mcontext.sendBroadcast(ints);
            L.i("连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancelReconnect();//连接成功的时候取消重连,初始化连接次数
            doAuth();
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Intent ints = new Intent();
            ints.setAction("com.xrjframework.demo.websocket.onconnecterror");
            mcontext.sendBroadcast(ints);
            L.i("连接错误" + exception.toString());
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接错误的时候调用重连方法
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            //Logger.t(TAG).d("断开连接");
            L.i("断开连接");
            Intent ints = new Intent();
            ints.setAction("com.xrjframework.demo.websocket.ondisconnected");
            mcontext.sendBroadcast(ints);
            setStatus(WsStatus.CONNECT_FAIL);
            //reconnect();//连接断开的时候调用重连方法
        }
    }

    private void setStatus(WsStatus status) {
        this.mStatus = status;
    }

    private WsStatus getStatus() {
        return mStatus;
    }

    private int reconnectCount = 0;//连次数
    private long minInterval = 3000;//重连最小时间间隔
    private long maxInterval = 60000;//重连最大时间间隔

    private void startHeartbeat() {
        mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
    }


    private void cancelHeartbeat() {
        heartbeatFailCount = 0;
        mHandler.removeCallbacks(heartbeatTask);
    }


    int heartbeatFailCount = 0;
    private Runnable heartbeatTask = new Runnable() {
        @Override
        public void run() {
            heartbeatFailCount+=1;
            String heartbeatCmd = "{\"cmd\":13,\"hbbyte\":\"-1\"}";
            ws.sendText(heartbeatCmd);
            mHandler.postDelayed(this, HEARTBEAT_INTERVAL);
        }
    };

    public void reconnect() {
        if (!isNetConnect()) {
            reconnectCount = 0;
            L.i("重连失败网络不可用");
            return;
        }

        //这里其实应该还有个用户是否登录了的判断 因为当连接成功后我们需要发送用户信息到服务端进行校验
        //由于我们这里是个demo所以省略了
        if (ws != null &&
                !ws.isOpen() &&//当前连接断开了
                getStatus() != WsStatus.CONNECTING) {//不是正在重连状态

            reconnectCount++;
            setStatus(WsStatus.CONNECTING);
            cancelHeartbeat();//取消心跳

            long reconnectTime = minInterval;
            if (reconnectCount > 3) {
                url = DEF_URL;
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            }
            L.i("准备开始第%d次重连,重连间隔%d -- url:%s", reconnectCount + reconnectTime + url);
            mHandler.postDelayed(mReconnectTask, reconnectTime);
        }
    }


    private Runnable mReconnectTask = new Runnable() {

        @Override
        public void run() {
            try {
                ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                        .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                        .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                        .addListener(mListener = new WsListener())//添加回调监听
                        .connectAsynchronously();//异步连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    private void cancelReconnect() {
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }


    private boolean isNetConnect() {
        ConnectivityManager connectivity = (ConnectivityManager) mcontext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }




    /**
     * 发送群组信息
     *
     * @param content
     * @param username
     * @param createTime
     * @param msgtype  类型  1.消息   2.礼物   3.群体禁言
     */
    public void sendGrouptext(String content, String username, String createTime,String msgtype,String imgurl,String leave,String nick) {
        // String str="{\"command\":11,\"data\":{\"chatType\":2,\"cmd\":11,\"content\":\"hahahahahaha\",\"createTime\":1527133207581,\"from\":\"1527126464114\",\"id\":\"ee7b9c37dcb548cd826ddb0a1ce5cf28\",\"msgType\":0,\"to\":\"xirenjie\"}}";
        //String str="{\"command\":11,\"data\":{\"chatType\":1,\"cmd\":11,\"content\":\"hahahah\",\"createTime\":1527126807475,\"from\":\"xirenjie\",\"group_id\":\"100\",\"id\":\"70393dc01fec4996aabb61496218s37d\",\"msgType\":0}}";
        //String str = "{\"from\": \"" + username + "\",\"createTime\":" + createTime + ",\"cmd\":11,\"group_id\":\""+group_id+"\",\"chatType\":\"1\",\"msgType\":\"0\",\"content\": \"" + content + "\"}";
        GroupSendBean groupBean = new GroupSendBean();
        groupBean.setChatType("1");
        groupBean.setCmd(11);
        groupBean.setContent(content);
        groupBean.setFrom(username);
        groupBean.setCreateTime(createTime);
        groupBean.setGroup_id(groupnum);
        groupBean.setMsgType(msgtype);
        groupBean.setImgUrl(imgurl);
        groupBean.setLeave(leave);
        groupBean.setNickname(nick);
        Gson gson=new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        ws.sendText(gson.toJson(groupBean));
    }

    /**
     * 私聊
     * @param content
     * @param fromname
     * @param toname
     * @param createTime
     * @param msgtype   类型  4.消息  5.禁言  6.踢出
     */
    public void sendChatText(String content, String fromname, String toname,String createTime,String msgtype,String imgurl,String leave,String nick)
    {
        //{\"from\": \""+username+"\",\"to\": \""+toId+"\",\"cmd\":11,\"createTime\":"+createTime+",\"chatType\":\"2\",\"msgType\": \"0\",\"content\": \""+content+"\"}
        ChatSendBean chatBean=new ChatSendBean();
        chatBean.setMsgType(msgtype);
        chatBean.setChatType("2");
        chatBean.setFrom(fromname);
        chatBean.setTo(toname);
        chatBean.setContent(content);
        chatBean.setCreateTime(createTime);
        chatBean.setCmd(11);
        chatBean.setImgUrl(imgurl);
        chatBean.setLeave(leave);
        chatBean.setNickname(nick);
        Gson gson=new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        ws.sendText(gson.toJson(chatBean));
    }

    /**
     *鉴权结果
     * @param text
     */
    private void AuthCmds(String text)
    {
        AuthResponseBean authResponseBean=new Gson().fromJson(text, AuthResponseBean.class);
        if(authResponseBean.getCode()==10009)
        {
            L.i(authResponseBean.getMsg());
            //ApplyGroup();
            startHeartbeat();
        }
    }

    /**
     * 加入群组通知
     * @param content
     */
    private void EnterGroup(String content)
    {
        UserGroupResponseBean userGroupResponseBean=new Gson().fromJson(content, UserGroupResponseBean.class);
        groupnum=userGroupResponseBean.getData().getGroup();
        Intent intgroup = new Intent();
        intgroup.putExtra("data", content);
        intgroup.setAction("com.xrjframework.demo.websocket.joingroup");
        mcontext.sendBroadcast(intgroup);
    }

    /**
     * 申请入群
     */
    public void ApplyGroup(String group_id)
    {
        String str= "{\"cmd\":7,\"group_id\":\""+group_id+"\"}";
        Gson gson=new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        ws.sendText(str);
    }

    /**
     * 获取个人用户信息
     */
    public void sendUserMessage()
    {
        String str = "{\"cmd\":17,\"userid\":\"33\"}";
        ws.sendText(str);
    }

    /**
     * 获取在线用户
     */
    public void OnlineSend() {
        String str = "{\"cmd\":17,\"type\":\"1\"}";
        Gson gson = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        this.ws.sendText(str);
    }

    /**
     * 指定用户退出群组
     */
    public void SpecifyExit(String userid)
    {
        String str= "{\"cmd\":14,\"userid\":\""+userid+"\"}";
        ws.sendText(str);
    }

    /**
     * 取消心跳
     */
    private void HeartCmds()
    {
        cancelHeartbeat();
    }

    public void disconnect() {
        if (ws != null)
            ws.disconnect();
            HeartCmds();
    }




}

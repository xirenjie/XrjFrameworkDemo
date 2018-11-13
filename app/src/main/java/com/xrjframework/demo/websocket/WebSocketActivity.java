package com.xrjframework.demo.websocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrj.imframework.entrance.WsManager;
import com.xrjframework.demo.R;

import butterknife.BindView;

/**
 * Created by 袭人杰 on 2018/5/24.
 */
public class WebSocketActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.websocket_edit)
    EditText websocket_edit;

    @BindView(R.id.websocket_button)
    Button websocket_button;

    String tex;
    @Override
    protected int initLayoutId() {
        return R.layout.websocket_layout;
    }

    @Override
    protected void initView() {
        filter();
        WsManager.getInstance().init(getApplication(),"192.168.1.78:8866","xirenjie","123");
        websocket_button.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }


    private void filter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xrjframework.demo.websocket.data");
        filter.addAction("com.xrjframework.demo.websocket.ondisconnected");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(myReceiver, filter);
    }
    /*
     * 广播接收
     */
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String aciton = intent.getAction();

            if (aciton.equals("com.xrjframework.demo.websocket.data")) {
                String mass=intent.getStringExtra("data");
                Toast.makeText(WebSocketActivity.this,mass,Toast.LENGTH_SHORT).show();
            }
            if(aciton.equals("com.xrjframework.demo.websocket.ondisconnected"))
            {
                Toast.makeText(WebSocketActivity.this,"连接断开,正在重连",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.websocket_button:
                tex=websocket_edit.getText().toString();
                WsManager.getInstance().sendGrouptext(tex, "xirenjie", getTime(),1+"","","","张三");
                break;
        }
    }

    public String getTime(){
        long time=System.currentTimeMillis();//获取系统时间的10位的时间戳
        String  str=String.valueOf(time);
        return str;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WsManager.getInstance().disconnect();
    }
}

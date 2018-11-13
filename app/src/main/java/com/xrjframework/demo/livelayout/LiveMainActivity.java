package com.xrjframework.demo.livelayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xrj.imframework.entrance.WsManager;
import com.xrjframework.demo.R;

/**
 * 该界面是LiveViewFragment与交互界面MainDialogFragment的容器
 *
 * Success is the sum of small efforts, repeated day in and day out.
 * 成功就是日复一日那一点点小小努力的积累。
 * AndroidGroup：158423375
 * Author：Johnny
 * AuthorQQ：956595454
 * AuthorWX：Qiang_it
 * AuthorPhone：nothing
 * Created by 2016/9/22.
 */
public class LiveMainActivity extends FragmentActivity {
    private String token="eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxODc2NDExNzcwNiIsInVzZXJJZCI6IjMzIiwibmFtZSI6IuiireWkp-WkpyIsImV4cCI6MTU0MjI0Njk3Nn0.cnw6hp3-sf96v5s5pROQg4iPcVLm7hW13DFAWz9JbyP3ssWsZP3233kH7RaJVKEXjKqFYEkX0E6C1Cb7TIZdLVBQE-9CiPXKrIwcKFM41j0xoS7E-yWlFp83rgEIBB88oErMtmg7qpm23fof0zvXXR1405tqPq-XB92ZyFkPYdY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_main);
        String configUrl = "ws://192.168.1.78:8855?token="+token;
        WsManager.getInstance().init(this, configUrl,"xirenjie", "100");
        /*这里可以看到的就是我们将初始化直播的Fragment添加到了这个页面作为填充
        * 并且将MainDialogFragment显示在该页面的顶部已达到各种不同交互的需求*/
        LiveViewFragment liveViewFragment = new LiveViewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flmain, liveViewFragment).commit();
        new MainDialogFragment().show(getSupportFragmentManager(),"MainDialogFragment");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        WsManager.getInstance().disconnect();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WsManager.getInstance().disconnect();
        finish();
    }
}
package com.xrjframework.demo.dyna;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

import butterknife.BindView;

/**
 * Created by xrj on 2018/5/16.
 * 动态显示隐藏状态栏
 *
 */
public class DynaStatusActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.mlayout)
    RelativeLayout mlayout;
    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;
    @BindView(R.id.btn_3)
    Button btn_3;
    @BindView(R.id.btn_4)
    Button btn_4;
    @BindView(R.id.btn_5)
    Button btn_5;
    @BindView(R.id.btn_6)
    Button btn_6;
    @Override
    protected int initLayoutId() {
        return R.layout.dyna_status_layout;
    }

    @Override
    protected void initView() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_1:
                //显示状态栏，Activity不全屏显示(恢复到有状态的正常情况)
                mlayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                break;
            case R.id.btn_2:
                //隐藏状态栏，同时Activity会伸展全屏显示
                mlayout.setSystemUiVisibility(View.INVISIBLE);
                break;
            case R.id.btn_3:
                //Activity全屏显示，且状态栏被隐藏覆盖掉。
                mlayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                break;
            case R.id.btn_4:
                //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
                mlayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                break;
            case R.id.btn_5:
                //隐藏虚拟按键(导航栏)
                mlayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                break;
            case R.id.btn_6:
                //状态栏显示处于低能显示状态(low profile模式)，状态栏上一些图标显示会被隐藏。
                mlayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                break;
        }
    }
}

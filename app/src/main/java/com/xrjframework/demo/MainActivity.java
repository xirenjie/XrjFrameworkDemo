package com.xrjframework.demo;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xrj.framework.Util.SkipActivity;
import com.xrj.framework.nice.activity.BaseActivity;
import com.xrj.imframework.entrance.WsManager;
import com.xrjframework.demo.crash.CrashActivity;
import com.xrjframework.demo.customdirectionguide.CustomWelcome;
import com.xrjframework.demo.dyna.DynaStatusActivity;
import com.xrjframework.demo.easypermission.EasypermissionActivity;
import com.xrjframework.demo.livelayout.LiveMainActivity;
import com.xrjframework.demo.mvp.activity.XrjMvpActivity;
import com.xrjframework.demo.nice.activity.NiceActivity;
import com.xrjframework.demo.pagetransition.PageTransitionActivity;
import com.xrjframework.demo.palette.PaletteActivity;
import com.xrjframework.demo.video.VideoActivity;
import com.xrjframework.demo.websocket.WebSocketActivity;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class MainActivity extends BaseActivity {

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

        TestClassFormat();
    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    public void init()
    {
        /**
         * 卡片拖拽,动画跟随
         */
        ImageView pagetransition_img= (ImageView) findViewById(R.id.pagetransition_img);
        pagetransition_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, PageTransitionActivity.class);
                SkipActivity.toActivity(MainActivity.this,WebSocketActivity.class);
            }
        });

        /**
         * 上下滑动的引导页
         */
        ImageView custom_img= (ImageView) findViewById(R.id.custom_img);
        custom_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, CustomWelcome.class);
            }
        });

        /**
         * 直播页面
         */
        ImageView livelayout_img= (ImageView) findViewById(R.id.livelayout_img);
        livelayout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, LiveMainActivity.class);
            }
        });

        /**
         * mvp模式架构
         */
        ImageView mvp_img= (ImageView) findViewById(R.id.mvp_img);
        mvp_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, XrjMvpActivity.class);
            }
        });

        /**
         * nice框架
         */
        ImageView nice_img= (ImageView) findViewById(R.id.nice_img);
        nice_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, NiceActivity.class);
            }
        });
        /**
         * 权限申请
         */
        ImageView permission_img= (ImageView) findViewById(R.id.permission_img);
        permission_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, EasypermissionActivity.class);
            }
        });
        /**
         * 异常拦截
         */
        ImageView crash_img= (ImageView) findViewById(R.id.crash_img);
        crash_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, CrashActivity.class);
            }
        });
        /**
         * 选择器
         */
        TextView picker_choose = (TextView) findViewById(R.id.picker_choose);
        picker_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionPicker(v);
            }
        });
        /**
         * 视频
         */
        ImageView video_img= (ImageView) findViewById(R.id.video_img);
        video_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, VideoActivity.class);
            }
        });
        /**
         * palette取色器,用于同步整个页面颜色差异
         */
        ImageView palette_text_img= (ImageView) findViewById(R.id.palette_text_img);
        palette_text_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, PaletteActivity.class);
            }
        });

        ImageView dynastatus_img= (ImageView) findViewById(R.id.dynastatus_img);
        dynastatus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkipActivity.toActivity(MainActivity.this, DynaStatusActivity.class);
            }
        });
    }


    public void onOptionPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "第一项", "第二项", "这是一个很长很长很长很长很长很长很长很长很长的很长很长的很长很长的项"
        });
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        //picker.setShadowColor(Color.RED, 40);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                //showToast("index=" + index + ", item=" + item);
                Toast.makeText(MainActivity.this, "index=" + index + ", item=" + item, Toast.LENGTH_SHORT).show();
            }
        });
        picker.show();
    }

    private boolean isBackPressed;
    @Override
    public void onBackPressed() {
            if (isBackPressed) {
                super.onBackPressed();
                return;
            }
            isBackPressed = true;
            Toast.makeText(MainActivity.this,"再按就要说再见了",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = false;
                }
            }, 2000);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WsManager.getInstance().disconnect();
    }


    public void TestClassFormat()//unhandler execption:java.lang.classnotfoundexecption
    {
        try
        {
         Class.forName("com.xrjframework.demo.ClassFormatTest").newInstance();
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (ClassNotFoundException e) {

        }
    }




}

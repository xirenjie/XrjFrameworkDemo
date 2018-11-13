package com.xrjframework.demo.crash;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

/**
 * Created by 袭人杰 on 2017/10/13.
 */
public class CrashActivity extends BaseActivity {
    private TextView textView;

    @Override
    protected int initLayoutId() {
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.e("AndroidRuntime", "--->uncaughtException:" + t + "<---", e);
                uncaughtExceptionHandler.uncaughtException(t,e);//若不把异常交给默认的异常处理器处理会导致ANR，交给了就会导致crash
            }
        });
        return R.layout.crash_layout;
    }

    @Override
    protected void initView() {

        textView = (TextView) findViewById(R.id.crash_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CrashActivity.this, "异常拦截", Toast.LENGTH_SHORT).show();
                throw new RuntimeException("click exception...");
            }
        });
        throw new RuntimeException("click exception...");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }


}

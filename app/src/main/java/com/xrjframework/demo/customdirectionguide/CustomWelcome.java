package com.xrjframework.demo.customdirectionguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xrj.framework.customdirectionguide.VerticalLinearLayout;
import com.xrjframework.demo.R;

public class CustomWelcome extends Activity
{
    private VerticalLinearLayout mMianLayout;
    private Button jump,over;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
        jump = (Button) findViewById(R.id.jump);
        over = (Button) findViewById(R.id.over);

        jump.setOnClickListener(new View.OnClickListener() {//跳过
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomWelcome.this,CustomActivity.class));
                finish();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {//开始使用
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomWelcome.this,CustomActivity.class));
                finish();
            }
        });

        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {//滑动监听
            @Override
            public void onPageChange(int currentPage) {
                mMianLayout.getChildAt(currentPage);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

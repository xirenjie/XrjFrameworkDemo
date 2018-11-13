package com.xrjframework.demo.nice.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

import butterknife.BindView;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */

public class OtherActivity extends BaseActivity {
    @BindView(R.id.toolbar_other)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.other_cardview)
    CardView other_cardview;

    @Override
    protected int initLayoutId() {

        return R.layout.other_layout;
    }

    @Override
    protected void initView() {
        toolbar= (Toolbar) findViewById(R.id.toolbar_other);
        toolbar.setTitle(R.string.other);
        CharSequence charSequence = "哈哈".toString();
        collapsing_toolbar.setTitle(charSequence);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SnackBarUtil.show(view, "这是在干啥");
                //Toast.makeText(mContext,"hahaha",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        DragDismiss();
    }

    @Override
    protected void initData() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_other:
                        Toast.makeText(OtherActivity.this, "这是在干啥", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    /**
     * cardview支持滑动删除
     */
    public void DragDismiss() {
        final SwipeDismissBehavior<CardView> swipe
                = new SwipeDismissBehavior();
        swipe.setSwipeDirection(
                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipe.setListener(
                new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        Toast.makeText(OtherActivity.this,
                                "Card swiped !!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                    }
                });

        CoordinatorLayout.LayoutParams coordinatorParams =
                (CoordinatorLayout.LayoutParams) other_cardview.getLayoutParams();

        coordinatorParams.setBehavior(swipe);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

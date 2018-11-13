package com.xrjframework.demo.nice.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.xrj.framework.Util.ImageLoader;
import com.xrj.framework.Util.ResourceUtil;
import com.xrj.framework.Util.ShareUtil;
import com.xrj.framework.Util.SnackBarUtil;
import com.xrj.framework.nice.activity.BaseActivity;
import com.xrj.framework.nice.fragment.BaseFragment;
import com.xrjframework.demo.R;
import com.xrjframework.demo.nice.fragment.AboutFragment;
import com.xrjframework.demo.nice.fragment.TypeFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by a on 2017/6/22.
 *
 * @auther XRJ
 */
public class NiceActivity extends BaseActivity {
    private String mCurrentType;
    private Map<String, BaseFragment> mTypeFragments;

    @BindView(R.id.main_nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;


    @Override
    protected int initLayoutId() {
        return R.layout.nice_layout;
    }

    @Override
    protected void initView() {
        initStatusBar();//状态栏设置

        initDrawer();

        initNavigationView();

        doReplace(ResourceUtil.resToStr(mContext, R.string.gank));
    }

    @Override
    protected void initData() {
        mTypeFragments = new HashMap<>();
    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    //设置分享按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_share:
                ShareUtil.share(mContext, ResourceUtil.resToStr(mContext, R.string.main_share_content));
                break;
            case R.id.create_new:
                SnackBarUtil.show(mDrawerLayout, "这是新建文件夹");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawer() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        //设置左上角显示三道横线
        toggle.syncState();
        mToolbar.setTitle(R.string.app_name);
    }

    private void initNavigationView() {
        ImageView icon = (ImageView) mNavView.getHeaderView(0).findViewById(R.id.nav_head_icon);
        ImageLoader.loadCircle(mContext, R.drawable.icon, icon);
        TextView name = (TextView) mNavView.getHeaderView(0).findViewById(R.id.nav_head_name);
        name.setText(R.string.app_name);
        mNavView.setCheckedItem(R.id.nav_gank);//设置默认选中
        //设置NavigationView对应menu item的点击事情
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_gank:
                        doReplace(ResourceUtil.resToStr(mContext, R.string.gank));
                        break;
                    case R.id.nav_girl:
                        doReplace(ResourceUtil.resToStr(mContext, R.string.girl));
                        break;
                    case R.id.nav_set:
                        //openSet();
                        break;
                    case R.id.nav_about:
                        doReplace(ResourceUtil.resToStr(mContext, R.string.about));
                        break;
                    case R.id.nav_other:
                        openOther();
                        break;
                }
                //隐藏NavigationView
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void doReplace(String type) {//替换
        if (!type.equals(mCurrentType)) {
            if (ResourceUtil.resToStr(NiceActivity.this, R.string.about).equals(type))
            {
                AboutFragment fragment = new AboutFragment();
                replaceFragment(fragment,type, mCurrentType);
            }else
            {
                replaceFragment(TypeFragment.newInstance(type), type, mCurrentType);
            }
            mCurrentType = type;
        }
    }

    private void replaceFragment(BaseFragment fragment, String tag, String lastTag) {
        if (mTypeFragments.get(tag) == null) {
            mTypeFragments.put(tag, fragment);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment_container, fragment, tag)
                    .commit();
        }

        if (mTypeFragments.get(lastTag) != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mTypeFragments.get(lastTag))
                    .show(mTypeFragments.get(tag))
                    .commit();
        }
    }

    private void openOther()
    {
        Intent in=new Intent(mContext,OtherActivity.class);
        startActivity(in);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
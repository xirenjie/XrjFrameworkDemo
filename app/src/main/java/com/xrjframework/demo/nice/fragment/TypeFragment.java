package com.xrjframework.demo.nice.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xrj.framework.Util.ResourceUtil;
import com.xrj.framework.nice.fragment.BaseFragment;
import com.xrj.framework.nice.fragment.BaseMvpFragment;
import com.xrjframework.demo.R;
import com.xrjframework.demo.nice.adapter.TypePageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:27
 */
public class TypeFragment extends BaseFragment {
    private static final String TYPE = "type";

    private String mType;
    private List<BaseMvpFragment> mFragments;
    private List<String> mTitles;

    private TypePageAdapter mTypeAdapter;

    @BindView(R.id.type_tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.type_viewpager)
    ViewPager mViewPager;


    @Override
    protected int initLayoutId() {
        return R.layout.fragment_type_layout;
    }

    @Override
    protected void initView() {
        mTypeAdapter = new TypePageAdapter(getChildFragmentManager());
        mTypeAdapter.setData(mFragments, mTitles);
        mViewPager.setAdapter(mTypeAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.size() - 1);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        //build 24以前使用的方法
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        //build 23以后使用的方法
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            //cannot resolve method addOnTabSelectedListener
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        if (getArguments() == null) {
            return;
        }

        mFragments = new ArrayList<>();

        mType = getArguments().getString(TYPE);

        if (ResourceUtil.resToStr(mActivity, R.string.gank).equals(mType)) {
            mTitles=Arrays.asList(mActivity.getResources().getStringArray(R.array.gank));
           // mTitles = ResourceUtil.stringArrayToList(mActivity, R.array.gank);
            for (String title : mTitles) {
                mFragments.add(GankItemFragment.newInstance(title));
            }
        } else if (ResourceUtil.resToStr(mActivity, R.string.girl).equals(mType)) {
            mTitles = ResourceUtil.stringArrayToList(mActivity, R.array.girl);
            List<String> subtypes = ResourceUtil.stringArrayToList(mActivity, R.array.girl_cid);
            for (String subtype : subtypes) {
               // mFragments.add(GirlItemFragment.newInstance(subtype));
            }
        }
    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    public static TypeFragment newInstance(String type) {
        TypeFragment fragment = new TypeFragment();
        Bundle arguments = new Bundle();
        arguments.putString(TYPE, type);
        fragment.setArguments(arguments);
        return fragment;
    }
}

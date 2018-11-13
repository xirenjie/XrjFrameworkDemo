package com.xrjframework.demo.video;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 袭人杰 on 2018/1/11.
 */
public class ListVideoActivity extends BaseActivity{
    @BindView(R.id.video_list_recycle)
    RecyclerView video_list_recycle;
    private VideoAdapter videoAdapter;
    List<String> ls;
    @Override
    protected int initLayoutId() {
        return R.layout.video_list_layout;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        video_list_recycle.setLayoutManager(layoutManager);
        videoAdapter = new VideoAdapter(ListVideoActivity.this, new ArrayList<String>(), true);
        videoAdapter.setLoadingView(R.layout.load_loading_layout);
        video_list_recycle.setAdapter(videoAdapter);
        ls=new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            ls.add("gggggggg");
        }
        videoAdapter.setNewData(ls);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }
}

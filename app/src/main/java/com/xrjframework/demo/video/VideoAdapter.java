package com.xrjframework.demo.video;

import android.content.Context;

import com.xrj.framework.nice.adapter.BaseAdapter;
import com.xrj.framework.nice.adapter.ViewHolder;
import com.xrjframework.demo.R;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by 袭人杰 on 2018/1/11.
 */
public class VideoAdapter extends BaseAdapter<String> {

    public VideoAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }
    @Override
    protected void convert(ViewHolder holder, String data) {
        JZVideoPlayerStandard jcVideoPlayer= (JZVideoPlayerStandard) holder.getConvertView().findViewById(R.id.videoplayer);
        jcVideoPlayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        jcVideoPlayer.thumbImageView.setImageResource(R.drawable.beautiful);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_videoview;
    }
}

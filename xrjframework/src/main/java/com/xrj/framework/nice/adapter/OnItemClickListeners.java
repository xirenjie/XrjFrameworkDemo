package com.xrj.framework.nice.adapter;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */
public interface OnItemClickListeners<T> {
    void onItemClick(ViewHolder viewHolder, T data, int position);
}

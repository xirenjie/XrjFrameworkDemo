package com.xrjframework.demo.nice.adapter.common;

/**
 * Created by ${吴心良}
 * on 2017/4/28.
 * description:
 */

public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}


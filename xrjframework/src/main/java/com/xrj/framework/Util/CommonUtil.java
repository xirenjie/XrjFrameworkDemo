package com.xrj.framework.Util;

import android.content.Context;

/**
 * Created by a on 2017/6/16.
 * 使用getIdentifier()获取资源Id
 *
 * @auther XRJ
 */

public class CommonUtil {
    public static int getStatusBarHeight(Context context) {
        int result;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        result = context.getResources().getDimensionPixelSize(resourceId);
        if (result <= 0) {
            result = 25;
        }
        return result;
    }
}

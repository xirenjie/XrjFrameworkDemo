package com.xrj.framework.Util;

import android.content.Context;
import android.content.Intent;

import com.xrj.framework.R;
/**
 * Created by a on 2017/6/16.
 * 分享 发送文本类型
 *
 * @auther XRJ
 */
public class ShareUtil {
    public static void share(Context context, String plainContent) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, plainContent);
        intent.setType("text/plain");

        context.startActivity(Intent.createChooser(intent, ResourceUtil.resToStr(context, R.string.share_to)));
    }
}

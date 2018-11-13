package com.xrj.framework.Util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by a on 2017/6/16.
 *  普通字符串，URL和Intent三种数据使用情况：

 （1）普通字符：就是普通字符串的剪切，复制，粘贴。
 （2）URL：在复制的时候可以复制一个URL,这个URL可以是请求ContentProvider的URL，在粘贴的时候调用ContntProvider获取数据，并使用。
 （3）Intent：如，在记事本应用中长按某一个记录本条目，这时会创建删除这个记事本的Intent，并添加到剪贴板，当用户将这个记事本条目拖到垃圾桶松开时，应用会从剪贴板中获取Intent并执行，这个记事本条目就被删除了。
 *
 * @auther XRJ
 */
public class CopyUtil {
    public static void copy(Context context, String plaintText) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText(null, plaintText));
    }
}

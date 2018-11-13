package com.xrj.framework.Util;

import android.support.design.widget.Snackbar;
import android.view.View;
/**
 * Created by a on 2017/6/16.
 * 提示框
 *
 * @auther XRJ
 */
public class SnackBarUtil {
    private static int messageShowCount = 0;
    private static int gcCount = 5;

    public static void count() {
        messageShowCount++;
        if (messageShowCount >= gcCount) {
            System.gc();
            messageShowCount = 0;
        }
    }
    public static void show(View rootView, int textId) {
        count();
        Snackbar.make(rootView, textId, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View rootView, String text) {
        count();
        Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT).show();
    }
}

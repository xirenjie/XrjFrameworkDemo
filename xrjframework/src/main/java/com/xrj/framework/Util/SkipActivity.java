package com.xrj.framework.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by a on 2017/6/15.
 *
 * @auther XRJ
 */
public class SkipActivity {
    public static void toActivity(Activity activity,Class<?> cls){
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }
    public static void toActivity(Activity activity,Class<?> cls,Bundle bundle){
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

}
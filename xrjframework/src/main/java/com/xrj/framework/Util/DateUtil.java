package com.xrj.framework.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Created by a on 2017/6/16.
 * 返回年月日日期
 *
 * @auther XRJ
 */
public class DateUtil {
    public static final String TYPE1 = "yyyy-MM-dd";

    public static String formatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TYPE1, Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}

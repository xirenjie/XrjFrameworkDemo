package com.xrj.framework.Util;

import java.math.BigDecimal;

/**
 * Created by a on 2017/6/16.
 * 获取文件大小
 *
 * @auther XRJ
 */
public class FileUtil {
    public static long getFileLength(java.io.File dir) {
        long length = 0;
        for (java.io.File file :
                dir.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else
                length += getFileLength(file);
        }
        return length;
    }

    public static String getFileSize(java.io.File dir) {
        BigDecimal bd;
        if (getFileLength(dir) > 1024 * 1024) {
            bd = new BigDecimal(getFileLength(dir) / 1000000);
            return bd.setScale(2, BigDecimal.ROUND_HALF_EVEN) + " M";

        } else {
            bd = new BigDecimal(getFileLength(dir) / 1000);
            return bd.setScale(0, BigDecimal.ROUND_HALF_EVEN) + " k";

        }
    }
}

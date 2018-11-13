package com.xrjframework.demo;

import android.util.Log;

/**
 * Created by 袭人杰 on 2018/5/22.
 */
public class ClassFormatTest {


    /**
     * 构造函数
     */
    public ClassFormatTest() {
        System.out.println("classDemo!");
        test();
    }

    private String test()
    {
        String str="哈哈哈哈";
        Log.i("xiren","反射"+str);
        return  str;
    }

}

package com.xrjframework.demo;

import android.app.Application;

/**
 * Created by 袭人杰 on 2017/10/13.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
//        Cockroach.install(new Cockroach.ExceptionHandler() {
//
//            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException
//
//            @Override
//            public void handlerException(final Thread thread, final Throwable throwable) {
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Log.d("Cockroach", thread + "\n" + throwable.toString());
//                            throwable.printStackTrace();
//                            Toast.makeText(MyApplication.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
////                        throw new RuntimeException("..."+(i++));
//                        } catch (Throwable e) {
//
//                        }
//                    }
//                });
//            }
//        });
    }
}

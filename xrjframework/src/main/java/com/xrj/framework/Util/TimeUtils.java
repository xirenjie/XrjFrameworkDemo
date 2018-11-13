package com.xrj.framework.Util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
public class TimeUtils {
    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }
    /**
     * 获取当前秒数
     * @return
     */
    public static String getSecond(){
        Calendar calendar = Calendar.getInstance();
        int sec = calendar.get(Calendar.SECOND);
        String value = String.valueOf(sec<10?"0"+sec:sec);
        return value;
    }

    /**
     * 获取当天日期
     *
     * @return 当前时间
     */
    public static String getriqi() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当天日期
        String time = formatter.format(curDate);

        return time;
    }
    /**
     * 获取当前全部时间
     *
     * @return 当前时间
     */
    public static String getalldate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }

    public static Date getDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 天数减一
     */
    public static String Yesterday(int data) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - data);
        // Date endDate = dft.parse(dft.format(date.getTime()));
        String time = dft.format(date.getTime());
        return time;
    }
    /**
     * 获取星期
     *
     * @param date
     *            ****-**-**
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 时间顺序判断
     * @param data1
     * @param data2
     * @return
     */
    public static boolean conpare_data(String data1,String data2){
        if(data1==null||"".equals(data1)){
            return true;
        }
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date dt1 = dFormat.parse(data1);
            Date dt2 = dFormat.parse(data2);
            if(dt1.getTime()>dt2.getTime()){
                Log.i("time_compare", "dt1在dt2之后");
                return false;
            }else if (dt1.getTime() < dt2.getTime()) {
                Log.i("time_compare", "dt1在dt2之前");
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 日期比较
     */
    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                if (dt1.getTime() - dt2.getTime() == 86400000) {
                    return 1;
                } else if (dt1.getTime() - dt2.getTime() == (86400000 * 2)) {
                    return 2;
                } else if (dt1.getTime() - dt2.getTime() == (86400000 * 3)) {
                    return 3;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }

    }
    /**
     * 返回两个string类型日期之间相差的天数
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try{
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 返回两个string类型日期相差的小时数
     */
    public static int daysBetween2(String startTime, String endTime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try{
            cal.setTime(sdf.parse(startTime));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(endTime));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600);

        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 计算两段日期的重合日期
     */
    /**
     * 计算两段日期的重合日期
     * @param str1 开始日期1
     * @param str2 结束日期1
     * @param str3 开始日期2
     * @param str4 结束日期2
     * @return
     * @throws Exception
     */
    public static Map<String,Object> comparisonRQ(String str1, String str2, String str3,
                                                  String str4) throws Exception {
        String mesg = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startdate = "";
        String enddate = "";
        try {
            Date dt1 = df.parse(str1);
            Date dt2 = df.parse(str2);
            Date dt3 = df.parse(str3);
            Date dt4 = df.parse(str4);
            if (dt1.getTime()<=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str2;
            }
            if (dt1.getTime()>=dt3.getTime()&&dt3.getTime()<=dt2.getTime()&&dt2.getTime()<=dt4.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str2;
            }

            if (dt3.getTime()<=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str1;
                enddate = str4;
            }
            if (dt3.getTime()>=dt1.getTime()&&dt1.getTime()<=dt4.getTime()&&dt4.getTime()<=dt2.getTime()) {
                mesg = "f";//重合
                startdate = str3;
                enddate = str4;
            }

            System.out.println(startdate+"----"+enddate);


        }catch (ParseException e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), 0);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        return map;
    }
}

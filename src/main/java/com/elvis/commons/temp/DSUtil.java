package com.elvis.commons.temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DSUtil {
    private DSUtil() {
    }

    public static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyy_MM = "yyyy-MM";
    public static String HH_mm_ss = "HH:mm:ss";
    public static String HH_mm = "HH:mm";
    public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static String yyyyMMddHH = "yyyyMMddHH";
    public static String yyyyMMdd = "yyyyMMdd";
    public static String yyyyMM = "yyyyMM";
    public static String HHmmssSSS = "HHmmssSSS";
    public static String HHmmss = "HHmmss";
    public static String HHmm = "HHmm";
    public static String c_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
    public static String c_yyyy_MM_dd_HH_mm_ss = "yyyy年MM月dd日HH时mm分ss秒";
    public static String c_yyyy_MM_dd_HH_mm = "yyyy年MM月dd日HH时mm分";
    public static String c_yyyy_MM_dd_HH = "yyyy年MM月dd日HH时";
    public static String c_yyyy_MM_dd = "yyyy年MM月dd日";
    public static String c_yyyy_MM = "yyyy年MM月";
    public static String c_yyyy = "yyyy年";
    public static String c_HH_mm_ss_SSS = "HH时mm分ss秒SSS毫秒";
    public static String c_HH_mm_ss = "HH时mm分ss秒";
    public static String c_HH_mm = "HH时mm分";
    public static String c_yyyy_MM_dd_EE = "yyyy年MM月dd日 EE";
    public static String c_EE = "EE";
    public static String x_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    public static String x_yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
    public static String x_yyyy_MM_dd_HH_mm = "yyyy/MM/dd HH:mm";
    public static String x_yyyy_MM_dd_HH = "yyyy/MM/dd HH";
    public static String x_yyyy_MM_dd = "yyyy/MM/dd";
    public static String x_yyyy_MM = "yyyy/MM";
    public static String xh_yyyy_MM_dd_HH = "yyyy_MM_dd_HH";
    public static String xh_yyyy_MM_dd = "yyyy_MM_dd";
    public static String xh_yyyy_MM = "yyyy_MM";
    public static String yyyy = "yyyy";
    public static String MM = "MM";
    public static String dd = "dd";
    public static String HH = "HH";
    public static String mm = "mm";
    public static String ss = "ss";
    public static String SSS = "SSS";

    public static SimpleDateFormat sdf(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr, String pattern) {
        return parseDate(dateStr, new SimpleDateFormat(pattern));
    }

    private static Date parseDate(String dateStr, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

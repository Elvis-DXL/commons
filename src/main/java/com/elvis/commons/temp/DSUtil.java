package com.elvis.commons.temp;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public final class DSUtil {
    private DSUtil() {
    }

    //时间日期格式
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

    //地球平均半径(m)
    private static final double EARTH_RADIUS = 6371393;

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parseDate(String dateStr, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Calendar dateToCalendar(Date date) {
        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date addDate(Date date, int calendarType, int num) {
        Calendar calendar = dateToCalendar(date);
        calendar.add(calendarType, num);
        return calendar.getTime();
    }

    public static String nowDateStr(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static int monthDaysByDate(Date date) {
        Calendar calendar = dateToCalendar(date);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    public static Date dateStartTime(Date date) {
        Calendar calendar = dateToCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date dateEndTime(Date date) {
        Calendar calendar = dateToCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static void isNotEmpty(CharSequence cs, Consumer<CharSequence> consumer) {
        if (isEmpty(cs)) {
            return;
        }
        consumer.accept(cs);
    }

    public static void isNotEmpty(Collection<?> coll, Consumer<Collection<?>> consumer) {
        if (isEmpty(coll)) {
            return;
        }
        consumer.accept(coll);
    }

    public static <T> T randomGetOne(List<T> srcList) {
        return srcList.get(ThreadLocalRandom.current().nextInt(srcList.size()));
    }

    public static Double doubleFmt(Double value, Integer num) {
        if (value == null) {
            return 0D;
        }
        if (num == null || num < 1) {
            return value;
        }
        StringBuilder builder = new StringBuilder("#.");
        for (int i = 0; i < num; i++) {
            builder.append("0");
        }
        DecimalFormat df = new DecimalFormat(builder.toString());
        return Double.parseDouble(df.format(value));
    }

    public static double lngLatDistance(double srcLng, double srcLat, double aimLng, double aimLat) {
        return EARTH_RADIUS * Math.acos(Math.cos(Math.toRadians(srcLat)) * Math.cos(Math.toRadians(aimLat))
                * Math.cos(Math.toRadians(srcLng) - Math.toRadians(aimLng))
                + Math.sin(Math.toRadians(srcLat)) * Math.sin(Math.toRadians(aimLat)));
    }

    public static String intToStrByLength(int num, Integer length) {
        if (null == length) {
            return num + "";
        }
        String str = num + "";
        while (str.length() < length) {
            str = "0".concat(str);
        }
        return str;
    }
}

package com.elvis.commons.utils;

import com.elvis.commons.enums.DPEnum;

import java.util.Calendar;
import java.util.Date;

/**
 * 身份证工具集
 *
 * @author : Elvis
 * @since : 2021/11/24 16:52
 */
public final class IdCardUtil {

    private IdCardUtil() {
        throw new AssertionError("Utility classes do not allow instantiation");
    }

    public static Date birthday(String idCard) {
        return DateUtil.parseDate(idCard.substring(6, 14), DPEnum.yyyyMMdd);
    }

    public static String birthdayStr(String idCard, String pattern) {
        return DateUtil.formatDate(birthday(idCard), pattern);
    }

    public static String birthdayStr(String idCard, DPEnum dpEnum) {
        return birthdayStr(idCard, dpEnum.pattern());
    }

    public static int currAge(String idCard) {
        return dateAge(idCard, new Date());
    }

    public static int dateAge(String idCard, Date date) {
        int bornYear = Integer.parseInt(idCard.substring(6, 10));
        int dateYear = DateUtil.get(date, Calendar.YEAR);
        return Math.max(dateYear - bornYear, 0);
    }

    public static int sex(String idCard) {
        int sex = Integer.parseInt(idCard.substring(16, 17));
        return sex % 2;
    }

    public static String sexStr(String idCard) {
        return sex(idCard) == 1 ? "男" : "女";
    }
}

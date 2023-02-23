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
    }

    public static Date getBirthday(String idCardNumber) {
        return DateUtil.parseDate(idCardNumber.substring(6, 14), DPEnum.yyyyMMdd);
    }

    public static String getBirthdayStr(String idCardNumber, String pattern) {
        return DateUtil.formatDate(getBirthday(idCardNumber), pattern);
    }

    public static String getBirthdayStr(String idCardNumber, DPEnum dpEnum) {
        return getBirthdayStr(idCardNumber, dpEnum.pattern());
    }

    public static int currAge(String idCardNumber) {
        int bornYear = Integer.parseInt(idCardNumber.substring(6, 10));
        int currYear = DateUtil.get(new Date(), Calendar.YEAR);
        return currYear - bornYear;
    }

    public static int sex(String idCardNumber) {
        int sex = Integer.parseInt(idCardNumber.substring(16, 17));
        return sex % 2;
    }

    public static String sexStr(String idCardNumber) {
        return sex(idCardNumber) == 1 ? "男" : "女";
    }
}

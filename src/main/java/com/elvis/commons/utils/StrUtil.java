package com.elvis.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : Elvis
 * @since : 2020/6/24 11:04
 */
public final class StrUtil {

    private StrUtil() {
    }

    /**
     * 判断字符是否是null或者空字符串
     *
     * @param cs 字符序列
     * @return 结果
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符是否不是null并且不是空字符串
     *
     * @param cs 字符序列
     * @return 结果
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 分割字符串并转换成Long
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<Long> splitToLong(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<Long> result = new ArrayList<Long>();
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            result.add(Long.valueOf(temp));
        }
        return result;
    }

    /**
     * 分割字符串并转换成Integer
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<Integer> splitToInteger(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<Integer> result = new ArrayList<Integer>();
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            result.add(Integer.valueOf(temp));
        }
        return result;
    }

    /**
     * 分割字符串
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<String> split(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<String> result = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * 使用指定的字符连接字符串集合
     *
     * @param strCol         字符源集合
     * @param separatorChars 分割符
     * @return 字符串结果
     */
    public static String joinStr(Collection<String> strCol, String separatorChars) {
        if (null == strCol || strCol.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strCol) {
            sb.append(str);
            sb.append(separatorChars);
        }
        String result = sb.toString();
        return result.substring(0, result.length() - separatorChars.length());
    }
}

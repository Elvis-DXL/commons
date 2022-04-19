package com.elvis.commons.utils;

import java.text.DecimalFormat;

/**
 * 数字工具集
 *
 * @author : Elvis
 * @since : 2021/3/26 10:20
 */
public final class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Long max(Long... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Long max = null;
        for (Long number : numbers) {
            if (null == number) {
                continue;
            }
            max = number;
            break;
        }
        if (null == max) {
            return null;
        }
        for (Long number : numbers) {
            if (null == number) {
                continue;
            }
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Double max(Double... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Double max = null;
        for (Double number : numbers) {
            if (null == number) {
                continue;
            }
            max = number;
            break;
        }
        if (null == max) {
            return null;
        }
        for (Double number : numbers) {
            if (null == number) {
                continue;
            }
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Integer max(Integer... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Integer max = null;
        for (Integer number : numbers) {
            if (null == number) {
                continue;
            }
            max = number;
            break;
        }
        if (null == max) {
            return null;
        }
        for (Integer number : numbers) {
            if (null == number) {
                continue;
            }
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Short max(Short... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Short max = null;
        for (Short number : numbers) {
            if (null == number) {
                continue;
            }
            max = number;
            break;
        }
        if (null == max) {
            return null;
        }
        for (Short number : numbers) {
            if (null == number) {
                continue;
            }
            max = max > number ? max : number;
        }
        return max;
    }


    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Long min(Long... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Long min = null;
        for (Long number : numbers) {
            if (null == number) {
                continue;
            }
            min = number;
            break;
        }
        if (null == min) {
            return null;
        }
        for (Long number : numbers) {
            if (null == number) {
                continue;
            }
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Double min(Double... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Double min = null;
        for (Double number : numbers) {
            if (null == number) {
                continue;
            }
            min = number;
            break;
        }
        if (null == min) {
            return null;
        }
        for (Double number : numbers) {
            if (null == number) {
                continue;
            }
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Integer min(Integer... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Integer min = null;
        for (Integer number : numbers) {
            if (null == number) {
                continue;
            }
            min = number;
            break;
        }
        if (null == min) {
            return null;
        }
        for (Integer number : numbers) {
            if (null == number) {
                continue;
            }
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Short min(Short... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Short min = null;
        for (Short number : numbers) {
            if (null == number) {
                continue;
            }
            min = number;
            break;
        }
        if (null == min) {
            return null;
        }
        for (Short number : numbers) {
            if (null == number) {
                continue;
            }
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 四舍五入后保留小数
     *
     * @param value 值
     * @param num   保留小数位数
     * @return 结果
     */
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

}

package com.elvis.commons.utils;

/**
 * 经纬度工具集
 *
 * @author : Elvis
 * @since : 2023/2/3 16:41
 */
public final class LngLatUtil {

    private static final double EARTH_RADIUS = 6371393;//地球平均半径(m)

    private LngLatUtil() {
    }

    /**
     * 计算两个经纬度之间距离
     *
     * @param srcLng 起点经度
     * @param srcLat 起点纬度
     * @param aimLng 终点经度
     * @param aimLat 终点纬度
     * @return 距离(m)
     */

    public static double getDistance(double srcLng, double srcLat, double aimLng, double aimLat) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(srcLng); // A经弧度
        double radiansAY = Math.toRadians(srcLat); // A纬弧度
        double radiansBX = Math.toRadians(aimLng); // B经弧度
        double radiansBY = Math.toRadians(aimLat); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值
        return EARTH_RADIUS * acos; // 最终结果
    }

}

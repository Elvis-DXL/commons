package com.elvis.commons.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author : Elvis
 * @since : 2022/3/17 11:10
 */
public final class DownloadUtil {

    private DownloadUtil() {
    }

    private static final String FIREFOX = "firefox";

    public static String encodeDownloadFileName(String fileName, HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        try {
            if (agent.contains(FIREFOX)) {
                // 火狐浏览器
                fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
                fileName = fileName.replaceAll("\r\n", "");
            } else {
                // IE及其他浏览器
                fileName = URLEncoder.encode(fileName, "utf-8");
                fileName = fileName.replace("+", " ");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}

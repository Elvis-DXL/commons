package com.elvis.commons.zzold.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author : Elvis
 * @since : 2020/9/3 17:05
 */
public final class DownloadUtils {
    private DownloadUtils() {
    }

    private static final String FIREFOX = "firefox";

    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     *
     * @param fileName 下载文件名
     * @param agent    客户端浏览器
     * @throws IOException 异常
     */
    public static String encodeFileName(String fileName, String agent) throws UnsupportedEncodingException {
        agent = agent.toLowerCase();
        if (agent.contains(FIREFOX)) {
            // 火狐浏览器
            fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
            fileName = fileName.replaceAll("\r\n", "");
        } else {
            // IE及其他浏览器
            fileName = URLEncoder.encode(fileName, "utf-8");
            fileName = fileName.replace("+", " ");
        }
        return fileName;
    }

    /**
     * 下载FTP服务器文件
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param ftpUtils  ftp工具
     * @param fileName 文件名
     * @param filePath 文件目录
     */
    public static void downloadByFtp(HttpServletRequest request, HttpServletResponse response, FtpUtils ftpUtils, String fileName, String filePath) {
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        OutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            String name = encodeFileName(fileName, agent);
            response.setHeader("Content-disposition", "attachment;filename=" + name);
            response.setContentType("application/octet-stream");
            ftpUtils.downloadFile(filePath, fileName, outStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(outStream);
        }
    }

}

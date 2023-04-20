package com.elvis.commons.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO工具集
 *
 * @author : Elvis
 * @since : 2020/6/24 11:07
 */
public final class IOUtil {

    private IOUtil() {
    }

    /**
     * 关闭流
     *
     * @param streams 流
     */
    public static void closeStream(Closeable... streams) {
        if (null == streams || streams.length == 0) {
            return;
        }
        for (Closeable stream : streams) {
            if (null == stream) {
                continue;
            }
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输入流到输出流
     *
     * @param inStream  输入流
     * @param outStream 输出流
     */
    public static void inToOut(InputStream inStream, OutputStream outStream) {
        byte[] cache = new byte[1024 * 1024 * 20];
        try {
            int len = inStream.read(cache);
            while (len > 0) {
                outStream.write(cache, 0, len);
                outStream.flush();
                len = inStream.read(cache);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(inStream, outStream);
        }
    }

}

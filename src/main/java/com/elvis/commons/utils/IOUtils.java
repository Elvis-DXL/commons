package com.elvis.commons.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : Elvis
 * @since : 2020/6/24 11:07
 */
public final class IOUtils {

    private IOUtils() {
    }

    /**
     * 关闭流
     *
     * @param streams 流
     */
    public static void closeStream(Closeable... streams) {
        for (Closeable item : streams) {
            if (null == item) {
                continue;
            }
            try {
                item.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输入流到输出流
     *
     * @param in  输入流
     * @param out 输出流
     */
    public static void inToOut(InputStream in, OutputStream out) {
        byte[] cache = new byte[1024 * 1024 * 20];
        try {
            int len = in.read(cache);
            while (len > 0) {
                out.write(cache, 0, len);
                out.flush();
                len = in.read(cache);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(in, out);
        }
    }

}

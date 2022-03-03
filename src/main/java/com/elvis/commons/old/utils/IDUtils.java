package com.elvis.commons.old.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : Elvis
 * @since : 2020/10/22 11:50
 */
public final class IDUtils {

    private IDUtils() {
    }

    /**
     * 获取32位UUID字符串
     *
     * @return 获取结果
     */
    public static String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString().replace("-", "");
    }
}

package com.elvis.commons.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author : Elvis
 * @since : 2021/5/28 09:29
 */
public final class EncryptUtils {

    private Key key;

    private final String CHARSET = "UTF-8";
    private final String ALGORITHM = "DES";

    public EncryptUtils(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            throw new IllegalArgumentException("SecretKey can not be empty");
        }
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKey.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to Construct Encrypt Key");
        }
    }

    public String encode(String aimStr) {
        if (StringUtils.isEmpty(aimStr)) {
            return null;
        }
        Base64 base64 = new Base64();
        try {
            byte[] bytes = aimStr.getBytes(CHARSET);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(base64.encode(doFinal));
        } catch (Exception e) {
            throw new RuntimeException("Failed to Encode");
        }
    }

    public String decode(String aimStr) {
        if (StringUtils.isEmpty(aimStr)) {
            return null;
        }
        Base64 base64 = new Base64();
        try {
            byte[] bytes = base64.decode(aimStr);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("Failed to Decode");
        }
    }

}

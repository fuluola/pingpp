package com.pingpp.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author fuzhuan
 * @created 2016-09-18
 */
public class SecurityUtil {

    public static final String KEY_MD5 = "MD5";

    /**
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        byte[] b = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        buf.toString();

        return buf.toString().getBytes();

    }

    public static String MD5(byte[] data) throws NoSuchAlgorithmException {
        
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(data);
            byte[] b = md5.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
           return  buf.toString();
        
    }
    
}

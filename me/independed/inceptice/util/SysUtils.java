/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.util;

import java.security.MessageDigest;
import java.util.ArrayList;

public class SysUtils {
    public static ArrayList array = new ArrayList();

    public static String getByOther() {
        String string = String.valueOf(System.getProperty("user.name")) + System.getProperty("os.version") + System.getProperty("os.name") + System.getProperty("user.name");
        byte[] arrby = string.getBytes("iso-8859-1");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] arrby2 = new byte[40];
        messageDigest.update(arrby);
        arrby2 = messageDigest.digest();
        return SysUtils.convertToHex(arrby2);
    }

    public static String convertToHex(byte[] arrby) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = 0;
        if (n < arrby.length) {
            int n2 = arrby[n] >>> 4 & 0xF;
            int n3 = 0;
            while (true) {
                if (n2 >= 0 && n2 <= 9) {
                    stringBuffer.append((char)(48 + n2));
                } else {
                    stringBuffer.append((char)(97 + (n2 - 10)));
                }
                n2 = arrby[n] & 0xF;
                ++n3;
            }
        }
        return stringBuffer.toString();
    }
}


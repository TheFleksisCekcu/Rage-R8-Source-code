/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.util;

import java.awt.Color;

public class ColorUtils {
    public static Color rainbow() {
        long l = 999999999999L;
        float f = 1.0f;
        float f2 = (float)(System.nanoTime() + l) / 1.0E10f % 1.0f;
        long l2 = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(f2, 1.0f, 1.0f)), 16);
        Color color = new Color((int)l2);
        return new Color((float)color.getRed() / 255.0f * f, (float)color.getGreen() / 255.0f * f, (float)color.getBlue() / 255.0f * f, (float)color.getAlpha() / 255.0f);
    }

    public static int color(int n, int n2, int n3, int n4) {
        n4 = 255;
        return new Color(n, n2, n3, n4).getRGB();
    }

    public static int color(float f, float f2, float f3, float f4) {
        return new Color(f, f2, f3, f4).getRGB();
    }
}



/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 */
package me.independed.inceptice.util;

import net.minecraft.client.gui.FontRenderer;

public class GuiUtil {
    public static void drawCenteredLongText(FontRenderer fontRenderer, String string, int n, int n2, int n3, int n4) {
        int n5 = 0;
        while (!string.isEmpty()) {
            String string2 = string;
            while (fontRenderer.getStringWidth(string2) > n3) {
                int n6 = string2.lastIndexOf(" ");
                if (n6 < 0) {
                    string2 = "";
                    continue;
                }
                string2 = string2.substring(0, n6);
            }
            string = string.substring(string.indexOf(string2) + string2.length());
            string2 = "\u00a77" + string2.trim();
            if (n5 >= n4 - 1 && !string.isEmpty()) {
                fontRenderer.drawString(string2 + "...", n - fontRenderer.getStringWidth(string2 + "..."), n2 + (n5 * fontRenderer.FONT_HEIGHT + 4), 0xFFFFFF);
                return;
            }
            fontRenderer.drawString(string2, n - fontRenderer.getStringWidth(string2), n2 + (n5 * fontRenderer.FONT_HEIGHT + 4), 0xFFFFFF);
            ++n5;
        }
    }

    public static void drawLongText(FontRenderer fontRenderer, String string, int n, int n2, int n3, int n4) {
        int n5 = 0;
        while (!string.isEmpty()) {
            String string2 = string;
            while (fontRenderer.getStringWidth(string2) > n3) {
                int n6 = string2.lastIndexOf(" ");
                if (n6 < 0) {
                    string2 = "";
                    continue;
                }
                string2 = string2.substring(0, n6);
            }
            string = string.substring(string.indexOf(string2) + string2.length());
            string2 = "\u00a77" + string2.trim();
            if (n5 >= n4 - 1 && !string.isEmpty()) {
                fontRenderer.drawString(string2 + "...", n, n2 + (n5 * fontRenderer.FONT_HEIGHT + 4), 0xFFFFFF);
                return;
            }
            fontRenderer.drawString(string2, n, n2 + (n5 * fontRenderer.FONT_HEIGHT + 4), 0xFFFFFF);
            ++n5;
        }
    }
}


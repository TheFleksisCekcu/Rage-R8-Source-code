
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  me.independed.inceptice.secondclickgui.GlyphPage
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.secondclickgui;

import java.util.Locale;
import java.util.Random;
import me.independed.inceptice.secondclickgui.GlyphPage;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GlyphPageFontRenderer {
    private float alpha;
    private boolean underlineStyle;
    private GlyphPage boldItalicGlyphPage;
    private float green;
    private boolean strikethroughStyle;
    private GlyphPage italicGlyphPage;
    private int textColor;
    private int[] colorCode;
    private boolean italicStyle;
    private float posY;
    private boolean randomStyle;
    private float posX;
    private boolean boldStyle;
    public Random fontRandom = new Random();
    private float blue;
    private GlyphPage regularGlyphPage;
    private GlyphPage boldGlyphPage;
    private float red;

    public static GlyphPageFontRenderer create(String string, int n, boolean bl, boolean bl2, boolean bl3) {
        char[] arrc = new char[256];
        int n2 = 0;
        while (true) {
            int cfr_ignored_0 = arrc.length;
            arrc[n2] = (char)n2;
            ++n2;
        }
    }

    public int getStringWidth(String string) {
        if (string == null) {
            return 0;
        }
        int n = 0;
        int n2 = string.length();
        boolean bl = false;
        for (int i = 0; i < n2; ++i) {
            char c = string.charAt(i);
            c = string.charAt(i);
            GlyphPage glyphPage = this.getCurrentGlyphPage();
            n = (int)((float)n + (glyphPage.getWidth(c) - 8.0f));
        }
        return n / 2;
    }

    public int drawString(String string, float f, float f2, int n, boolean bl) {
        int n2;
        GlStateManager.enableAlpha();
        this.resetStyles();
        if (bl) {
            n2 = this.renderString(string, f + 1.0f, f2 + 1.0f, n, true);
            n2 = Math.max(n2, this.renderString(string, f, f2, n, false));
        } else {
            n2 = this.renderString(string, f, f2, n, false);
        }
        return n2;
    }

    public GlyphPageFontRenderer(GlyphPage glyphPage, GlyphPage glyphPage2, GlyphPage glyphPage3, GlyphPage glyphPage4) {
        this.colorCode = new int[32];
        this.regularGlyphPage = glyphPage;
        this.boldGlyphPage = glyphPage2;
        this.italicGlyphPage = glyphPage3;
        this.boldItalicGlyphPage = glyphPage4;
        int n = 0;
        while (true) {
            int n2 = (n >> 3 & 1) * 85;
            int n3 = (n >> 2 & 1) * 170 + n2;
            int n4 = (n >> 1 & 1) * 170 + n2;
            int n5 = (n & 1) * 170 + n2;
            this.colorCode[n] = (n3 & 0xFF) << 16 | (n4 & 0xFF) << 8 | n5 & 0xFF;
            ++n;
        }
    }

    public String trimStringToWidth(String string, int n, boolean bl) {
        int n2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl2 = false;
        int n3 = 0;
        int n4 = 1;
        int n5 = 0;
        int n6 = n3;
        while (true) {
            if (n6 >= string.length() || n6 >= n) break;
            char c = string.charAt(n6);
            c = string.charAt(n6);
            GlyphPage glyphPage = this.getCurrentGlyphPage();
            if (n6 > (n5 = (int)((float)n5 + (glyphPage.getWidth(c) - 8.0f) / 2.0f))) break;
            stringBuilder.append(c);
            n6 += n4;
        }
        return stringBuilder.toString();
    }

    public int getFontHeight() {
        return this.regularGlyphPage.getMaxFontHeight() / 2;
    }

    private void doDraw(float f, GlyphPage glyphPage) {
        BufferBuilder bufferBuilder;
        Tessellator tessellator;
        if (this.strikethroughStyle) {
            tessellator = Tessellator.getInstance();
            bufferBuilder = tessellator.getBuffer();
            GlStateManager.disableTexture2D();
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
            bufferBuilder.pos((double)this.posX, (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2)), 0.0).endVertex();
            bufferBuilder.pos((double)(this.posX + f), (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2)), 0.0).endVertex();
            bufferBuilder.pos((double)(this.posX + f), (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2) - 1.0f), 0.0).endVertex();
            bufferBuilder.pos((double)this.posX, (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2) - 1.0f), 0.0).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
        }
        if (this.underlineStyle) {
            tessellator = Tessellator.getInstance();
            bufferBuilder = tessellator.getBuffer();
            GlStateManager.disableTexture2D();
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
            int n = this.underlineStyle ? -1 : 0;
            bufferBuilder.pos((double)(this.posX + (float)n), (double)(this.posY + (float)glyphPage.getMaxFontHeight()), 0.0).endVertex();
            bufferBuilder.pos((double)(this.posX + f), (double)(this.posY + (float)glyphPage.getMaxFontHeight()), 0.0).endVertex();
            bufferBuilder.pos((double)(this.posX + f), (double)(this.posY + (float)glyphPage.getMaxFontHeight() - 1.0f), 0.0).endVertex();
            bufferBuilder.pos((double)(this.posX + (float)n), (double)(this.posY + (float)glyphPage.getMaxFontHeight() - 1.0f), 0.0).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
        }
        this.posX += f;
    }

    public String trimStringToWidth(String string, int n) {
        return this.trimStringToWidth(string, n, false);
    }

    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    private void renderStringAtPos(String string, boolean bl) {
        GlyphPage glyphPage = this.getCurrentGlyphPage();
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.enableTexture2D();
        glyphPage.bindTexture();
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c == '\u00a7' && i + 1 < string.length()) {
                int n = "0123456789abcdefklmnor".indexOf(string.toLowerCase(Locale.ENGLISH).charAt(i + 1));
                if (n < 16) {
                    int n2;
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (n < 0) {
                        n = 15;
                    }
                    if (bl) {
                        n += 16;
                    }
                    this.textColor = n2 = this.colorCode[n];
                    GlStateManager.color((float)((float)(n2 >> 16) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)this.alpha);
                } else if (n == 16) {
                    this.randomStyle = true;
                } else if (n == 17) {
                    this.boldStyle = true;
                } else if (n == 18) {
                    this.strikethroughStyle = true;
                } else if (n == 19) {
                    this.underlineStyle = true;
                } else if (n == 20) {
                    this.italicStyle = true;
                } else {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    GlStateManager.color((float)this.red, (float)this.blue, (float)this.green, (float)this.alpha);
                }
                ++i;
                continue;
            }
            glyphPage = this.getCurrentGlyphPage();
            glyphPage.bindTexture();
            float f = glyphPage.drawChar(c, this.posX, this.posY);
            this.doDraw(f, glyphPage);
        }
        glyphPage.unbindTexture();
        GL11.glPopMatrix();
    }

    private int renderString(String string, float f, float f2, int n, boolean bl) {
        if (string == null) {
            return 0;
        }
        if ((n & 0xFC000000) == 0) {
            n |= 0xFF000000;
        }
        if (bl) {
            n = (n & 0xFCFCFC) >> 2 | n & 0xFF000000;
        }
        this.red = (float)(n >> 16 & 0xFF) / 255.0f;
        this.blue = (float)(n >> 8 & 0xFF) / 255.0f;
        this.green = (float)(n & 0xFF) / 255.0f;
        this.alpha = (float)(n >> 24 & 0xFF) / 255.0f;
        GlStateManager.color((float)this.red, (float)this.blue, (float)this.green, (float)this.alpha);
        this.posX = f * 2.0f;
        this.posY = f2 * 2.0f;
        this.renderStringAtPos(string, bl);
        return (int)(this.posX / 4.0f);
    }

    private GlyphPage getCurrentGlyphPage() {
        if (this.boldStyle && this.italicStyle) {
            return this.boldItalicGlyphPage;
        }
        if (this.boldStyle) {
            return this.boldGlyphPage;
        }
        if (this.italicStyle) {
            return this.italicGlyphPage;
        }
        return this.regularGlyphPage;
    }
}


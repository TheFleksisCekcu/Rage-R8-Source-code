
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.font;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import me.independed.inceptice.font.CFont;
import me.independed.inceptice.font.CFont$CharData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CFontRenderer
extends CFont {
    protected DynamicTexture texItalic;
    protected CFont$CharData[] italicChars;
    private final int[] colorCode;
    protected CFont$CharData[] boldChars = new CFont$CharData[256];
    protected DynamicTexture texBold;
    protected DynamicTexture texItalicBold;
    protected CFont$CharData[] boldItalicChars;
    private final String colorcodeIdentifiers = "0123456789abcdefklmnor";
    String fontName;
    int fontSize;

    public float drawCenteredString(String string, float f, float f2, int n) {
        return this.drawString(string, f - (float)(this.getStringWidth(string) / 2), f2, n);
    }

    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }

    public List formatString(String string, double d) {
        string = "";
        ArrayList arrayList = new ArrayList();
        String string2 = "";
        char[] arrc = string.toCharArray();
        boolean bl = false;
        int cfr_ignored_0 = arrc.length;
        string2.length();
        return arrayList;
    }

    @Override
    public int getStringWidth(String string) {
        if (string == null) {
            return 0;
        }
        int n = 0;
        CFont$CharData[] arrcFont$CharData = this.charData;
        boolean bl = false;
        boolean bl2 = false;
        int n2 = string.length();
        for (int i = 0; i < n2; ++i) {
            char c = string.charAt(i);
            if (c == '\u00a7' && i < n2) {
                int n3 = "0123456789abcdefklmnor".indexOf(c);
                if (n3 < 16) {
                    bl = false;
                    bl2 = false;
                } else if (n3 == 17) {
                    bl = true;
                    arrcFont$CharData = this.boldChars;
                } else if (n3 == 20) {
                    bl2 = true;
                    arrcFont$CharData = this.italicChars;
                } else if (n3 == 21) {
                    bl = false;
                    bl2 = false;
                    arrcFont$CharData = this.charData;
                }
                ++i;
                continue;
            }
            if (c >= arrcFont$CharData.length || c < '\u0000') continue;
            n += arrcFont$CharData[c].width - 8 + this.charOffset;
        }
        return n / 2;
    }

    public float drawCenteredStringWithShadow(String string, float f, float f2, int n) {
        return this.drawStringWithShadow(string, f - (float)(this.getStringWidth(string) / 2), f2, n);
    }

    public float drawStringWithShadow(String string, double d, double d2, int n) {
        float f = this.drawString(string, d + 1.0, d2 + 1.0, n, true);
        return Math.max(f, this.drawString(string, d, d2, n, false));
    }

    public float drawString(String string, double d, double d2, int n, boolean bl) {
        d -= 1.0;
        d2 -= 2.0;
        if (string == null) {
            return 0.0f;
        }
        if (n == 0x20FFFFFF) {
            n = 0xFFFFFF;
        }
        if ((n & 0xFC000000) == 0) {
            n |= 0xFF000000;
        }
        if (bl) {
            n = (n & 0xFCFCFC) >> 2 | n & 0xFF000000;
        }
        CFont$CharData[] arrcFont$CharData = this.charData;
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = true;
        d *= 2.0;
        d2 *= 2.0;
        GL11.glPushMatrix();
        GlStateManager.scale((double)0.5, (double)0.5, (double)0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
        int n2 = string.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture((int)this.tex.getGlTextureId());
        GL11.glBindTexture((int)3553, (int)this.tex.getGlTextureId());
        for (int i = 0; i < n2; ++i) {
            char c = string.charAt(i);
            if (c == '\u00a7' && i < n2) {
                int n3 = 21;
                n3 = "0123456789abcdefklmnor".indexOf(string.charAt(i + 1));
                if (n3 < 16) {
                    bl2 = false;
                    bl3 = false;
                    bl5 = false;
                    bl4 = false;
                    GlStateManager.bindTexture((int)this.tex.getGlTextureId());
                    arrcFont$CharData = this.charData;
                    if (n3 < 0 || n3 > 15) {
                        n3 = 15;
                    }
                    if (bl) {
                        n3 += 16;
                    }
                    int n4 = this.colorCode[n3];
                    GlStateManager.color((float)((float)(n4 >> 16 & 0xFF) / 255.0f), (float)((float)(n4 >> 8 & 0xFF) / 255.0f), (float)((float)(n4 & 0xFF) / 255.0f), (float)f);
                } else if (n3 != 16) {
                    if (n3 == 17) {
                        bl2 = true;
                        GlStateManager.bindTexture((int)this.texBold.getGlTextureId());
                        arrcFont$CharData = this.boldChars;
                    } else if (n3 == 18) {
                        bl4 = true;
                    } else if (n3 == 19) {
                        bl5 = true;
                    } else if (n3 == 20) {
                        bl3 = true;
                        GlStateManager.bindTexture((int)this.texItalic.getGlTextureId());
                        arrcFont$CharData = this.italicChars;
                    } else if (n3 == 21) {
                        bl2 = false;
                        bl3 = false;
                        bl5 = false;
                        bl4 = false;
                        GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
                        GlStateManager.bindTexture((int)this.tex.getGlTextureId());
                        arrcFont$CharData = this.charData;
                    }
                }
                ++i;
                continue;
            }
            if (c >= arrcFont$CharData.length || c < '\u0000') continue;
            GL11.glBegin((int)4);
            this.drawChar(arrcFont$CharData, c, (float)d, (float)d2);
            GL11.glEnd();
            d += (double)(arrcFont$CharData[c].width - 8 + this.charOffset);
        }
        GL11.glHint((int)3155, (int)4352);
        GL11.glPopMatrix();
        return (float)d / 2.0f;
    }

    private void setupMinecraftColorcodes() {
        int n = 0;
        while (true) {
            int n2 = (n >> 3 & 1) * 85;
            int n3 = (n >> 2 & 1) * 170 + n2;
            int n4 = (n >> 1 & 1) * 170 + n2;
            int n5 = (n >> 0 & 1) * 170 + n2;
            this.colorCode[n] = (n3 & 0xFF) << 16 | (n4 & 0xFF) << 8 | n5 & 0xFF;
            ++n;
        }
    }

    public CFontRenderer(Font font, boolean bl, boolean bl2) {
        super(font, bl, bl2);
        this.italicChars = new CFont$CharData[256];
        this.boldItalicChars = new CFont$CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }

    public void setFontName(String string) {
        this.fontName = string;
    }

    public float drawString(String string, float f, float f2, int n) {
        return this.drawString(string, f, f2, n, false);
    }

    public String getFontName() {
        return this.fontName;
    }

    @Override
    public void setAntiAlias(boolean bl) {
        super.setAntiAlias(bl);
        this.setupBoldItalicIDs();
    }

    public List wrapWords(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if ((double)this.getStringWidth(string) > d) {
            String[] arrstring = string.split(" ");
            String string2 = "";
            char c = '\uffff';
            for (String string3 : arrstring) {
                for (int i = 0; i < string3.toCharArray().length; ++i) {
                    char c2 = string3.toCharArray()[i];
                    if (c2 != '\u00a7' || i >= string3.toCharArray().length - 1) continue;
                    c = string3.toCharArray()[i + 1];
                }
                StringBuilder stringBuilder = new StringBuilder();
                if ((double)this.getStringWidth(stringBuilder.append(string2).append(string3).append(" ").toString()) < d) {
                    string2 = string2 + string3 + " ";
                    continue;
                }
                arrayList.add(string2);
                string2 = "\u00a7" + c + string3 + " ";
            }
            string2.length();
        } else {
            arrayList.add(string);
        }
        return arrayList;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int n) {
        this.fontSize = n;
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }

    private void drawLine(double d, double d2, double d3, double d4, float f) {
        f = 1.0f;
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    @Override
    public void setFractionalMetrics(boolean bl) {
        super.setFractionalMetrics(bl);
        this.setupBoldItalicIDs();
    }
}


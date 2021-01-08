/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import me.independed.inceptice.font.CFont$CharData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CFont {
    protected Font font;
    private float imgSize = 512.0f;
    protected int fontHeight = -1;
    protected boolean antiAlias;
    protected DynamicTexture tex;
    protected CFont$CharData[] charData = new CFont$CharData[256];
    protected int charOffset = 0;
    protected boolean fractionalMetrics;

    public void setFont(Font font) {
        this.font = font;
        this.tex = this.setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
    }

    protected BufferedImage generateFontImage(Font font, boolean bl, boolean bl2, CFont$CharData[] arrcFont$CharData) {
        int n = (int)this.imgSize;
        BufferedImage bufferedImage = new BufferedImage(n, n, 2);
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setFont(font);
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, n, n);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, bl2 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, bl ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, bl ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        for (int i = 0; i < arrcFont$CharData.length; ++i) {
            char c = (char)i;
            CFont$CharData cFont$CharData = new CFont$CharData(this);
            Rectangle2D rectangle2D = fontMetrics.getStringBounds(String.valueOf(c), graphics2D);
            cFont$CharData.width = rectangle2D.getBounds().width + 8;
            cFont$CharData.height = rectangle2D.getBounds().height;
            if (n3 + cFont$CharData.width >= n) {
                n3 = 0;
                n4 += n2;
                n2 = 0;
            }
            if (cFont$CharData.height > n2) {
                n2 = cFont$CharData.height;
            }
            cFont$CharData.storedX = n3;
            cFont$CharData.storedY = n4;
            if (cFont$CharData.height > this.fontHeight) {
                this.fontHeight = cFont$CharData.height;
            }
            arrcFont$CharData[i] = cFont$CharData;
            graphics2D.drawString(String.valueOf(c), n3 + 2, n4 + fontMetrics.getAscent());
            n3 += cFont$CharData.width;
        }
        return bufferedImage;
    }

    public void setAntiAlias(boolean bl) {
        if (this.antiAlias != bl) {
            this.antiAlias = bl;
            this.tex = this.setupTexture(this.font, bl, this.fractionalMetrics, this.charData);
        }
    }

    protected void drawQuad(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = f5 / this.imgSize;
        float f10 = f6 / this.imgSize;
        float f11 = f7 / this.imgSize;
        float f12 = f8 / this.imgSize;
        GL11.glTexCoord2f((float)(f9 + f11), (float)f10);
        GL11.glVertex2d((double)(f + f3), (double)f2);
        GL11.glTexCoord2f((float)f9, (float)f10);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glTexCoord2f((float)f9, (float)(f10 + f12));
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glTexCoord2f((float)f9, (float)(f10 + f12));
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glTexCoord2f((float)(f9 + f11), (float)(f10 + f12));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4));
        GL11.glTexCoord2f((float)(f9 + f11), (float)f10);
        GL11.glVertex2d((double)(f + f3), (double)f2);
    }

    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }

    protected DynamicTexture setupTexture(Font font, boolean bl, boolean bl2, CFont$CharData[] arrcFont$CharData) {
        BufferedImage bufferedImage = this.generateFontImage(font, bl, bl2, arrcFont$CharData);
        return new DynamicTexture(bufferedImage);
    }

    public boolean isAntiAlias() {
        return this.antiAlias;
    }

    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }

    public Font getFont() {
        return this.font;
    }

    public int getStringWidth(String string) {
        int n = 0;
        for (char c : string.toCharArray()) {
            if (c >= this.charData.length || c < '\u0000') continue;
            n += this.charData[c].width - 8 + this.charOffset;
        }
        return n / 2;
    }

    public void drawChar(CFont$CharData[] arrcFont$CharData, char c, float f, float f2) throws ArrayIndexOutOfBoundsException {
        this.drawQuad(f, f2, arrcFont$CharData[c].width, arrcFont$CharData[c].height, arrcFont$CharData[c].storedX, arrcFont$CharData[c].storedY, arrcFont$CharData[c].width, arrcFont$CharData[c].height);
    }

    public void setFractionalMetrics(boolean bl) {
        if (this.fractionalMetrics != bl) {
            this.fractionalMetrics = bl;
            this.tex = this.setupTexture(this.font, this.antiAlias, bl, this.charData);
        }
    }

    public int getStringHeight(String string) {
        return this.getHeight();
    }

    public CFont(Font font, boolean bl, boolean bl2) {
        this.font = font;
        this.antiAlias = bl;
        this.fractionalMetrics = bl2;
        this.tex = this.setupTexture(font, bl, bl2, this.charData);
    }
}


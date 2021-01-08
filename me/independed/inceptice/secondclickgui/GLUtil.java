
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.secondclickgui;

import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GLUtil {
    public static void setColor(int n) {
        int n2 = n & 0xFF;
        int n3 = n >> 8 & 0xFF;
        int n4 = n >> 16 & 0xFF;
        int n5 = n >> 24 & 0xFF;
        GL11.glColor4b((byte)((byte)n2), (byte)((byte)n3), (byte)((byte)n4), (byte)((byte)n5));
    }

    public static void drawRect(int n, double d, double d2, double d3, double d4, int n2) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n2 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).endVertex();
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void setColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public static void drawRect(int n, int n2, int n3, int n4, int n5, int n6) {
        int n7;
        if (n2 < n4) {
            n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        if (n3 < n5) {
            n7 = n3;
            n3 = n5;
            n5 = n7;
        }
        float f = (float)(n6 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n6 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)n2, (double)n5, 0.0).endVertex();
        bufferBuilder.pos((double)n4, (double)n5, 0.0).endVertex();
        bufferBuilder.pos((double)n4, (double)n3, 0.0).endVertex();
        bufferBuilder.pos((double)n2, (double)n3, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static int toRGBA(Color color) {
        return color.getRed() | color.getGreen() << 8 | color.getBlue() << 16 | color.getAlpha() << 24;
    }
}


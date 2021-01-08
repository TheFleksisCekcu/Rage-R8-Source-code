
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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.BooleanSetting;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class Esp
extends Module {
    public BooleanSetting invisibleSet;
    private transient int BOX = 0;
    public BooleanSetting animalSet;
    private transient ArrayList ENTITIES = new ArrayList();
    public BooleanSetting mobSet = new BooleanSetting("Mobs", false);
    public BooleanSetting playerSet;

    private void a(Entity entity, double d, double d2, double d3) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d2), (float)((float)d3));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-Esp.mc.getRenderManager().playerViewY), (float)0.0f, (float)0.2f, (float)0.0f);
        GL11.glRotatef((float)Esp.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer();
        if (((EntityLivingBase)entity).getHealth() <= 20.0f) {
            Esp.drawRect(-0.7f, ((EntityLivingBase)entity).getHealth() / 10.0f, -0.58f, 0.0f, new Color(255, 0, 0, 255).getRGB());
            Esp.drawRect(-0.71f, 2.01f, -0.58f, 2.0f, new Color(0, 0, 0, 255).getRGB());
            Esp.drawRect(-0.71f, 2.01f, -0.7f, 0.01f, new Color(0, 0, 0, 255).getRGB());
            Esp.drawRect(-0.59f, 0.01f, -0.58f, 2.0f, new Color(0, 0, 0, 255).getRGB());
            Esp.drawRect(-0.58f, 0.01f, -0.71f, 0.0f, new Color(0, 0, 0, 255).getRGB());
            Esp.a(-0.5, 2.0, 0.5, 0.0, 2.5f, new Color(255, 125, 0, 255).getRGB(), new Color(255, 0, 0, 255).getRGB());
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    @Override
    public void onRenderWorldLast(float f) {
        if (Esp.mc.player == null || Esp.mc.player.isDead) {
            return;
        }
        List list = Esp.mc.world.loadedEntityList.stream().filter(entity -> entity != Esp.mc.player).filter(entity -> !entity.isDead).sorted(Comparator.comparing(entity -> Float.valueOf(Esp.mc.player.getDistance(entity)))).collect(Collectors.toList());
        for (Entity entity2 : list) {
            double d;
            double d2;
            double d3;
            if (entity2 == null || entity2 == Esp.mc.player) continue;
            if (this.invisibleSet.isEnabled() && entity2.isInvisible() && (entity2 instanceof EntityPlayer && this.playerSet.isEnabled() || entity2 instanceof EntityMob && this.mobSet.isEnabled() || entity2 instanceof EntityAnimal && this.animalSet.isEnabled())) {
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) - Esp.mc.getRenderManager().viewerPosX;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) - Esp.mc.getRenderManager().viewerPosY;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) - Esp.mc.getRenderManager().viewerPosZ;
                this.a(entity2, d3, d2, d);
                this.ab(entity2, d3, d2, d);
                continue;
            }
            if (this.mobSet.isEnabled() && entity2 instanceof EntityMob) {
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) - Esp.mc.getRenderManager().viewerPosX;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) - Esp.mc.getRenderManager().viewerPosY;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) - Esp.mc.getRenderManager().viewerPosZ;
                this.a(entity2, d3, d2, d);
                this.ab(entity2, d3, d2, d);
                continue;
            }
            if (this.animalSet.isEnabled() && entity2 instanceof EntityAnimal) {
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) - Esp.mc.getRenderManager().viewerPosX;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) - Esp.mc.getRenderManager().viewerPosY;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) - Esp.mc.getRenderManager().viewerPosZ;
                this.a(entity2, d3, d2, d);
                this.ab(entity2, d3, d2, d);
                continue;
            }
            if (!this.playerSet.isEnabled() || !(entity2 instanceof EntityPlayer)) continue;
            d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) - Esp.mc.getRenderManager().viewerPosX;
            d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) - Esp.mc.getRenderManager().viewerPosY;
            d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) - Esp.mc.getRenderManager().viewerPosZ;
            this.a(entity2, d3, d2, d);
            this.ab(entity2, d3, d2, d);
        }
    }

    private void ab(Entity entity, double d, double d2, double d3) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d2), (float)((float)d3));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-Esp.mc.getRenderManager().playerViewX), (float)0.0f, (float)0.2f, (float)0.0f);
        GL11.glRotatef((float)Esp.mc.getRenderManager().playerViewY, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        if (((EntityLivingBase)entity).getHealth() <= 20.0f) {
            Esp.drawRect(1.1f, 0.5f, 1.1f, 0.5f, -14880362);
        }
        if (((EntityLivingBase)entity).getHealth() <= 10.0f) {
            Esp.drawRect(1.1f, 2.0f, 1.7f, 2.0f, -196692);
        }
        if (((EntityLivingBase)entity).getHealth() <= 5.0f) {
            Esp.drawRect(1.1f, 0.5f, 1.1f, 0.5f, -63232);
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void a(double d, double d2, double d3, double d4, float f, int n, int n2) {
        d4 = 0.0;
        d3 = 0.5;
        d2 = 2.0;
        d = -0.5;
        Esp.a((float)d, (float)d2, (float)d3, (float)d4, n2);
        float f2 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d, (double)d4);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glVertex2d((double)d3, (double)d2);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d3, (double)d2);
        GL11.glVertex2d((double)d, (double)d4);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        float f5;
        if (f < f3) {
            f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f6, (float)f7, (float)f8, (float)f5);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)f, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public Esp() {
        super("Esp", "draw entity lines", 0, Module$Category.RENDER);
        this.animalSet = new BooleanSetting("Animals", false);
        this.playerSet = new BooleanSetting("Players", true);
        this.invisibleSet = new BooleanSetting("Invisible", true);
        this.settings.add(this.mobSet);
        this.settings.add(this.animalSet);
        this.settings.add(this.playerSet);
        this.settings.add(this.invisibleSet);
    }

    public static void a(float f, float f2, float f3, float f4, int n) {
        f4 = 0.0f;
        f3 = 0.5f;
        f2 = 2.0f;
        f = -0.5f;
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)f6, (float)f7, (float)f8, (float)f5);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)f3, (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }
}


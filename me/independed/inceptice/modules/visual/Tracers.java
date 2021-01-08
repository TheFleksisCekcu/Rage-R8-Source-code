
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Tracers
extends Module {
    @Override
    public void onRenderWorldLast(float f) {
        GL11.glPushMatrix();
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)1.0f);
        for (Entity entity : Tracers.mc.world.loadedEntityList) {
            if (entity == Tracers.mc.player || entity == null || !(entity instanceof EntityPlayer)) continue;
            mc.getRenderViewEntity().getDistance(entity);
            double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - Tracers.mc.getRenderManager().viewerPosX;
            double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - Tracers.mc.getRenderManager().viewerPosY;
            double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - Tracers.mc.getRenderManager().viewerPosZ;
            float f2 = (float)(System.currentTimeMillis() % 2000L) / 1000.0f;
            float cfr_ignored_0 = 0.5f + 0.5f * MathHelper.sin((float)(f2 * (float)Math.PI));
            float cfr_ignored_1 = 0.5f + 0.5f * MathHelper.sin((float)((f2 + 1.3333334f) * (float)Math.PI));
            float cfr_ignored_2 = 0.5f + 0.5f * MathHelper.sin((float)((f2 + 2.6666667f) * (float)Math.PI));
            float cfr_ignored_3 = Tracers.mc.player.getDistance(entity) / 20.0f;
            GL11.glColor4f((float)0.4f, (float)0.6f, (float)1.0f, (float)1.71f);
            Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
            vec3d = vec3d.rotatePitch(-((float)Math.toRadians(Tracers.mc.player.rotationPitch)));
            Vec3d vec3d2 = vec3d.rotateYaw(-((float)Math.toRadians(Tracers.mc.player.rotationYaw)));
            GL11.glBegin((int)2);
            GL11.glVertex3d((double)vec3d2.x, (double)((double)Tracers.mc.player.getEyeHeight() + vec3d2.y), (double)vec3d2.z);
            GL11.glVertex3d((double)d, (double)(d2 + 1.15), (double)d3);
            GL11.glEnd();
        }
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public Tracers() {
        super("Tracers", "draw a line to an entity", 0, Module$Category.RENDER);
    }
}


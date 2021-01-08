
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemEgg
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemLingeringPotion
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemSnowball
 *  net.minecraft.item.ItemSplashPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.BlockUtil;
import me.independed.inceptice.util.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Trajectories
extends Module {
    private transient int BOX = 0;

    @Override
    public void onRenderWorldLast(float f) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        ItemStack itemStack = entityPlayerSP.inventory.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        Item item = itemStack.getItem();
        if (!(item instanceof ItemBow || item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemEnderPearl || item instanceof ItemSplashPotion || item instanceof ItemLingeringPotion || item instanceof ItemFishingRod)) {
            return;
        }
        boolean bl = itemStack.getItem() instanceof ItemBow;
        double d = entityPlayerSP.lastTickPosX + (entityPlayerSP.posX - entityPlayerSP.lastTickPosX) * (double)f - Math.cos((float)Math.toRadians(entityPlayerSP.rotationYaw)) * (double)0.08f;
        double d2 = entityPlayerSP.lastTickPosY + (entityPlayerSP.posY - entityPlayerSP.lastTickPosY) * (double)f + (double)entityPlayerSP.getEyeHeight() - 0.04;
        double d3 = entityPlayerSP.lastTickPosZ + (entityPlayerSP.posZ - entityPlayerSP.lastTickPosZ) * (double)f - Math.sin((float)Math.toRadians(entityPlayerSP.rotationYaw)) * (double)0.08f;
        float f2 = bl ? 1.0f : 0.4f;
        float f3 = (float)Math.toRadians(entityPlayerSP.rotationYaw);
        float f4 = (float)Math.toRadians(entityPlayerSP.rotationPitch);
        double d4 = -Math.sin(f3) * Math.cos(f4) * (double)f2;
        double d5 = -Math.sin(f4) * (double)f2;
        double d6 = Math.cos(f3) * Math.cos(f4) * (double)f2;
        double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
        d4 /= d7;
        d5 /= d7;
        d6 /= d7;
        if (bl) {
            float f5 = (float)(72000 - entityPlayerSP.getItemInUseCount()) / 20.0f;
            if ((f5 = (f5 * f5 + f5 * 2.0f) / 3.0f) > 1.0f || f5 <= 0.1f) {
                f5 = 1.0f;
            }
            d4 *= (double)(f5 *= 3.0f);
            d5 *= (double)f5;
            d6 *= (double)f5;
        } else {
            d4 *= 1.5;
            d5 *= 1.5;
            d6 *= 1.5;
        }
        GL11.glPushAttrib((int)24837);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        double d8 = bl ? 0.005 : (item instanceof ItemPotion ? 0.04 : (item instanceof ItemFishingRod ? 0.015 : 0.003));
        Vec3d vec3d = new Vec3d(entityPlayerSP.posX, entityPlayerSP.posY + (double)entityPlayerSP.getEyeHeight(), entityPlayerSP.posZ);
        boolean bl2 = false;
        boolean bl3 = this.predictHit(vec3d, new Vec3d(d, d2, d3), new Vec3d(d4, d5, d6), d8);
        if (bl3) {
            GL11.glColor4f((float)0.9f, (float)0.2f, (float)0.1f, (float)0.5f);
        } else {
            GL11.glColor4f((float)0.0f, (float)0.9f, (float)0.8f, (float)0.5f);
        }
        GL11.glBegin((int)3);
        int n = 0;
        while (true) {
            if (Minecraft.getMinecraft().world.rayTraceBlocks(vec3d, new Vec3d(d, d2, d3)) != null) {
                if (bl3) {
                    GL11.glColor4f((float)0.3f, (float)0.1f, (float)0.1f, (float)0.5f);
                } else {
                    GL11.glColor4f((float)0.1f, (float)0.3f, (float)0.3f, (float)0.5f);
                }
            } else if (bl3) {
                GL11.glColor4f((float)0.9f, (float)0.2f, (float)0.1f, (float)0.5f);
            } else {
                GL11.glColor4f((float)0.0f, (float)0.9f, (float)0.8f, (float)0.5f);
            }
            GL11.glVertex3d((double)(d - renderManager.viewerPosX), (double)(d2 - renderManager.viewerPosY), (double)(d3 - renderManager.viewerPosZ));
            d += d4 * 0.1;
            d2 += d5 * 0.1;
            d3 += d6 * 0.1;
            d4 *= 0.999;
            d5 *= 0.999;
            d6 *= 0.999;
            d5 -= d8;
            for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (!(entity instanceof EntityLiving) || !entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(new Vec3d(d, d2, d3))) continue;
                bl2 = true;
                break;
            }
            for (Entity entity : Minecraft.getMinecraft().world.playerEntities) {
                if (entity == Minecraft.getMinecraft().player || !entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(new Vec3d(d, d2, d3))) continue;
                bl2 = true;
                break;
            }
            Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(new Vec3d(d, d2, d3))).getBlock();
            if (BlockUtil.isCollidable(block)) break;
            ++n;
        }
        GL11.glEnd();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(d - renderManager.viewerPosX), (double)(d2 - renderManager.viewerPosY), (double)(d3 - renderManager.viewerPosZ));
        GL11.glCallList((int)this.BOX);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public Trajectories() {
        super("Trajectories", "show trajectories of different stuff", 0, Module$Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.BOX = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.BOX, (int)4864);
        RenderUtil.drawSolidBox(new AxisAlignedBB(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5));
        GL11.glEndList();
    }

    @Override
    public void onDisable() {
        GL11.glDeleteLists((int)this.BOX, (int)1);
    }

    boolean predictHit(Vec3d vec3d, Vec3d vec3d2, Vec3d vec3d3, double d) {
        d = 0.003;
        boolean bl = false;
        int n = 0;
        while (true) {
            vec3d2 = vec3d2.add(vec3d3.scale(0.4));
            vec3d3 = new Vec3d(vec3d3.x * 0.996, vec3d3.y * 0.996 - d * 4.0, vec3d3.z * 0.996);
            for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (!(entity instanceof EntityLiving) || !entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(vec3d2)) continue;
                bl = true;
                break;
            }
            for (Entity entity : Minecraft.getMinecraft().world.playerEntities) {
                if (entity == Minecraft.getMinecraft().player || !entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(vec3d2)) continue;
                bl = true;
                break;
            }
            Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(vec3d2)).getBlock();
            if (BlockUtil.isCollidable(block)) break;
            ++n;
        }
        return bl;
    }
}


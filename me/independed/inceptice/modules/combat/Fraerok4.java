
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.combat;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fraerok4
extends Module {
    public Fraerok4() {
        super("TriggerBot", "attack when your mouse on entity", 0, Module$Category.COMBAT);
    }

    private void setRotation(float f, float f2) {
        Fraerok4.mc.player.rotationYaw = f % 360.0f;
        Fraerok4.mc.player.rotationPitch = f2 % 360.0f;
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        Entity entity = Fraerok4.mc.objectMouseOver.entityHit;
        if (entity == null || (double)Fraerok4.mc.player.getDistance(entity) > 3.5 || entity instanceof EntityEnderCrystal || entity.isDead || ((EntityLivingBase)entity).getHealth() <= 0.0f || !(entity instanceof EntityPlayer)) {
            return;
        }
        double d = (entity.rotationYaw + 180.0f) % 360.0f;
        double d2 = Fraerok4.mc.player.rotationYaw % 360.0f;
        if (d < 0.0) {
            d += 360.0;
        }
        if (d2 < 0.0) {
            d2 += 360.0;
        }
        if (Fraerok4.mc.player.getCooledAttackStrength(0.0f) >= 1.0f) {
            Fraerok4.mc.player.rotationYaw = this.getRotations(entity)[0];
            Fraerok4.mc.player.rotationPitch = this.getRotations(entity)[1];
            Fraerok4.mc.playerController.attackEntity((EntityPlayer)Fraerok4.mc.player, entity);
            Fraerok4.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public float[] getRotations(Entity entity) {
        double d = entity.posX + (entity.posX - entity.lastTickPosX) - Fraerok4.mc.player.posX;
        double d2 = entity.posY + (double)entity.getEyeHeight() - Fraerok4.mc.player.posY + (double)Fraerok4.mc.player.getEyeHeight() - 3.5;
        double d3 = entity.posZ + (entity.posZ - entity.lastTickPosZ) - Fraerok4.mc.player.posZ;
        double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        } else if (d > 0.0 && d3 < 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        return new float[]{f, f2};
    }
}


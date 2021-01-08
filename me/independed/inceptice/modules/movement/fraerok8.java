
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class fraerok8
extends Module {
    public static boolean isMoving() {
        return fraerok8.mc.player != null && (fraerok8.mc.player.movementInput.moveForward != 0.0f || fraerok8.mc.player.movementInput.moveStrafe != 0.0f);
    }

    public static void setMoveSpeed(double d) {
        double d2 = fraerok8.mc.player.movementInput.moveForward;
        double d3 = fraerok8.mc.player.movementInput.moveStrafe;
        float f = fraerok8.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            fraerok8.mc.player.motionX = 0.0;
            fraerok8.mc.player.motionZ = 0.0;
        } else {
            if (d2 != 0.0) {
                if (d3 > 0.0) {
                    f += (float)(d2 > 0.0 ? -45 : 45);
                } else if (d3 < 0.0) {
                    f += (float)(d2 > 0.0 ? 45 : -45);
                }
                d3 = 0.0;
                if (d2 > 0.0) {
                    d2 = 1.0;
                } else if (d2 < 0.0) {
                    d2 = -1.0;
                }
            }
            fraerok8.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            fraerok8.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
    }

    public static float getSpeed() {
        return (float)Math.sqrt(fraerok8.mc.player.motionX * fraerok8.mc.player.motionX + fraerok8.mc.player.motionZ * fraerok8.mc.player.motionZ);
    }

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = fraerok8.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static double getDirection() {
        float f = fraerok8.mc.player.rotationYaw;
        if (fraerok8.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (fraerok8.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (fraerok8.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (fraerok8.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (fraerok8.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    public fraerok8() {
        super("NoSlowdown", "you can run using items", 0, Module$Category.MOVEMENT);
    }

    public static double[] forward(double d) {
        float f = Minecraft.getMinecraft().player.movementInput.moveForward;
        float f2 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float f3 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (fraerok8.isMoving() && fraerok8.mc.player.isHandActive()) {
            if (fraerok8.mc.player.onGround) {
                fraerok8.mc.player.jump();
            }
            float f = fraerok8.mc.player.rotationYaw;
            Vec3d vec3d = Vec3d.fromPitchYaw((float)0.0f, (float)f);
            Vec3d vec3d2 = Vec3d.fromPitchYaw((float)0.0f, (float)(f + 90.0f));
            double d = 0.0;
            double d2 = 0.0;
            boolean bl = false;
            if (fraerok8.mc.player.movementInput.forwardKeyDown) {
                d += vec3d.x / 20.0 * 5.6;
                d2 += vec3d.z / 20.0 * 5.6;
                bl = true;
            }
            if (fraerok8.mc.player.movementInput.backKeyDown) {
                d -= vec3d.x / 20.0 * 5.6;
                d2 -= vec3d.z / 20.0 * 5.6;
                bl = true;
            }
            if (fraerok8.mc.player.movementInput.rightKeyDown) {
                d += vec3d2.x / 20.0 * 5.6;
                d2 += vec3d2.z / 20.0 * 5.6;
            }
            if (fraerok8.mc.player.movementInput.leftKeyDown) {
                d -= vec3d2.x / 20.0 * 5.6;
                d2 -= vec3d2.z / 20.0 * 5.6;
            }
            fraerok8.mc.player.motionX = d;
            fraerok8.mc.player.motionZ = d2;
        }
    }
}


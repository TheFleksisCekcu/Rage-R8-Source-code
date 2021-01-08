
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import java.lang.reflect.Field;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class spidiki5
extends Module {
    public NumberSetting speedBlocksPerSec = new NumberSetting("Speed", 5.6, 4.0, 100.0, 0.1);

    public static double getDirection() {
        float f = spidiki5.mc.player.rotationYaw;
        if (spidiki5.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (spidiki5.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (spidiki5.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (spidiki5.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (spidiki5.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    public spidiki5() {
        super("Speed", "faster moving", 0, Module$Category.MOVEMENT);
        this.addSettings(this.speedBlocksPerSec);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (spidiki5.mc.player.onGround && spidiki5.mc.player.moveForward != 0.0f) {
            spidiki5.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
            spidiki5.mc.player.jump();
        }
        float f = spidiki5.mc.player.rotationYaw;
        Vec3d vec3d = Vec3d.fromPitchYaw((float)0.0f, (float)f);
        Vec3d vec3d2 = Vec3d.fromPitchYaw((float)0.0f, (float)(f + 90.0f));
        double d = 0.0;
        double d2 = 0.0;
        boolean bl = false;
        if (spidiki5.mc.player.movementInput.forwardKeyDown) {
            d += vec3d.x / 20.0 * this.speedBlocksPerSec.getValue();
            d2 += vec3d.z / 20.0 * this.speedBlocksPerSec.getValue();
            bl = true;
        }
        if (spidiki5.mc.player.movementInput.backKeyDown) {
            d -= vec3d.x / 20.0 * this.speedBlocksPerSec.getValue();
            d2 -= vec3d.z / 20.0 * this.speedBlocksPerSec.getValue();
            bl = true;
        }
        if (spidiki5.mc.player.movementInput.rightKeyDown) {
            d += vec3d2.x / 20.0 * this.speedBlocksPerSec.getValue();
            d2 += vec3d2.z / 20.0 * this.speedBlocksPerSec.getValue();
        }
        if (spidiki5.mc.player.movementInput.leftKeyDown) {
            d -= vec3d2.x / 20.0 * this.speedBlocksPerSec.getValue();
            d2 -= vec3d2.z / 20.0 * this.speedBlocksPerSec.getValue();
        }
        spidiki5.mc.player.motionX = d;
        spidiki5.mc.player.motionZ = d2;
    }

    public static float getSpeed() {
        return (float)Math.sqrt(spidiki5.mc.player.motionX * spidiki5.mc.player.motionX + spidiki5.mc.player.motionZ * spidiki5.mc.player.motionZ);
    }

    public static void setMoveSpeed(double d) {
        double d2 = spidiki5.mc.player.movementInput.moveForward;
        double d3 = spidiki5.mc.player.movementInput.moveStrafe;
        float f = spidiki5.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            spidiki5.mc.player.motionX = 0.0;
            spidiki5.mc.player.motionZ = 0.0;
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
            spidiki5.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            spidiki5.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
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

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = spidiki5.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static boolean isMoving() {
        return spidiki5.mc.player != null && (spidiki5.mc.player.movementInput.moveForward != 0.0f || spidiki5.mc.player.movementInput.moveStrafe != 0.0f);
    }

    public static void setTimerSpeed(float f) {
        Class<Minecraft> class_ = Minecraft.class;
        Field field = class_.getDeclaredField("timer");
        Minecraft.getMinecraft();
        Object object = null;
        Class<?> class_2 = object.getClass();
        Field field2 = class_2.getDeclaredField("timerSpeed");
    }

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }
}


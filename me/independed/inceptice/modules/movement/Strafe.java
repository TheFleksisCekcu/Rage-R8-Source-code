
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Strafe
extends Module {
    public static void strafe() {
        Strafe.strafe(Strafe.getSpeed());
    }

    public static void strafe(float f) {
        if (!Strafe.isMoving()) {
            return;
        }
        double d = Strafe.getDirection();
        Strafe.mc.player.motionX = -Math.sin(d) * (double)f;
        Strafe.mc.player.motionZ = Math.cos(d) * (double)f;
    }

    public Strafe() {
        super("Strafe", "strafing", 0, Module$Category.MOVEMENT);
    }

    public static float getSpeed() {
        return (float)Math.sqrt(Strafe.mc.player.motionX * Strafe.mc.player.motionX + Strafe.mc.player.motionZ * Strafe.mc.player.motionZ);
    }

    public static double getDirection() {
        float f = Strafe.mc.player.rotationYaw;
        if (Strafe.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (Strafe.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (Strafe.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (Strafe.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (Strafe.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        Strafe.strafe();
    }

    public static boolean isMoving() {
        return Strafe.mc.player != null && (Strafe.mc.player.movementInput.moveForward != 0.0f || Strafe.mc.player.movementInput.moveStrafe != 0.0f);
    }
}


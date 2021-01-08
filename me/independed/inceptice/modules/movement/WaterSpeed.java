
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
import me.independed.inceptice.settings.NumberSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WaterSpeed
extends Module {
    public NumberSetting speedSetting = new NumberSetting("Speed", 1.0, 0.1, 5.0, 0.1);
    public int ticks = 0;

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (WaterSpeed.mc.player.isInWater()) {
            ++this.ticks;
            if (this.ticks == 4) {
                WaterSpeed.setMoveSpeed(this.speedSetting.getValue());
            }
            if (this.ticks >= 5) {
                WaterSpeed.setMoveSpeed(this.speedSetting.getValue());
                this.ticks = 0;
            }
            WaterSpeed.setMoveSpeed(this.speedSetting.getValue());
        }
    }

    public static void setMoveSpeed(double d) {
        double d2 = WaterSpeed.mc.player.movementInput.moveForward;
        double d3 = WaterSpeed.mc.player.movementInput.moveStrafe;
        float f = WaterSpeed.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            WaterSpeed.mc.player.motionX = 0.0;
            WaterSpeed.mc.player.motionZ = 0.0;
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
            WaterSpeed.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            WaterSpeed.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
    }

    public WaterSpeed() {
        super("WaterSpeed", "faster in water", 0, Module$Category.PLAYER);
        this.addSettings(this.speedSetting);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.ticks = 0;
    }
}


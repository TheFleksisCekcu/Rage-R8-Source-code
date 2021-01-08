
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.movement.spidiki5;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoParkour
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (spidiki5.isMoving() && AutoParkour.mc.player.onGround && !AutoParkour.mc.player.isSneaking() && !AutoParkour.mc.gameSettings.keyBindSneak.isKeyDown() && !AutoParkour.mc.gameSettings.keyBindJump.isKeyDown() && AutoParkour.mc.world.getCollisionBoxes((Entity)AutoParkour.mc.player, AutoParkour.mc.player.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001)).isEmpty() && AutoParkour.mc.player.moveForward != 0.0f) {
            AutoParkour.mc.player.jump();
        }
    }

    public AutoParkour() {
        super("AutoParkour", "automatically parkour", 0, Module$Category.MOVEMENT);
    }
}


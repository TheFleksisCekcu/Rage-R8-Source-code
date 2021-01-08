
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
import me.independed.inceptice.modules.movement.LongJump;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HighJump
extends Module {
    public HighJump() {
        super("HighJump", "higher jump", 0, Module$Category.MOVEMENT);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (HighJump.mc.player.hurtTime > 0 && HighJump.mc.gameSettings.keyBindJump.isPressed()) {
            HighJump.mc.player.motionY += 0.9736375212669373;
        }
        if (HighJump.mc.player.hurtTime > 0 && HighJump.mc.gameSettings.keyBindForward.isKeyDown()) {
            LongJump.setMoveSpeed(0.431237);
        }
    }
}


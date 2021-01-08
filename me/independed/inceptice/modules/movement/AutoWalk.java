
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
import me.independed.inceptice.modules.movement.InventoryMove;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoWalk
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        InventoryMove.setPressed(AutoWalk.mc.gameSettings.keyBindForward, true);
    }

    @Override
    public void onDisable() {
        InventoryMove.setPressed(AutoWalk.mc.gameSettings.keyBindForward, false);
        super.onDisable();
    }

    public AutoWalk() {
        super("AutoWalk", "auto walk ok", 0, Module$Category.MOVEMENT);
    }
}


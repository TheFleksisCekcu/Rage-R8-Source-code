
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GodMode
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (GodMode.mc.player.getHealth() <= 0.0f) {
            GodMode.mc.player.setHealth(GodMode.mc.player.getMaxHealth());
            if (GodMode.mc.currentScreen instanceof GuiGameOver) {
                GodMode.mc.currentScreen = null;
            }
        }
    }

    public GodMode() {
        super("GodMode", "Makes you invisible and invincible when you die on a vanilla server.", 0, Module$Category.PLAYER);
    }
}


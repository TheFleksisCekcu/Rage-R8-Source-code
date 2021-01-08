/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Blink
extends Module {
    private EntityOtherPlayerMP oldPlayer;

    @Override
    public void onEnable() {
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
    }

    public Blink() {
        super("Blink", "Simulates lag. For other players it looks like you are teleporting.", 0, Module$Category.PLAYER);
    }

    @Override
    public void onDisable() {
    }
}


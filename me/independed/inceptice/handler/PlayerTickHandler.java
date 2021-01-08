
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.handler;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerTickHandler {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (playerTickEvent.phase != TickEvent.Phase.START) {
            return;
        }
        EntityPlayer entityPlayer = playerTickEvent.player;
        if (entityPlayer == Minecraft.getMinecraft().player) {
            for (Module module : ModuleManager.getModuleList()) {
                if (!module.isToggled()) continue;
                module.onLocalPlayerUpdate();
            }
        }
    }
}


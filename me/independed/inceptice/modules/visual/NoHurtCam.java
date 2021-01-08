
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoHurtCam
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        NoHurtCam.mc.player.hurtTime = 0;
        NoHurtCam.mc.player.hurtResistantTime = 0;
        NoHurtCam.mc.player.maxHurtResistantTime = 0;
        NoHurtCam.mc.player.maxHurtTime = 0;
    }

    public NoHurtCam() {
        super("NoHurtCam", "disables hurt effect", 0, Module$Category.RENDER);
    }
}


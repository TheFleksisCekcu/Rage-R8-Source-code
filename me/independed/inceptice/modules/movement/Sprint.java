
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
import me.independed.inceptice.settings.ModeSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint
extends Module {
    public ModeSetting modeSetting = new ModeSetting("Mode", "Simple", "Simple", "Rage");

    @Override
    public void onDisable() {
        super.onDisable();
        Sprint.mc.player.setSprinting(false);
    }

    public Sprint() {
        super("Sprint", "auto runs", 50, Module$Category.MOVEMENT);
        this.addSettings(this.modeSetting);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Sprint.mc.player == null || Sprint.mc.world == null) {
            return;
        }
        if (this.modeSetting.activeMode == "Simple") {
            if (Sprint.mc.player.moveForward > 0.0f && !Sprint.mc.player.isSneaking()) {
                Sprint.mc.player.setSprinting(true);
            }
        } else {
            Sprint.mc.player.setSprinting(true);
        }
    }
}


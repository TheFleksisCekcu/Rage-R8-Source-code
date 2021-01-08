
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import java.util.Random;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Derp
extends Module {
    private final Random e = new Random();

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        float f = this.e.nextFloat() * 360.0f;
        float cfr_ignored_0 = this.e.nextFloat() * 180.0f - 90.0f;
        Derp.mc.player.rotationYawHead = f;
        Derp.mc.player.renderYawOffset = f;
    }

    public Derp() {
        super("Derp", "rotating like bitch", 0, Module$Category.PLAYER);
    }
}


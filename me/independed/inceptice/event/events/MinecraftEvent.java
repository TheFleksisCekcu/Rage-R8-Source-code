
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package me.independed.inceptice.event.events;

import me.independed.inceptice.event.events.MinecraftEvent$Era;
import me.zero.alpine.bus.type.Cancellable;
import net.minecraft.client.Minecraft;

public class MinecraftEvent
extends Cancellable {
    Minecraft mc = Minecraft.getMinecraft();
    private MinecraftEvent$Era era = MinecraftEvent$Era.PRE;
    private final float partialTicks = this.mc.getRenderPartialTicks();

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public MinecraftEvent$Era getEra() {
        return this.era;
    }

    public MinecraftEvent() {
    }

    public MinecraftEvent(MinecraftEvent$Era minecraftEvent$Era) {
        this.era = minecraftEvent$Era;
    }
}


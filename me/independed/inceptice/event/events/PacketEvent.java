/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package me.independed.inceptice.event.events;

import me.independed.inceptice.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent
extends Event {
    private final Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}


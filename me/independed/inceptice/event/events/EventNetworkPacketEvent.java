/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package me.independed.inceptice.event.events;

import me.independed.inceptice.event.events.MinecraftEvent;
import net.minecraft.network.Packet;

public class EventNetworkPacketEvent
extends MinecraftEvent {
    public Packet m_Packet;

    public EventNetworkPacketEvent(Packet packet) {
        this.m_Packet = packet;
    }

    public Packet getPacket() {
        return this.m_Packet;
    }

    public Packet GetPacket() {
        return this.m_Packet;
    }
}


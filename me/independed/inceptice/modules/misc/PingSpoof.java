
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketKeepAlive
 */
package me.independed.inceptice.modules.misc;

import java.util.function.Predicate;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.zero.alpine.bus.listener.EventHandler;
import me.zero.alpine.bus.listener.Listener;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketKeepAlive;

public class PingSpoof
extends Module {
    @EventHandler
    private final Listener receiveListener = new Listener(eventNetworkPacketEvent -> {
        if (eventNetworkPacketEvent.getPacket() instanceof CPacketKeepAlive) {
            System.out.println("hellobitches111111111111");
            eventNetworkPacketEvent.cancel();
        }
    }, new Predicate[0]);
    @EventHandler
    private final Listener sendListener = new Listener(eventNetworkPacketEvent -> {
        if (eventNetworkPacketEvent.getPacket() instanceof CPacketKeepAlive) {
            System.out.println("hellobitches2222222222222");
            eventNetworkPacketEvent.cancel();
            PingSpoof.mc.player.connection.sendPacket((Packet)new CPacketKeepAlive());
        }
    }, new Predicate[0]);

    public PingSpoof() {
        super("PingSpoof", "decrease your ping for the server", 0, Module$Category.WORLD);
    }
}


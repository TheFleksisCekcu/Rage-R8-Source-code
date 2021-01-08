
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoFall
extends Module {
    public NoFall() {
        super("NoFall", "disable fall damage", 50, Module$Category.PLAYER);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (NoFall.mc.player.fallDistance > 3.0f) {
            NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
            NoFall.mc.player.onGround = true;
            NoFall.mc.player.motionY = -0.48876887;
            NoFall.mc.player.fallDistance = 0.0f;
        }
    }
}


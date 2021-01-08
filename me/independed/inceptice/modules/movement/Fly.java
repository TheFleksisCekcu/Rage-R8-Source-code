
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fly
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Fly.mc.gameSettings.keyBindJump.isPressed()) {
            Fly.mc.player.motionY = 0.413213;
            double d = Fly.mc.player.posX;
            double d2 = Fly.mc.player.posY;
            double d3 = Fly.mc.player.posZ;
            Fly.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(d, d2 + 0.413213, d3, Fly.mc.player.rotationYaw, Fly.mc.player.rotationPitch, true));
            Fly.mc.player.setPositionAndRotationDirect(d, d2 + 0.413213, d3, Fly.mc.player.rotationYaw, Fly.mc.player.rotationPitch, 1, false);
        }
    }

    public Fly() {
        super("Fly", "you can fly", 0, Module$Category.MOVEMENT);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}



/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
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

public class AirWalk
extends Module {
    @Override
    public void onDisable() {
        super.onDisable();
        AirWalk.mc.player.onGround = false;
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        AirWalk.mc.player.onGround = true;
        if (AirWalk.mc.player.ticksExisted % 3 == 1) {
            AirWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AirWalk.mc.player.posX, AirWalk.mc.player.posY, AirWalk.mc.player.posZ, true));
        }
    }

    public AirWalk() {
        super("AirWalk", "walking in air", 0, Module$Category.MOVEMENT);
    }
}


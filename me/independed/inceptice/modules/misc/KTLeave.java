
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.misc;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KTLeave
extends Module {
    private double toAdd;

    @Override
    public void onEnable() {
        super.onEnable();
        this.toAdd = KTLeave.mc.player.posY + 100.0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        KTLeave.mc.player.noClip = false;
    }

    public KTLeave() {
        super("KTLeave", "100 blocks upper", 0, Module$Category.WORLD);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (KTLeave.mc.player.posY >= this.toAdd - 5.0 && KTLeave.mc.player.posY <= this.toAdd + 5.0) {
            KTLeave.mc.player.noClip = true;
            KTLeave.mc.player.motionY = 0.0;
            return;
        }
        KTLeave.mc.player.noClip = false;
        KTLeave.mc.player.setPosition(KTLeave.mc.player.posX, this.toAdd, KTLeave.mc.player.posZ);
        KTLeave.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(KTLeave.mc.player.posX, this.toAdd, KTLeave.mc.player.posZ, true));
    }
}


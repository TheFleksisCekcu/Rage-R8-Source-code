
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemBow
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.combat;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.item.ItemBow;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BowSpam
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (BowSpam.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && BowSpam.mc.player.isHandActive() && (double)BowSpam.mc.player.getItemInUseMaxCount() >= 2.4) {
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowSpam.mc.player.getHorizontalFacing()));
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(BowSpam.mc.player.getActiveHand()));
            BowSpam.mc.player.stopActiveHand();
        }
    }

    public BowSpam() {
        super("BowSpam", "spamming arrows", 0, Module$Category.COMBAT);
    }
}



/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.misc;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.BooleanSetting;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FastUse
extends Module {
    public BooleanSetting bow;
    public BooleanSetting xpBottle = new BooleanSetting("xpBottle", true);

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.bow.isEnabled() && FastUse.mc.player.isHandActive() && FastUse.mc.player.getItemInUseMaxCount() >= 3 && (FastUse.mc.player.getHeldItemMainhand().getItem() == Items.BOW || FastUse.mc.player.getHeldItemOffhand().getItem() == Items.BOW)) {
            FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, FastUse.mc.player.getHorizontalFacing()));
            FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(FastUse.mc.player.getActiveHand()));
            FastUse.mc.player.stopActiveHand();
        }
    }

    public FastUse() {
        super("FastUse", "use items faster", 0, Module$Category.WORLD);
        this.bow = new BooleanSetting("bow", true);
        this.addSettings(this.xpBottle, this.bow);
    }
}


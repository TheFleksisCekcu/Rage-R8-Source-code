
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Nuker
extends Module {
    public NumberSetting Nukerradius = new NumberSetting("Radius", 3.0, 1.0, 20.0, 1.0);

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        for (int i = (int)(-this.Nukerradius.getValue()); i < (int)this.Nukerradius.getValue(); ++i) {
            for (int j = (int)this.Nukerradius.getValue() + 1; j > (int)(-this.Nukerradius.getValue()) + 1; --j) {
                for (int k = (int)(-this.Nukerradius.getValue()); k < (int)this.Nukerradius.getValue(); ++k) {
                    double d = Nuker.mc.player.posX + (double)i;
                    double d2 = Nuker.mc.player.posY + (double)j;
                    double d3 = Nuker.mc.player.posZ + (double)k;
                    BlockPos blockPos = new BlockPos(d, d2, d3);
                    Block block = Nuker.mc.world.getBlockState(blockPos).getBlock();
                    if (block == Block.getBlockFromName((String)"Air")) continue;
                    Nuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.NORTH));
                    Nuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.NORTH));
                }
            }
        }
        super.onUpdate();
    }

    public Nuker() {
        super("Nuker", "breaks blocks in radius", 0, Module$Category.PLAYER);
        this.addSettings(this.Nukerradius);
    }
}


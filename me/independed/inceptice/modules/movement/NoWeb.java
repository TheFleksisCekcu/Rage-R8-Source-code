
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.movement.LongJump;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoWeb
extends Module {
    public NoWeb() {
        super("NoWeb", "fast walk in webs", 0, Module$Category.MOVEMENT);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        BlockPos blockPos = new BlockPos(NoWeb.mc.player.posX, NoWeb.mc.player.posY, NoWeb.mc.player.posZ);
        BlockPos blockPos2 = new BlockPos(NoWeb.mc.player.posX, NoWeb.mc.player.posY + 1.0, NoWeb.mc.player.posZ);
        if ((NoWeb.mc.world.getBlockState(blockPos).getBlock() == Blocks.WEB || NoWeb.mc.world.getBlockState(blockPos2).getBlock() == Blocks.WEB) && NoWeb.mc.gameSettings.keyBindForward.isKeyDown()) {
            LongJump.setMoveSpeed(0.25);
        }
    }
}


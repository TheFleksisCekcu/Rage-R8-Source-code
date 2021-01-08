
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Teleport
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            int n = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getX();
            int n2 = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getY();
            int n3 = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getZ();
            Minecraft.getMinecraft().player.setPosition((double)n, (double)(n2 + 4), (double)n3);
        }
    }

    public Teleport() {
        super("Teleport", "teleport you on block that you are looking at", 0, Module$Category.PLAYER);
    }
}


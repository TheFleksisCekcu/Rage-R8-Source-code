
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FreeCam
extends Module {
    private double[] oldPosition;
    private EntityOtherPlayerMP freecamPlayer = null;

    public FreeCam() {
        super("FreeCam", "allows you to move out your body", 0, Module$Category.RENDER);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent playerTickEvent) {
        FreeCam.mc.player.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        FreeCam.mc.player.setPositionAndRotation(this.oldPosition[0], this.oldPosition[1], this.oldPosition[2], FreeCam.mc.player.rotationYaw, FreeCam.mc.player.rotationPitch);
        FreeCam.mc.world.removeEntityFromWorld(-420);
        this.freecamPlayer = null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.clonePositions();
        this.freecamPlayer.copyLocationAndAnglesFrom((Entity)FreeCam.mc.player);
        this.freecamPlayer.noClip = true;
        this.freecamPlayer.rotationYawHead = FreeCam.mc.player.rotationYawHead;
        FreeCam.mc.world.addEntityToWorld(-420, (Entity)this.freecamPlayer);
    }

    private void clonePositions() {
        this.oldPosition = new double[]{FreeCam.mc.player.posX, FreeCam.mc.player.posY, FreeCam.mc.player.posZ};
    }
}


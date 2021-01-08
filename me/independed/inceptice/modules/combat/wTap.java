
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.independed.inceptice.modules.combat;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class wTap
extends Module {
    public wTap() {
        super("wTap", "automatically push enemy", 0, Module$Category.COMBAT);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onAttack(AttackEntityEvent attackEntityEvent) {
        if (attackEntityEvent.getTarget() instanceof EntityLivingBase) {
            if (wTap.mc.player.isSprinting()) {
                wTap.mc.player.setSprinting(false);
            }
            wTap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)wTap.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            wTap.mc.player.setSprinting(true);
        }
    }
}


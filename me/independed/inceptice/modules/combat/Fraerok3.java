
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.independed.inceptice.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.combat.EntitySize;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fraerok3
extends Module {
    public NumberSetting widthSet = new NumberSetting("Size", 2.0, 1.0, 10.0, 0.1);

    public void setEntityBoundingBoxSize(EntityPlayer entityPlayer, float f, float f2) {
        EntitySize entitySize = this.getEntitySize(entityPlayer);
        entityPlayer.width = entitySize.width;
        entityPlayer.height = entitySize.height;
        double d = (double)f / 2.0;
        entityPlayer.setEntityBoundingBox(new AxisAlignedBB(entityPlayer.posX - d, entityPlayer.posY, entityPlayer.posZ - d, entityPlayer.posX + d, entityPlayer.posY + (double)f2, entityPlayer.posZ + d));
    }

    public void setHitBoxForEntities() {
        if (Fraerok3.mc.player == null || Fraerok3.mc.player.isDead) {
            return;
        }
        List list = Fraerok3.mc.world.loadedEntityList.stream().filter(entity -> entity != Fraerok3.mc.player).filter(entity -> Fraerok3.mc.player.getDistance(entity) <= 200.0f).filter(entity -> !entity.isDead).filter(entity -> entity instanceof EntityPlayer).sorted(Comparator.comparing(entity -> Float.valueOf(Fraerok3.mc.player.getDistance(entity)))).collect(Collectors.toList());
        for (Entity entity2 : list) {
            float f = (float)this.widthSet.getValue();
            float f2 = (float)this.widthSet.getValue();
            this.setEntityBoundingBoxSize((EntityPlayer)entity2, f, f2);
        }
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
        this.setHitBoxForEntities();
    }

    public Fraerok3() {
        super("H1tBox", "bigger players range attack", 0, Module$Category.COMBAT);
        this.addSettings(this.widthSet);
    }

    public EntitySize getEntitySize(EntityPlayer entityPlayer) {
        EntitySize entitySize = new EntitySize(0.6f, 1.8f);
        return entitySize;
    }

    public boolean check(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayerSP) {
            return false;
        }
        if (entityLivingBase == Fraerok3.mc.player) {
            return false;
        }
        if (entityLivingBase.isDead) {
            return false;
        }
        if (entityLivingBase.getHealth() < 0.0f) {
            return false;
        }
        return entityLivingBase instanceof EntityPlayer;
    }
}


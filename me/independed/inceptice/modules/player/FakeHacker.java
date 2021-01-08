
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.RotationUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FakeHacker
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (FakeHacker.mc.player == null || FakeHacker.mc.player.isDead) {
            return;
        }
        List list = FakeHacker.mc.world.loadedEntityList.stream().filter(entity -> entity != FakeHacker.mc.player).filter(entity -> FakeHacker.mc.player.getDistance(entity) <= 5.0f).filter(entity -> !entity.isDead).filter(this::attackCheck).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> Float.valueOf(FakeHacker.mc.player.getDistance(entity)))).collect(Collectors.toList());
        if (list.size() > 0) {
            EntityPlayer entityPlayer = (EntityPlayer)list.get(0);
            float[] arrf = FakeHacker.getFacePosEntityRemote((EntityLivingBase)entityPlayer, (Entity)FakeHacker.mc.player);
            entityPlayer.rotationYawHead = arrf[0] - 35.0f;
            entityPlayer.rotationPitch = arrf[1] - 90.0f;
            entityPlayer.setSneaking(true);
        }
    }

    public FakeHacker() {
        super("HackerMaker", "makes other players hackers", 0, Module$Category.PLAYER);
    }

    public static float[] getFacePosEntityRemote(EntityLivingBase entityLivingBase, Entity entity) {
        if (entity == null) {
            return new float[]{entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch};
        }
        return FakeHacker.getFacePosRemote(new Vec3d(entityLivingBase.posX, entityLivingBase.posY + (double)entity.getEyeHeight(), entityLivingBase.posZ), new Vec3d(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ));
    }

    public boolean attackCheck(Entity entity) {
        return entity instanceof EntityPlayer && ((EntityPlayer)entity).getHealth() > 0.0f && Math.abs(FakeHacker.mc.player.rotationYaw - RotationUtils.getNeededRotations((EntityLivingBase)entity)[0]) % 180.0f < 190.0f && !entity.isInvisible() && !entity.getUniqueID().equals(FakeHacker.mc.player.getUniqueID());
    }

    public static float[] getFacePosRemote(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.x - vec3d.x;
        double d2 = vec3d2.y - vec3d.y;
        double d3 = vec3d2.z - vec3d.z;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        float f = (float)(Math.atan2(d3, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d2, d4) * 180.0 / Math.PI));
        return new float[]{MathHelper.wrapDegrees((float)f), MathHelper.wrapDegrees((float)f2)};
    }
}


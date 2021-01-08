
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockHopper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.independed.inceptice.modules.movement;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.movement.spidiki5;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class fsjahfk4
extends Module {
    public ModeSetting modeSetting;
    public NumberSetting speedSet = new NumberSetting("Speed", 0.5, 0.3, 5.0, 0.1);

    @SubscribeEvent
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (fsjahfk4.mc.player != null) {
            fsjahfk4.mc.player.noClip = true;
            fsjahfk4.mc.player.motionY = 0.0;
            fsjahfk4.mc.player.onGround = false;
            fsjahfk4.mc.player.capabilities.isFlying = false;
            if (this.modeSetting.activeMode == "Simple") {
                if (fsjahfk4.mc.gameSettings.keyBindJump.isKeyDown()) {
                    fsjahfk4.mc.player.motionY += this.speedSet.getValue();
                }
                if (fsjahfk4.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    fsjahfk4.mc.player.motionY -= this.speedSet.getValue();
                }
            } else {
                EntityPlayerSP entityPlayerSP;
                float f = fsjahfk4.mc.player.rotationYaw;
                if (fsjahfk4.mc.player.moveForward < 0.0f) {
                    f += 180.0f;
                }
                if (fsjahfk4.mc.player.moveStrafing > 0.0f) {
                    f -= 90.0f * (fsjahfk4.mc.player.moveForward < 0.0f ? -0.5f : (fsjahfk4.mc.player.moveForward > 0.0f ? 0.5f : 1.0f));
                }
                if (fsjahfk4.mc.player.moveStrafing < 0.0f) {
                    f += 90.0f * (fsjahfk4.mc.player.moveForward < 0.0f ? -0.5f : (fsjahfk4.mc.player.moveForward > 0.0f ? 0.5f : 1.0f));
                }
                double cfr_ignored_0 = Math.cos(Math.toRadians(f + 90.0f)) * 1.273197475E-314;
                double cfr_ignored_1 = Math.sin(Math.toRadians(f + 90.0f)) * 1.273197475E-314;
                double d = (double)fsjahfk4.mc.player.getHorizontalFacing().getDirectionVec().getX() * 1.273197475E-15;
                double d2 = (double)fsjahfk4.mc.player.getHorizontalFacing().getDirectionVec().getZ() * 1.273197475E-15;
                fsjahfk4.mc.player.motionY = 0.0;
                if (fsjahfk4.mc.gameSettings.keyBindJump.isKeyDown()) {
                    entityPlayerSP = fsjahfk4.mc.player;
                    entityPlayerSP.motionY += 4.24399158E-15;
                }
                if (fsjahfk4.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    entityPlayerSP = fsjahfk4.mc.player;
                    entityPlayerSP.motionY -= 4.24399158E-15;
                }
                if (fsjahfk4.mc.player.collidedHorizontally) {
                    fsjahfk4.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(fsjahfk4.mc.player.posX, fsjahfk4.mc.player.posY + 1.273197475E-14, fsjahfk4.mc.player.posZ, true));
                    fsjahfk4.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(fsjahfk4.mc.player.posX + d * 0.0, fsjahfk4.mc.player.posY, fsjahfk4.mc.player.posZ + d2 * 0.0, true));
                    fsjahfk4.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(fsjahfk4.mc.player.posX, fsjahfk4.mc.player.posY, fsjahfk4.mc.player.posZ, true));
                }
            }
        }
    }

    @Override
    public void onDisable() {
        fsjahfk4.mc.player.noClip = false;
        fsjahfk4.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(fsjahfk4.mc.player.posX, fsjahfk4.mc.player.getEntityBoundingBox().minY, fsjahfk4.mc.player.posZ, fsjahfk4.mc.player.cameraYaw, fsjahfk4.mc.player.cameraPitch, fsjahfk4.mc.player.onGround));
        super.onDisable();
    }

    public static boolean isInsideBlock() {
        for (int i = MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().minX); i < MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().maxX) + 1; ++i) {
            for (int j = MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().minY); j < MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().maxY) + 1; ++j) {
                for (int k = MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().minZ); k < MathHelper.floor((double)Minecraft.getMinecraft().player.getEntityBoundingBox().maxZ) + 1; ++k) {
                    Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, j, k)).getBlock();
                    if (block == null || block instanceof BlockAir) continue;
                    AxisAlignedBB axisAlignedBB = block.getCollisionBoundingBox(Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, j, k)), (IBlockAccess)Minecraft.getMinecraft().world, new BlockPos(i, j, k));
                    if (block instanceof BlockHopper) {
                        axisAlignedBB = new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
                    }
                    if (axisAlignedBB == null || !Minecraft.getMinecraft().player.getEntityBoundingBox().intersects(axisAlignedBB)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public fsjahfk4() {
        super("NoClip", "no clips", 0, Module$Category.MOVEMENT);
        this.modeSetting = new ModeSetting("Mode", "Simple", "Simple", "Test");
        this.addSettings(this.speedSet, this.modeSetting);
    }

    public static void setMoveSpeedAris(double d) {
        double d2 = Minecraft.getMinecraft().player.movementInput.moveForward;
        double d3 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        double d4 = Minecraft.getMinecraft().player.rotationYaw;
        if (d2 != 0.0 || d3 != 0.0) {
            if (d2 != 0.0) {
                if (d3 > 0.0) {
                    d4 += d2 > 0.0 ? -45.0 : 45.0;
                } else if (d3 < 0.0) {
                    d4 += d2 > 0.0 ? 45.0 : -45.0;
                }
                d3 = 0.0;
                if (d2 > 0.0) {
                    d2 = 1.0;
                } else if (d2 < 0.0) {
                    d2 = -1.0;
                }
            }
            if (spidiki5.isMoving()) {
                fsjahfk4.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(d4 + 88.0)) + d3 * d * Math.sin(Math.toRadians(d4 + (double)87.9f));
                fsjahfk4.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(d4 + 88.0)) - d3 * d * Math.cos(Math.toRadians(d4 + (double)87.9f));
            }
        }
    }
}


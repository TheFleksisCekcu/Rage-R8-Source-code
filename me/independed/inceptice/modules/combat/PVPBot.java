
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package me.independed.inceptice.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.RotationUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PVPBot
extends Module {
    private int hitDelayTimer = 625;
    private long curTimeHit;
    private Random random = new Random();
    private double posX;
    private long curTimeRotate;
    private int rotateTimer = 2000;

    @Override
    public void onEnable() {
        super.onEnable();
        this.posX = PVPBot.mc.player.posX;
    }

    public PVPBot() {
        super("PVPBot", "automatically pvp", 0, Module$Category.COMBAT);
        this.curTimeHit = System.currentTimeMillis();
        this.curTimeRotate = System.currentTimeMillis();
    }

    public static float[] getRotations(Entity entity) {
        double d = entity.posX + (entity.posX - entity.lastTickPosX) - PVPBot.mc.player.posX;
        double d2 = entity.posY + (double)entity.getEyeHeight() - PVPBot.mc.player.posY + (double)PVPBot.mc.player.getEyeHeight() - 3.5;
        double d3 = entity.posZ + (entity.posZ - entity.lastTickPosZ) - PVPBot.mc.player.posZ;
        double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        } else if (d > 0.0 && d3 < 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        return new float[]{f, f2};
    }

    @SideOnly(value=Side.SERVER)
    @SubscribeEvent
    @SideOnly(value=Side.SERVER)
    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup cameraSetup) {
        block21: {
            if (PVPBot.mc.player == null || PVPBot.mc.player.isDead) {
                return;
            }
            List list = PVPBot.mc.world.loadedEntityList.stream().filter(entity -> entity != PVPBot.mc.player).filter(entity -> (double)PVPBot.mc.player.getDistance(entity) <= 3.5).filter(entity -> !entity.isDead).filter(this::attackCheck).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> Float.valueOf(PVPBot.mc.player.getDistance(entity)))).collect(Collectors.toList());
            if (list.size() <= 0) break block21;
            float[] arrf = PVPBot.getRotations((Entity)((EntityLivingBase)list.get(0)));
            arrf[0] = arrf[0] + (float)this.random.nextInt(30) * 0.1f;
            arrf[1] = arrf[1] + (float)this.random.nextInt(60) * 0.1f;
            float f = arrf[0] - 180.0f;
            float f2 = arrf[1];
            PVPBot.mc.player.renderYawOffset = f - 180.0f;
            PVPBot.mc.player.rotationYawHead = f - 180.0f;
            if (f >= 0.0f) {
                if (cameraSetup.getYaw() < f) {
                    while (cameraSetup.getYaw() < f) {
                        cameraSetup.setYaw(cameraSetup.getYaw() + (float)this.random.nextInt(99) * 0.001f);
                    }
                } else {
                    while (cameraSetup.getYaw() > f) {
                        cameraSetup.setYaw(cameraSetup.getYaw() - (float)this.random.nextInt(99) * 0.001f);
                    }
                }
            } else if (cameraSetup.getYaw() < f) {
                while (cameraSetup.getYaw() < f) {
                    cameraSetup.setYaw(cameraSetup.getYaw() + (float)this.random.nextInt(99) * 0.001f);
                }
            } else {
                while (cameraSetup.getYaw() > f) {
                    cameraSetup.setYaw(cameraSetup.getYaw() - (float)this.random.nextInt(99) * 0.001f);
                }
            }
            if (f2 >= 0.0f) {
                if (cameraSetup.getPitch() < f2) {
                    while (cameraSetup.getPitch() < f2) {
                        cameraSetup.setPitch(cameraSetup.getPitch() + (float)this.random.nextInt(99) * 0.001f);
                    }
                } else {
                    while (cameraSetup.getPitch() > f2) {
                        cameraSetup.setPitch(cameraSetup.getPitch() - (float)this.random.nextInt(99) * 0.001f);
                    }
                }
            } else if (cameraSetup.getPitch() < f2) {
                while (cameraSetup.getPitch() < f2) {
                    cameraSetup.setPitch(cameraSetup.getPitch() + (float)this.random.nextInt(99) * 0.001f);
                }
            } else {
                while (cameraSetup.getPitch() > f2) {
                    cameraSetup.setPitch(cameraSetup.getPitch() - (float)this.random.nextInt(99) * 0.001f);
                }
            }
        }
    }

    private void setRotation(float f, float f2, EntityPlayer entityPlayer) {
        PVPBot.mc.player.renderYawOffset = f;
        PVPBot.mc.player.rotationYawHead = f;
        if (f >= 0.0f) {
            if (PVPBot.mc.player.rotationYaw < f) {
                while (PVPBot.mc.player.rotationYaw < f) {
                    PVPBot.mc.player.rotationYaw = (float)((double)PVPBot.mc.player.rotationYaw + (double)this.random.nextInt(99) * 1.0E-4);
                }
            } else {
                while (PVPBot.mc.player.rotationYaw > f) {
                    PVPBot.mc.player.rotationYaw = (float)((double)PVPBot.mc.player.rotationYaw - (double)this.random.nextInt(99) * 1.0E-4);
                }
            }
        } else if (PVPBot.mc.player.rotationYaw < f) {
            while (PVPBot.mc.player.rotationYaw < f) {
                PVPBot.mc.player.rotationYaw = (float)((double)PVPBot.mc.player.rotationYaw + (double)this.random.nextInt(99) * 1.0E-4);
            }
        } else {
            while (PVPBot.mc.player.rotationYaw > f) {
                PVPBot.mc.player.rotationYaw = (float)((double)PVPBot.mc.player.rotationYaw - (double)this.random.nextInt(99) * 1.0E-4);
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        KeyBinding.setKeyBindState((int)PVPBot.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)false);
    }

    public boolean attackCheck(Entity entity) {
        return entity instanceof EntityPlayer && ((EntityPlayer)entity).getHealth() > 0.0f && Math.abs(PVPBot.mc.player.rotationYaw - RotationUtils.getNeededRotations((EntityLivingBase)entity)[0]) % 180.0f < 190.0f && !entity.isInvisible() && !entity.getUniqueID().equals(PVPBot.mc.player.getUniqueID());
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        List list;
        if (PVPBot.mc.player == null || PVPBot.mc.player.isDead) {
            return;
        }
        KeyBinding.setKeyBindState((int)PVPBot.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
        if (PVPBot.mc.player.onGround) {
            KeyBinding.setKeyBindState((int)PVPBot.mc.gameSettings.keyBindJump.getKeyCode(), (boolean)true);
        }
        if ((list = PVPBot.mc.world.loadedEntityList.stream().filter(entity -> entity != PVPBot.mc.player).filter(entity -> PVPBot.mc.player.getDistance(entity) <= 50.0f).filter(entity -> !entity.isDead).filter(this::attackCheck).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> Float.valueOf(PVPBot.mc.player.getDistance(entity)))).collect(Collectors.toList())).size() > 0) {
            float[] arrf = PVPBot.getRotations((Entity)list.get(0));
            if (PVPBot.mc.player.getDistance((Entity)list.get(0)) > 7.0f && System.currentTimeMillis() - this.curTimeRotate >= (long)this.rotateTimer) {
                this.setRotation(arrf[0], arrf[1], (EntityPlayer)list.get(0));
                this.curTimeRotate = System.currentTimeMillis();
            }
            if ((double)PVPBot.mc.player.getDistance((Entity)list.get(0)) <= 3.5 && System.currentTimeMillis() - this.curTimeHit >= (long)this.hitDelayTimer) {
                this.setRotation(arrf[0], arrf[1], (EntityPlayer)list.get(0));
                PVPBot.mc.playerController.attackEntity((EntityPlayer)PVPBot.mc.player, (Entity)list.get(0));
                PVPBot.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.curTimeHit = System.currentTimeMillis();
            }
        }
    }
}


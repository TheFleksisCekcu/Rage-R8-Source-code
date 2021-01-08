
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.BooleanSetting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Particles
extends Module {
    public BooleanSetting redStoneSet;
    public BooleanSetting cloudSet;
    public BooleanSetting portalSet;
    public BooleanSetting heartSet = new BooleanSetting("Heart", false);
    public BooleanSetting fireWorkSet;
    public BooleanSetting smokeSet;
    public BooleanSetting spitSet;
    public BooleanSetting flameSett;

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.heartSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.HEART, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.cloudSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.CLOUD, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.flameSett.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.FLAME, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.smokeSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.redStoneSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.1, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.3, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.4, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.5, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.fireWorkSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, Particles.mc.player.posX, Particles.mc.player.posY + 0.5, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.portalSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (this.spitSet.isEnabled()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SPIT, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    public Particles() {
        super("Particles", "spawn particles", 0, Module$Category.RENDER);
        this.cloudSet = new BooleanSetting("Cloud", false);
        this.flameSett = new BooleanSetting("Flame", true);
        this.smokeSet = new BooleanSetting("Smoke", false);
        this.redStoneSet = new BooleanSetting("RedStone", false);
        this.fireWorkSet = new BooleanSetting("FireWork", false);
        this.portalSet = new BooleanSetting("Portal", false);
        this.spitSet = new BooleanSetting("Spit", false);
        this.addSettings(this.heartSet, this.cloudSet, this.flameSett, this.smokeSet, this.redStoneSet, this.fireWorkSet, this.portalSet, this.spitSet);
    }
}


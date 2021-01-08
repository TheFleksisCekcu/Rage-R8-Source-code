
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityDonkey
 *  net.minecraft.world.World
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.world.World;

public class FakePlayer
extends Module {
    private EntityDonkey RidingEntity;
    private EntityOtherPlayerMP Original;

    @Override
    public void onEnable() {
        if (FakePlayer.mc.world == null) {
            this.toggle();
            return;
        }
        this.Original = new EntityOtherPlayerMP((World)FakePlayer.mc.world, FakePlayer.mc.player.getGameProfile());
        this.Original.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.Original.rotationYaw = FakePlayer.mc.player.rotationYaw;
        this.Original.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        this.Original.inventory.copyInventory(FakePlayer.mc.player.inventory);
        FakePlayer.mc.world.addEntityToWorld(-1048575, (Entity)this.Original);
        if (FakePlayer.mc.player.isRiding() && FakePlayer.mc.player.getRidingEntity() instanceof EntityDonkey) {
            EntityDonkey entityDonkey = (EntityDonkey)FakePlayer.mc.player.getRidingEntity();
            this.RidingEntity = new EntityDonkey((World)FakePlayer.mc.world);
            this.RidingEntity.copyLocationAndAnglesFrom((Entity)entityDonkey);
            this.RidingEntity.setChested(entityDonkey.hasChest());
            FakePlayer.mc.world.addEntityToWorld(-1048574, (Entity)this.RidingEntity);
            this.Original.startRiding((Entity)this.RidingEntity, true);
        }
    }

    public FakePlayer() {
        super("FakePlayer", "second player", 0, Module$Category.PLAYER);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.Original != null) {
            if (this.Original.isRiding()) {
                this.Original.dismountRidingEntity();
            }
            FakePlayer.mc.world.removeEntity((Entity)this.Original);
        }
        if (this.RidingEntity != null) {
            FakePlayer.mc.world.removeEntity((Entity)this.RidingEntity);
        }
    }
}


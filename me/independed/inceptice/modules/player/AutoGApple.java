
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.item.ItemAppleGold
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoGApple
extends Module {
    private int oldSlot = -1;
    public NumberSetting needHealth = new NumberSetting("Health", 11.5, 1.0, 24.0, 0.5);
    private boolean eating = false;

    boolean isFood(ItemStack itemStack) {
        return !AutoGApple.isNullOrEmptyStack(itemStack) && itemStack.getItem() instanceof ItemAppleGold;
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if ((double)(AutoGApple.mc.player.getHealth() + AutoGApple.mc.player.getAbsorptionAmount()) > this.needHealth.getValue() && this.eating) {
            this.eating = false;
            this.stop();
            return;
        }
        if (!this.canEat()) {
            return;
        }
        if (this.isFood(AutoGApple.mc.player.getHeldItemOffhand()) && (double)AutoGApple.mc.player.getHealth() <= this.needHealth.getValue() && this.canEat()) {
            KeyBinding.setKeyBindState((int)AutoGApple.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
            this.eating = true;
        }
        if (!this.canEat()) {
            this.stop();
        }
    }

    public static boolean isNullOrEmptyStack(ItemStack itemStack) {
        return itemStack == null || itemStack.isEmpty();
    }

    public boolean canEat() {
        Entity entity;
        return AutoGApple.mc.objectMouseOver == null || !((entity = AutoGApple.mc.objectMouseOver.entityHit) instanceof EntityVillager) && !(entity instanceof EntityTameable);
    }

    void stop() {
        KeyBinding.setKeyBindState((int)AutoGApple.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
    }

    public AutoGApple() {
        super("AutoApple", "eat golden apples automatically", 0, Module$Category.PLAYER);
        this.settings.add(this.needHealth);
    }
}


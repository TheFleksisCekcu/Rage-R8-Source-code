
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemSplashPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.NonNullList
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.combat;

import java.util.ArrayList;
import java.util.Random;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoPotion
extends Module {
    private long curTime;
    private long delay = 20000L;
    private ArrayList slots = new ArrayList();
    private Random random;

    public boolean isUnUsed(int n) {
        n = 0;
        for (Integer n2 : this.slots) {
            if (n != n2) continue;
            return false;
        }
        return true;
    }

    public void setDownPitch() {
        float f = AutoPotion.mc.player.rotationYaw;
        float f2 = 90.0f;
        if (f >= 0.0f) {
            if (AutoPotion.mc.player.rotationYaw < f) {
                while (AutoPotion.mc.player.rotationYaw < f) {
                    AutoPotion.mc.player.rotationYaw = (float)((double)AutoPotion.mc.player.rotationYaw + (double)this.random.nextInt(99) * 1.0E-4);
                }
            } else {
                while (AutoPotion.mc.player.rotationYaw > f) {
                    AutoPotion.mc.player.rotationYaw = (float)((double)AutoPotion.mc.player.rotationYaw - (double)this.random.nextInt(99) * 1.0E-4);
                }
            }
        } else if (AutoPotion.mc.player.rotationYaw < f) {
            while (AutoPotion.mc.player.rotationYaw < f) {
                AutoPotion.mc.player.rotationYaw = (float)((double)AutoPotion.mc.player.rotationYaw + (double)this.random.nextInt(99) * 1.0E-4);
            }
        } else {
            while (AutoPotion.mc.player.rotationYaw > f) {
                AutoPotion.mc.player.rotationYaw = (float)((double)AutoPotion.mc.player.rotationYaw - (double)this.random.nextInt(99) * 1.0E-4);
            }
        }
        Float.compare(f2, 0.0f);
        if (AutoPotion.mc.player.rotationPitch < f2) {
            while (AutoPotion.mc.player.rotationPitch < f2) {
                AutoPotion.mc.player.rotationPitch = (float)((double)AutoPotion.mc.player.rotationPitch + (double)this.random.nextInt(99) * 1.0E-4);
            }
        } else {
            while (AutoPotion.mc.player.rotationPitch > f2) {
                AutoPotion.mc.player.rotationPitch = (float)((double)AutoPotion.mc.player.rotationPitch - (double)this.random.nextInt(99) * 1.0E-4);
            }
        }
    }

    public AutoPotion() {
        super("AutoPotion", "throws potion automatically", 0, Module$Category.COMBAT);
        this.curTime = System.currentTimeMillis();
        this.random = new Random();
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        NonNullList nonNullList = Minecraft.getMinecraft().player.inventory.mainInventory;
        if (!(AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)1)) && AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)12)) && AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)5)))) {
            return;
        }
        if (!AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = 0;
            while (true) {
                if (((ItemStack)nonNullList.get(n)).getItem() instanceof ItemSplashPotion && this.isUnUsed(n)) {
                    this.slots.add(n);
                    return;
                }
                ++n;
            }
        }
        if (!AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)12))) {
            int n = 0;
            while (true) {
                if (((ItemStack)nonNullList.get(n)).getItem() instanceof ItemSplashPotion && this.isUnUsed(n)) {
                    this.slots.add(n);
                    return;
                }
                ++n;
            }
        }
        if (!AutoPotion.mc.player.isPotionActive(Potion.getPotionById((int)5))) {
            int n = 0;
            while (true) {
                if (((ItemStack)nonNullList.get(n)).getItem() instanceof ItemSplashPotion && this.isUnUsed(n)) {
                    this.slots.add(n);
                    return;
                }
                ++n;
            }
        }
        int n = 0;
        while (true) {
            if (((ItemStack)nonNullList.get(n)).getItem() instanceof ItemSplashPotion) {
                this.slots.add(n);
            }
            ++n;
        }
    }
}


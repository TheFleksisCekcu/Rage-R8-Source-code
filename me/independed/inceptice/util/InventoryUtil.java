
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketCreativeInventoryAction
 */
package me.independed.inceptice.util;

import me.independed.inceptice.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;

public class InventoryUtil {
    public static void updateSlot(int n, ItemStack itemStack) {
        Minecraft.getMinecraft().getConnection().sendPacket((Packet)new CPacketCreativeInventoryAction(n, itemStack));
    }

    public static void updateFirstEmptySlot(ItemStack itemStack) {
        boolean bl = false;
        int n = 0;
        while (true) {
            if (Minecraft.getMinecraft().player.inventory.getStackInSlot(n).isEmpty()) break;
            ++n;
        }
        bl = true;
        ChatUtil.warning("Could not find empty slot. Operation has been aborted.");
    }
}


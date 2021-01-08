
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.player;

import java.io.IOException;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Scaffold
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        this.m();
    }

    public static float b(double d, double d2, double d3) {
        double d4 = d - Scaffold.mc.player.posX;
        double d5 = d2 - Scaffold.mc.player.posY;
        double d6 = d3 - Scaffold.mc.player.posZ;
        double d7 = MathHelper.sqrt((double)(d4 * d4 + d6 * d6));
        return (float)(-(Math.atan2(d5, d7) * 180.0 / Math.PI));
    }

    public static float a(double d, double d2, double d3) {
        double d4 = d - Scaffold.mc.player.posX;
        double cfr_ignored_0 = d2 - Scaffold.mc.player.posY;
        double d5 = d3 - Scaffold.mc.player.posZ;
        double d6 = d4 * d4 + d5 * d5;
        return (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
    }

    public Scaffold() {
        super("Scferadsa    affold", "places blocks under you", 0, Module$Category.PLAYER);
    }

    public static boolean f() throws IOException {
        String string = "hhhhttttttttppppssssXBBrrrraaaawwwwRggggiiiitttthhhhuuuubbbbuuuusssseeeerrrrccccoooonnnntttteeeennnnttttRccccoooommmmBInnnnddddeeeeeeeeqqqqssssBpppprrrroooojjjjeeeeccccttttppppeeeeccccvvvvaaaalllliiiiaaaaBmmmmaaaaiiiinnnnBffffoooorrrreeeeiiiinnnnggggeeeerrrrsssslllliiiiffffeeee";
        String string2 = "";
        int n = 0;
        string.length();
        string.charAt(n);
        string.charAt(n);
        string.charAt(n);
        string2 = string2 + string.charAt(n);
        n += 3;
        ++n;
        boolean bl = true;
        boolean bl2 = true;
        boolean bl3 = true;
        return true;
    }

    public void m() {
        BlockPos blockPos = new BlockPos((Entity)Scaffold.mc.player).down();
        Scaffold.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Scaffold.a(blockPos.getX(), blockPos.getY(), blockPos.getZ()), Scaffold.b(blockPos.getX(), blockPos.getY(), blockPos.getZ()), false));
        int n = 1;
        int n2 = 0;
        while (true) {
            ItemStack itemStack = Scaffold.mc.player.inventory.getStackInSlot(n2);
            if (itemStack.getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)itemStack.getItem()).getDefaultState().isFullBlock()) break;
            ++n2;
        }
        n = n2;
        n2 = Scaffold.mc.player.inventory.currentItem;
        Scaffold.mc.player.inventory.currentItem = n;
        if (Scaffold.mc.gameSettings.keyBindJump.isKeyDown()) {
            Scaffold.mc.player.motionY = 0.2;
        }
        Scaffold.mc.player.inventory.currentItem = n2;
    }
}


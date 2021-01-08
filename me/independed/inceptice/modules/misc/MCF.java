
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.misc;

import me.independed.inceptice.friends.FriendManager;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.ChatUtil;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MCF
extends Module {
    TimerUtil timerUtil = new TimerUtil();

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.entityHit instanceof EntityPlayer && MCF.mc.gameSettings.keyBindPickBlock.isKeyDown() && this.timerUtil.hasReached(100.0)) {
            String string = Minecraft.getMinecraft().objectMouseOver.entityHit.getName();
            if (!FriendManager.isFriend(string)) {
                FriendManager.addFriend(string, string);
                ChatUtil.sendChatMessage("Added " + string + " as a friend.");
                this.timerUtil.reset();
                return;
            }
            if (this.timerUtil.hasReached(100.0)) {
                FriendManager.removeFriend(string);
                ChatUtil.sendChatMessage("Removed " + string + ".");
                this.timerUtil.reset();
            }
        }
    }

    public MCF() {
        super("MCF", "middle click friends", 0, Module$Category.WORLD);
    }
}


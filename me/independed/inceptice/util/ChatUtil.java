
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package me.independed.inceptice.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {
    public static void sendChatMessage(String string) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a79Rage R8: \u00a77" + string));
    }

    public static void error(String string) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a78\u00a7l[\u00a73\u00a7lRage R8:\u00a78\u00a7l] \u00a7c\u00a7lERROR: \u00a7c" + string));
    }

    public static void info(String string) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a78\u00a7l[\u00a73\u00a7lRage R8:\u00a78\u00a7l] \u00a7e" + string));
    }

    public static void warning(String string) {
        string = "Could not find empty slot. Operation has been aborted.";
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a78\u00a7l[\u00a73\u00a7lRage R8:\u00a78\u00a7l] \u00a7b\u00a7lWARNING: \u00a7b" + string));
    }

    public static void clear(int n) {
        for (int i = 0; i < n; ++i) {
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(""));
        }
    }

    public static void syntax(String string) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a78\u00a7l[\u00a73\u00a7lRage R8:\u00a78\u00a7l] \u00a7a\u00a7lSYNTAX: \u00a7a" + string));
    }
}


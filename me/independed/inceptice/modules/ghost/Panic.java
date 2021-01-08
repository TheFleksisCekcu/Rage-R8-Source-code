
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.ghost;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Panic
extends Module {
    public static Hud hud;
    public static Setting setting;
    public static ModuleManager moduleManager;

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        for (Module module : ModuleManager.getModuleList()) {
            if (module.isToggled()) {
                module.toggle();
            }
            module.setKey(0);
        }
        Panic.mc.player.jump();
        mc.displayGuiScreen(null);
    }

    public Panic() {
        super("Panic", "disable all active modules.(you will can't enable them after using this before restart)", 0, Module$Category.GHOST);
    }
}


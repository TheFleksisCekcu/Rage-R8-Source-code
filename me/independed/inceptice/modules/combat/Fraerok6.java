
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.combat;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fraerok6
extends Module {
    public NumberSetting numberSetting = new NumberSetting("KnockBack", 0.0, 0.0, 5.0, 0.1);

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Fraerok6.mc.player.hurtTime > 0 && !ModuleManager.getModuleByName("TargetStrafe").isToggled()) {
            Fraerok6.mc.player.motionX = this.numberSetting.getValue();
            Fraerok6.mc.player.motionZ = this.numberSetting.getValue();
        }
    }

    public Fraerok6() {
        super("Velocity", "make your knockback less", 0, Module$Category.COMBAT);
        this.addSettings(this.numberSetting);
    }
}


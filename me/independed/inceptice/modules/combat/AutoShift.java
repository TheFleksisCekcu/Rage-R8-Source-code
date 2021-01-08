
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.combat;

import java.lang.reflect.Field;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.NumberSetting;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoShift
extends Module {
    TimerUtil timer = new TimerUtil();
    NumberSetting delayShifting = new NumberSetting("Delay", 150.0, 30.0, 500.0, 10.0);

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.timer.hasReached(this.delayShifting.getValue()) && AutoShift.mc.player.isSneaking()) {
            AutoShift.resetPressed(AutoShift.mc.gameSettings.keyBindSneak);
            this.timer.reset();
        }
    }

    public static void resetPressed(KeyBinding keyBinding) {
        AutoShift.setPressed(keyBinding, GameSettings.isKeyDown((KeyBinding)keyBinding));
    }

    public static void setPressed(KeyBinding keyBinding, boolean bl) {
        Field field = keyBinding.getClass().getDeclaredField("pressed");
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent attackEntityEvent) {
        AutoShift.setPressed(AutoShift.mc.gameSettings.keyBindSneak, true);
        this.timer.reset();
    }

    public AutoShift() {
        super("AutoShift", "presses shift when you hit entity", 0, Module$Category.COMBAT);
        this.addSettings(this.delayShifting);
    }
}


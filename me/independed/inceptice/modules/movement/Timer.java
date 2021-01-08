
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.movement;

import java.lang.reflect.Field;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Timer
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        Timer.setTimerSpeed(0.01f);
    }

    public Timer() {
        super("Timer", "make your game faster", 0, Module$Category.MOVEMENT);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Timer.setTimerSpeed(1.0f);
    }

    public static void setTimerSpeed(float f) {
        Class<Minecraft> class_ = Minecraft.class;
        Field field = class_.getDeclaredField("timer");
        Minecraft.getMinecraft();
        Object object = null;
        Class<?> class_2 = object.getClass();
        Field field2 = class_2.getDeclaredField("tickLength");
    }
}


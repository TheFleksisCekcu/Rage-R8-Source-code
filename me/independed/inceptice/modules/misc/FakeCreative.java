
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.GameType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package me.independed.inceptice.modules.misc;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FakeCreative
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (FakeCreative.mc.playerController.getCurrentGameType() == GameType.CREATIVE) {
            return;
        }
        FakeCreative.mc.playerController.setGameType(GameType.CREATIVE);
    }

    public FakeCreative() {
        super("FakeCreative", "actually fake creative, you can beat further(reach)", 0, Module$Category.WORLD);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        FakeCreative.mc.playerController.setGameType(GameType.SURVIVAL);
    }
}


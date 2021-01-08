
/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.player;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;

public class AirStack
extends Module {
    @Override
    public void onDisable() {
        AirStack.mc.player.isDead = false;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (AirStack.mc.world != null && AirStack.mc.player != null) {
            AirStack.mc.player.isDead = true;
        }
    }

    public AirStack() {
        super("AirStuck", "AirStuck", 0, Module$Category.PLAYER);
    }
}


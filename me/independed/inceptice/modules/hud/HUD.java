/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.hud;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;

public class HUD
extends Module {
    @Override
    public void onEnable() {
        this.toggled = true;
    }

    public HUD() {
        super("HUD", "shows toggled hacks", 0, Module$Category.HUD);
        this.toggle();
    }

    @Override
    public void onDisable() {
        this.toggled = false;
    }
}


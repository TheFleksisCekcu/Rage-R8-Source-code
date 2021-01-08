/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.hud;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;

public class KeyStrokes
extends Module {
    public KeyStrokes() {
        super("KeyStrokes", "keys that you are pressing", 0, Module$Category.HUD);
        this.toggle();
    }
}


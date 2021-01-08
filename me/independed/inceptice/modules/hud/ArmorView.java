/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.hud;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;

public class ArmorView
extends Module {
    public ArmorView() {
        super("ArmorView", "show armor", 0, Module$Category.HUD);
        this.toggle();
    }
}


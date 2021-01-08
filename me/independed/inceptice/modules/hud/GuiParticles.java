/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.hud;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;

public class GuiParticles
extends Module {
    public GuiParticles() {
        super("GuiParticles", "show particles in ClickGUI", 0, Module$Category.HUD);
        this.toggle();
    }
}


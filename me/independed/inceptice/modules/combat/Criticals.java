/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules.combat;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.ModeSetting;

public class Criticals
extends Module {
    public ModeSetting modeSettingCriticals = new ModeSetting("Mode", "Legit", "Legit");

    public Criticals() {
        super("Criticals", "auto critical entities", 0, Module$Category.COMBAT);
        this.addSettings(this.modeSettingCriticals);
    }
}


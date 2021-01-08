/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.settings;

import me.independed.inceptice.settings.Setting;

public class BooleanSetting
extends Setting {
    public boolean enabled;

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public void setEnabled(boolean bl) {
        this.enabled = bl;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public BooleanSetting(String string, boolean bl) {
        this.name = string;
        this.enabled = bl;
    }
}


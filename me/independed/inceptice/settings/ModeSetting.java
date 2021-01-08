/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.settings;

import java.util.Arrays;
import java.util.List;
import me.independed.inceptice.settings.Setting;

public class ModeSetting
extends Setting {
    public List modes;
    public int index;
    public String activeMode;

    public void cycle() {
        this.index = this.index < this.modes.size() - 1 ? ++this.index : 0;
        this.activeMode = (String)this.modes.get(this.index);
    }

    public void setMode(String string) {
        this.index = this.modes.indexOf(string);
    }

    public ModeSetting(String string, String string2, String ... arrstring) {
        this.name = string;
        this.modes = Arrays.asList(arrstring);
        this.activeMode = string2;
        this.index = this.modes.indexOf(string2);
    }

    public String getMode() {
        return (String)this.modes.get(this.index);
    }

    public boolean is(String string) {
        return this.index == this.modes.indexOf(string);
    }
}


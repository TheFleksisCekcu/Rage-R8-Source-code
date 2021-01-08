/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.settings;

import me.independed.inceptice.settings.Setting;

public class NumberSetting
extends Setting {
    public double increment;
    public double value;
    public double minimum;
    public double maximum;

    public void setIncrement(double d) {
        this.increment = d;
    }

    public void setValue(double d) {
        double d2 = 1.0 / this.increment;
        this.value = (double)Math.round(Math.max(this.minimum, Math.min(this.maximum, d)) * d2) / d2;
    }

    public double getMaximum() {
        return this.maximum;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public void setMaximum(double d) {
        this.maximum = d;
    }

    public void increment(boolean bl) {
        this.setValue(this.getValue() + (double)(bl ? 1 : -1) * this.increment);
    }

    public double getValue() {
        return this.value;
    }

    public void setMinimum(double d) {
        this.minimum = d;
    }

    public NumberSetting(String string, double d, double d2, double d3, double d4) {
        this.name = string;
        this.value = d;
        this.minimum = d2;
        this.maximum = d3;
        this.increment = d4;
    }

    public double getIncrement() {
        return this.increment;
    }
}


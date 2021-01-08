/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.bus.type;

public class Cancellable {
    private boolean cancelled;

    public final void cancel() {
        this.cancelled = true;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}


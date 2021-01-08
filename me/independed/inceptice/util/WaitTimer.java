/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.util;

public final class WaitTimer {
    public long time = System.nanoTime() / 1000000L;

    public boolean hasTimeElapsed(long l, boolean bl) {
        if (this.getTime() >= l) {
            if (bl) {
                this.reset();
            }
            return true;
        }
        return false;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L - this.time;
    }

    public void reset() {
        this.time = System.nanoTime() / 1000000L;
    }
}


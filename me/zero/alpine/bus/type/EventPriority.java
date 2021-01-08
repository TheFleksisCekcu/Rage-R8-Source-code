/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.bus.type;

public interface EventPriority {
    public static final byte HIGHEST = 1;
    public static final byte HIGH = (byte)2;
    public static final byte MEDIUM;
    public static final byte DEFAULT;
    public static final byte LOW;
    public static final byte LOWEST;

    static {
        LOW = (byte)4;
        LOWEST = (byte)5;
        MEDIUM = (byte)3;
        DEFAULT = (byte)3;
    }
}


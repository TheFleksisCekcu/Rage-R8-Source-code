/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.bus;

public interface EventBus {
    public void post(Object var1);

    public void subscribe(Object var1);

    public void unsubscribe(Iterable var1);

    public void attach(EventBus var1);

    public void unsubscribe(Object ... var1);

    public void unsubscribe(Object var1);

    public void subscribe(Iterable var1);

    public void subscribe(Object ... var1);

    public void detach(EventBus var1);
}


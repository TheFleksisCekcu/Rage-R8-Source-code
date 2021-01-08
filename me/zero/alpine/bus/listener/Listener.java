/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.bus.listener;

import java.util.function.Predicate;
import me.zero.alpine.bus.listener.EventHook;
import net.jodah.typetools.TypeResolver;

public final class Listener
implements EventHook {
    private final Class target;
    private final Predicate[] filters;
    private final byte priority;
    private final EventHook hook;

    @SafeVarargs
    @SafeVarargs
    public Listener(EventHook eventHook, byte by, Predicate ... arrpredicate) {
        by = (byte)3;
        this.hook = eventHook;
        this.priority = by;
        this.target = TypeResolver.resolveRawArgument(EventHook.class, eventHook.getClass());
        this.filters = arrpredicate;
    }

    public final Class getTarget() {
        return this.target;
    }

    @Override
    public final void invoke(Object object) {
        if (this.filters.length > 0) {
            for (Predicate predicate : this.filters) {
                if (predicate.test(object)) continue;
                return;
            }
        }
        this.hook.invoke(object);
    }

    public final byte getPriority() {
        return this.priority;
    }

    @SafeVarargs
    @SafeVarargs
    public Listener(EventHook eventHook, Predicate ... arrpredicate) {
        this(eventHook, 3, arrpredicate);
    }
}


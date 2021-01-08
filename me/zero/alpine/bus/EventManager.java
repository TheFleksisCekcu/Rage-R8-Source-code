/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.bus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.listener.Listener;

public class EventManager
implements EventBus {
    private final Map SUBSCRIPTION_MAP;
    private final List ATTACHED_BUSES;
    private final Map SUBSCRIPTION_CACHE = new HashMap();

    @Override
    public void detach(EventBus eventBus) {
        if (this.ATTACHED_BUSES.contains(eventBus)) {
            this.ATTACHED_BUSES.remove(eventBus);
        }
    }

    @Override
    public void unsubscribe(Object object) {
        List list = (List)this.SUBSCRIPTION_CACHE.get(object);
        if (list != null) {
            this.SUBSCRIPTION_MAP.values().forEach(list2 -> {
                Objects.requireNonNull(list);
                list.getClass();
                list2.removeIf(list::contains);
            });
            if (!this.ATTACHED_BUSES.isEmpty()) {
                this.ATTACHED_BUSES.forEach(eventBus -> eventBus.unsubscribe(object));
            }
        }
    }

    public EventManager() {
        this.SUBSCRIPTION_MAP = new HashMap();
        this.ATTACHED_BUSES = new ArrayList();
    }

    @Override
    public void subscribe(Object ... arrobject) {
        Arrays.stream(arrobject).forEach(this::subscribe);
    }

    @Override
    public void post(Object object) {
        List list = (List)this.SUBSCRIPTION_MAP.get(object.getClass());
        if (list != null) {
            list.forEach(listener -> listener.invoke(object));
        }
        if (!this.ATTACHED_BUSES.isEmpty()) {
            this.ATTACHED_BUSES.forEach(eventBus -> eventBus.post(object));
        }
    }

    private void subscribe(Listener listener) {
        int n;
        List list = this.SUBSCRIPTION_MAP.computeIfAbsent(listener.getTarget(), class_ -> new ArrayList());
        for (n = 0; n < list.size() && listener.getPriority() >= ((Listener)list.get(n)).getPriority(); ++n) {
        }
        list.add(n, listener);
    }

    @Override
    public void unsubscribe(Iterable iterable) {
        iterable.forEach(this::unsubscribe);
    }

    @Override
    public void attach(EventBus eventBus) {
        if (!this.ATTACHED_BUSES.contains(eventBus)) {
            this.ATTACHED_BUSES.add(eventBus);
        }
    }

    private static Listener asListener(Object object, Field field) {
        boolean bl = false;
        Listener listener = null;
        if (listener == null) {
            return null;
        }
        if (listener.getPriority() <= 5 && listener.getPriority() >= 1) {
            return listener;
        }
        throw new RuntimeException("Event Priority out of bounds! %s");
    }

    private static boolean isValidField(Field field) {
        return false;
    }

    @Override
    public void subscribe(Iterable iterable) {
        iterable.forEach(this::subscribe);
    }

    @Override
    public void subscribe(Object object2) {
        List list = this.SUBSCRIPTION_CACHE.computeIfAbsent(object2, object -> Arrays.stream(object.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(field -> EventManager.asListener(object, field)).filter(Objects::nonNull).collect(Collectors.toList()));
        list.forEach(this::subscribe);
        if (!this.ATTACHED_BUSES.isEmpty()) {
            this.ATTACHED_BUSES.forEach(eventBus -> eventBus.subscribe(object2));
        }
    }

    @Override
    public void unsubscribe(Object ... arrobject) {
        Arrays.stream(arrobject).forEach(this::unsubscribe);
    }
}


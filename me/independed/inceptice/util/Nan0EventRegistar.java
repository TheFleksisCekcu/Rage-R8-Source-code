/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.MinecraftDummyContainer
 *  net.minecraftforge.fml.common.ModContainer
 *  net.minecraftforge.fml.common.eventhandler.ASMEventHandler
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 *  net.minecraftforge.fml.common.eventhandler.IEventListener
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package me.independed.inceptice.util;

import com.google.common.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MinecraftDummyContainer;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Nan0EventRegistar {
    public static void register(EventBus eventBus, Object object) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)ReflectionHelper.getPrivateValue(EventBus.class, (Object)eventBus, (String[])new String[]{"listeners"});
        Map map = (Map)ReflectionHelper.getPrivateValue(EventBus.class, (Object)eventBus, (String[])new String[]{"listenerOwners"});
        if (concurrentHashMap.containsKey(object)) {
            return;
        }
        MinecraftDummyContainer minecraftDummyContainer = Loader.instance().getMinecraftModContainer();
        map.put(object, minecraftDummyContainer);
        ReflectionHelper.setPrivateValue(EventBus.class, (Object)eventBus, (Object)map, (String[])new String[]{"listenerOwners"});
        Set set = TypeToken.of(object.getClass()).getTypes().rawTypes();
        for (Method method : object.getClass().getMethods()) {
            for (Class class_ : set) {
                Method method2 = class_.getDeclaredMethod(null, null);
            }
        }
    }

    private static void register(EventBus eventBus, Class class_, Object object, Method method, ModContainer modContainer) {
        int n = (Integer)ReflectionHelper.getPrivateValue(EventBus.class, (Object)eventBus, (String[])new String[]{"busID"});
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)ReflectionHelper.getPrivateValue(EventBus.class, (Object)eventBus, (String[])new String[]{"listeners"});
        Constructor constructor = class_.getConstructor(new Class[0]);
        Object[] cfr_ignored_0 = new Object[0];
        Event event = null;
        ASMEventHandler aSMEventHandler = new ASMEventHandler(object, method, modContainer);
        event.getListenerList().register(n, aSMEventHandler.getPriority(), (IEventListener)aSMEventHandler);
        ArrayList<ASMEventHandler> arrayList = (ArrayList<ASMEventHandler>)concurrentHashMap.get(object);
        if (arrayList == null) {
            arrayList = new ArrayList<ASMEventHandler>();
            concurrentHashMap.put(object, arrayList);
            ReflectionHelper.setPrivateValue(EventBus.class, (Object)eventBus, (Object)concurrentHashMap, (String[])new String[]{"listeners"});
        }
        arrayList.add(aSMEventHandler);
    }
}


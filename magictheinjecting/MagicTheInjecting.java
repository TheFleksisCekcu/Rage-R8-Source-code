/*
 * Decompiled with CFR 0.150.
 */
package magictheinjecting;

import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;

public class MagicTheInjecting
extends Thread {
    public static byte[][] classes;

    private static Class tryGetClass(PrintWriter printWriter, ClassLoader classLoader, String ... arrstring) throws ClassNotFoundException {
        Object var3_3 = null;
        int n = 0;
        String[] arrstring2 = arrstring;
        int n2 = arrstring2.length;
        if (n < n2) {
            String string = arrstring2[n];
            return classLoader.loadClass(string);
        }
        throw var3_3;
    }

    @Override
    public void run() {
        ArrayList arrayList;
        Iterator iterator;
        Object object;
        Serializable serializable;
        Object object2;
        Object object3;
        Object object4;
        String cfr_ignored_0 = System.getProperty("user.home") + null + "eloader-log.txt";
        PrintWriter printWriter = null;
        Object object6 = null;
        for (Thread object52 : Thread.getAllStackTraces().keySet()) {
            if (object52 == null || object52.getContextClassLoader() == null || (object4 = object52.getContextClassLoader()).getClass() == null || object4.getClass().getName() == null) continue;
            object3 = object4.getClass().getName();
            String cfr_ignored_1 = "Thread: " + object52.getName() + " [" + (String)object3 + "]";
            if (!((String)object3).contains("LaunchClassLoader") && !((String)object3).contains("RelaunchClassLoader")) continue;
            object6 = object4;
            break;
        }
        if (object6 == null) {
            throw new Exception("ClassLoader is null");
        }
        this.setContextClassLoader((ClassLoader)object6);
        Class class_ = MagicTheInjecting.tryGetClass(printWriter, object6, new String[]{"cpw.mods.fml.common.Mod$EventHandler", "net.minecraftforge.fml.common.Mod$EventHandler"});
        Class class_2 = MagicTheInjecting.tryGetClass(printWriter, object6, new String[]{"cpw.mods.fml.common.Mod", "net.minecraftforge.fml.common.Mod"});
        object4 = MagicTheInjecting.tryGetClass(printWriter, object6, new String[]{"cpw.mods.fml.common.event.FMLInitializationEvent", "net.minecraftforge.fml.common.event.FMLInitializationEvent"});
        object3 = MagicTheInjecting.tryGetClass(printWriter, (ClassLoader)object6, "cpw.mods.fml.common.event.FMLPreInitializationEvent", "net.minecraftforge.fml.common.event.FMLPreInitializationEvent");
        Method method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
        String cfr_ignored_2 = "Loading " + classes.length + " classes";
        ArrayList<Object[]> arrayList2 = new ArrayList<Object[]>();
        Object object5 = classes;
        int arrobject = ((byte[][])object5).length;
        for (int class_22 = 0; class_22 < arrobject; ++class_22) {
            object2 = object5[class_22];
            if (object2 == null) {
                throw new Exception("classData is null");
            }
            if (object6.getClass() == null) {
                throw new Exception("getClass() is null");
            }
            Object[] arrobject2 = new Object[]{null, object2, 0, ((byte[])object2).length, object6.getClass().getProtectionDomain()};
            serializable = null;
            if (((Class)serializable).getAnnotation(class_2) == null) continue;
            object = new Object[3];
            object[0] = serializable;
            iterator = new ArrayList();
            arrayList = new ArrayList();
            for (Method method2 : ((Class)serializable).getDeclaredMethods()) {
                if (null != null) {
                }
                if (null == null) continue;
            }
            object[1] = iterator;
            object[2] = arrayList;
            arrayList2.add((Object[])object);
        }
        String cfr_ignored_3 = classes.length + " loaded successfully";
        object5 = arrayList2.iterator();
        while (object5.hasNext()) {
            Object[] arrobject3 = (Object[])object5.next();
            Class class_3 = (Class)arrobject3[0];
            object2 = (ArrayList)arrobject3[1];
            serializable = (ArrayList)arrobject3[2];
            object = null;
            String cfr_ignored_4 = "Instancing " + class_3.getName();
            object = class_3.newInstance();
            iterator = object2.iterator();
            while (iterator.hasNext()) {
                arrayList = iterator.next();
                String cfr_ignored_5 = "Preiniting " + arrayList;
                (new Object[1])[0] = null;
            }
            iterator = ((ArrayList)serializable).iterator();
            while (iterator.hasNext()) {
                arrayList = iterator.next();
                String cfr_ignored_6 = "Initing " + arrayList;
                (new Object[1])[0] = null;
            }
        }
    }

    public static int injectCP(byte[][] arrby) {
        classes = arrby;
        MagicTheInjecting magicTheInjecting = new MagicTheInjecting();
        magicTheInjecting.start();
        return 0;
    }

    public static byte[][] getByteArray(int n) {
        return new byte[n][];
    }
}


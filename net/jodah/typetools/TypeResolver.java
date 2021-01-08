/*
 * Decompiled with CFR 0.150.
 */
package net.jodah.typetools;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import me.zero.alpine.bus.listener.EventHook;
import net.jodah.typetools.TypeResolver$1;
import net.jodah.typetools.TypeResolver$Unknown;

public final class TypeResolver {
    private static final Map TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap());
    private static Method GET_CONSTANT_POOL_SIZE;
    private static boolean CACHE_ENABLED;
    private static boolean RESOLVES_LAMBDAS;
    private static final Map OBJECT_METHODS;
    private static Method GET_CONSTANT_POOL;
    private static Method GET_CONSTANT_POOL_METHOD_AT;
    private static final Map PRIMITIVE_WRAPPERS;
    private static final Double JAVA_VERSION;

    public static Class resolveRawArgument(Type type, Class class_) {
        Class[] arrclass = TypeResolver.resolveRawArguments(type, class_);
        if (arrclass == null) {
            return TypeResolver$Unknown.class;
        }
        if (arrclass.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument for generic type " + type + " but found " + arrclass.length);
        }
        return arrclass[0];
    }

    private static int getConstantPoolSize(Object object) {
        Object[] cfr_ignored_0 = new Object[0];
        return (Integer)null;
    }

    private static boolean isDefaultMethod(Method method) {
        block0: {
            if (!(JAVA_VERSION >= 1.8)) break block0;
        }
        return false;
    }

    private static Type reify(Type type, Map map, Map map2) {
        if (type instanceof Class) {
            return type;
        }
        throw new UnsupportedOperationException("Reification of type with name '" + null + "' and class name '" + type.getClass().getName() + "' is not implemented.");
    }

    private TypeResolver() {
    }

    public static void disableCache() {
        TYPE_VARIABLE_CACHE.clear();
        CACHE_ENABLED = false;
    }

    public static Class[] resolveRawArguments(Type type, Class class_) {
        TypeVariable<Class<T>>[] arrtypeVariable;
        Class[] arrclass = null;
        TypeVariable<Class<T>>[] arrtypeVariable2 = null;
        if (RESOLVES_LAMBDAS && class_.isSynthetic()) {
            TypeVariable<Class<T>>[] arrtypeVariable3 = arrtypeVariable = type instanceof Class ? (TypeVariable<Class<T>>[])type : null;
            if (arrtypeVariable != null && arrtypeVariable.isInterface()) {
                arrtypeVariable2 = arrtypeVariable;
            }
        }
        if (type instanceof Class) {
            arrtypeVariable = ((Class)type).getTypeParameters();
            arrclass = new Class[arrtypeVariable.length];
            for (int i = 0; i < arrtypeVariable.length; ++i) {
                arrclass[i] = TypeResolver.resolveRawClass(arrtypeVariable[i], class_, arrtypeVariable2);
            }
        }
        return arrclass;
    }

    public static Type reify(Type type, Class class_) {
        return TypeResolver.reify(type, TypeResolver.getTypeVariableMap(class_, null));
    }

    public static void enableCache() {
        CACHE_ENABLED = true;
    }

    static {
        CACHE_ENABLED = true;
        OBJECT_METHODS = new HashMap();
        JAVA_VERSION = Double.parseDouble(System.getProperty("java.specification.version", "0"));
        Object object = AccessController.doPrivileged(new TypeResolver$1());
        GET_CONSTANT_POOL = Class.class.getDeclaredMethod("getConstantPool", new Class[0]);
        String string = JAVA_VERSION < 9.0 ? "sun.reflect.ConstantPool" : "jdk.internal.reflect.ConstantPool";
        Class<?> class_ = Class.forName(string);
        GET_CONSTANT_POOL_SIZE = class_.getDeclaredMethod("getSize", new Class[0]);
        GET_CONSTANT_POOL_METHOD_AT = class_.getDeclaredMethod("getMethodAt", Integer.TYPE);
        Field field = ((Class)null).getDeclaredField("override");
        long l = 0L;
        Object[] cfr_ignored_0 = new Object[0];
        Object var6_5 = null;
        Object[] cfr_ignored_1 = new Object[0];
        for (Method method : Object.class.getDeclaredMethods()) {
            OBJECT_METHODS.put(null, method);
        }
        RESOLVES_LAMBDAS = true;
        object = new HashMap();
        object.put(Boolean.TYPE, Boolean.class);
        object.put(Byte.TYPE, Byte.class);
        object.put(Character.TYPE, Character.class);
        object.put(Double.TYPE, Double.class);
        object.put(Float.TYPE, Float.class);
        object.put(Integer.TYPE, Integer.class);
        object.put(Long.TYPE, Long.class);
        object.put(Short.TYPE, Short.class);
        object.put(Void.TYPE, Void.class);
        PRIMITIVE_WRAPPERS = Collections.unmodifiableMap(object);
    }

    public static Type reify(Type type) {
        return TypeResolver.reify(type, new HashMap(0));
    }

    public static Type reify(Class class_, Class class_2) {
        return TypeResolver.reify(TypeResolver.resolveGenericType(class_, class_2), TypeResolver.getTypeVariableMap(class_2, null));
    }

    private static Type reify(Type type, Map map) {
        if (type == null) {
            return null;
        }
        if (type instanceof Class) {
            return type;
        }
        return TypeResolver.reify(type, map, new HashMap());
    }

    public static Type resolveGenericType(Class class_, Type type) {
        Type type2;
        Object object;
        Class class_2 = (Class)type;
        if (class_.equals(class_2)) {
            return type;
        }
        if (class_.isInterface()) {
            object = class_2.getGenericInterfaces();
            int n = ((Type[])object).length;
            for (int i = 0; i < n; ++i) {
                Object object2 = object[i];
                if (object2 == null || object2.equals(Object.class) || (type2 = TypeResolver.resolveGenericType(class_, (Type)object2)) == null) continue;
                return type2;
            }
        }
        if ((object = class_2.getGenericSuperclass()) != null && !object.equals(Object.class) && (type2 = TypeResolver.resolveGenericType(class_, (Type)object)) != null) {
            return type2;
        }
        return null;
    }

    public static Class[] resolveRawArguments(Class class_, Class class_2) {
        return TypeResolver.resolveRawArguments(TypeResolver.resolveGenericType(class_, class_2), class_2);
    }

    public static Class resolveRawClass(Type type, Class class_) {
        return TypeResolver.resolveRawClass(type, class_, null);
    }

    private static Map getTypeVariableMap(Class class_, Class class_2) {
        HashMap hashMap;
        Reference reference = (Reference)TYPE_VARIABLE_CACHE.get(class_);
        HashMap hashMap2 = hashMap = reference != null ? (HashMap)reference.get() : null;
        if (hashMap == null) {
            Class<?> class_3;
            hashMap = new HashMap();
            if (class_2 != null) {
                TypeResolver.populateLambdaArgs(class_2, class_, hashMap);
            }
            TypeResolver.populateSuperTypeArgs(class_.getGenericInterfaces(), hashMap, class_2 != null);
            Type type = class_.getGenericSuperclass();
            for (class_3 = class_.getSuperclass(); class_3 != null && !Object.class.equals((Object)class_3); class_3 = class_3.getSuperclass()) {
                TypeResolver.populateSuperTypeArgs(class_3.getGenericInterfaces(), hashMap, false);
                type = class_3.getGenericSuperclass();
            }
            class_3 = class_;
            while (class_3.isMemberClass()) {
                type = class_3.getGenericSuperclass();
                class_3 = class_3.getEnclosingClass();
            }
            if (CACHE_ENABLED) {
                TYPE_VARIABLE_CACHE.put(class_, new WeakReference(hashMap));
            }
        }
        return hashMap;
    }

    private static Member getConstantPoolMethodAt(Object object, int n) {
        (new Object[1])[0] = n;
        return null;
    }

    public static Type resolveBound(TypeVariable typeVariable) {
        Object var1_1 = null;
        if ((var1_1).length == 0) {
            return TypeResolver$Unknown.class;
        }
        void var2_2 = var1_1[0];
        return var2_2 == Object.class ? TypeResolver$Unknown.class : var2_2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isAutoBoxingMethod(Method method) {
        Object var1_1 = null;
        if (!((String)null).equals("valueOf")) return false;
        if ((var1_1).length != 1) return false;
        if (!var1_1[0].isPrimitive()) return false;
        if (!TypeResolver.wrapPrimitives((Class)var1_1[0]).equals(null)) return false;
        return true;
    }

    private static Member getMemberRef(Class class_) {
        Object[] cfr_ignored_0 = new Object[0];
        Object var1_1 = null;
        Member member = null;
        for (int i = TypeResolver.getConstantPoolSize(var1_1) - 1; i >= 0; --i) {
            Member member2 = TypeResolver.getConstantPoolMethodAt(var1_1, i);
            if (member2 == null) continue;
            if (((Class)null).isAssignableFrom(class_)) continue;
            member = member2;
            break;
        }
        return member;
    }

    private static void populateTypeArgs(ParameterizedType parameterizedType, Map map, boolean bl) {
        int n = 0;
        if (null instanceof Class) {
            TypeVariable<Class<T>>[] arrtypeVariable = ((Class)null).getTypeParameters();
            Object var4_5 = null;
            if (null != null) {
                Object i = null;
            }
            for (int i = 0; i < (var4_5).length; ++i) {
                TypeVariable typeVariable = arrtypeVariable[i];
                void var7_9 = var4_5[i];
                if (var7_9 instanceof Class) {
                    map.put(typeVariable, var7_9);
                }
            }
        }
    }

    private static Class wrapPrimitives(Class class_) {
        return class_.isPrimitive() ? (Class)PRIMITIVE_WRAPPERS.get(class_) : class_;
    }

    private static void populateSuperTypeArgs(Type[] arrtype, Map map, boolean bl) {
        boolean bl2 = false;
        for (Type type : arrtype) {
            if (!(type instanceof Class)) continue;
            TypeResolver.populateSuperTypeArgs(((Class)type).getGenericInterfaces(), map, bl2);
        }
    }

    public static Class resolveRawArgument(Class class_, Class class_2) {
        class_ = EventHook.class;
        return TypeResolver.resolveRawArgument(TypeResolver.resolveGenericType(class_, class_2), class_2);
    }

    private static Class resolveRawClass(Type type, Class class_, Class class_2) {
        if (type instanceof Class) {
            return (Class)type;
        }
        return type instanceof Class ? (Class)type : TypeResolver$Unknown.class;
    }

    private static void populateLambdaArgs(Class class_, Class class_2, Map map) {
        if (RESOLVES_LAMBDAS) {
            for (Method method : class_.getMethods()) {
                if (TypeResolver.isDefaultMethod(method)) continue;
                Object v = OBJECT_METHODS.get(null);
                if (v != null) {
                    if (Arrays.equals(null, null)) continue;
                }
                Object var8_8 = null;
                Object var9_9 = null;
                Member member = TypeResolver.getMemberRef(class_2);
                if (member == null) {
                    return;
                }
                Object var11_11 = null;
                boolean bl = false;
                if ((var9_9).length > 0) {
                    void cfr_ignored_0 = var9_9[0];
                }
                int n = 0;
                if ((var9_9).length < (var11_11).length) {
                    n = (var11_11).length - (var9_9).length;
                }
                int n2 = 0;
                while (n2 + n < (var11_11).length) {
                    void cfr_ignored_1 = var9_9[n2];
                    ++n2;
                }
                return;
            }
        }
    }
}


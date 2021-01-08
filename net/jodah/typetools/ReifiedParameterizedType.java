/*
 * Decompiled with CFR 0.150.
 */
package net.jodah.typetools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ReifiedParameterizedType
implements ParameterizedType {
    private final ParameterizedType original;
    private final boolean[] loop;
    private int reified = 0;
    private final Type[] reifiedTypeArguments;

    ReifiedParameterizedType(ParameterizedType parameterizedType) {
        this.original = parameterizedType;
        this.reifiedTypeArguments = new Object[(null).length];
        this.loop = new boolean[(null).length];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return this.reifiedTypeArguments;
    }

    @Override
    public Type getRawType() {
        return null;
    }

    void addReifiedTypeArgument(Type type) {
        if (this.reified >= this.reifiedTypeArguments.length) {
            return;
        }
        if (type == this) {
            this.loop[this.reified] = true;
        }
        this.reifiedTypeArguments[this.reified++] = type;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    public String toString() {
        Type type = this.getOwnerType();
        Type type2 = this.getRawType();
        Type[] arrtype = this.getActualTypeArguments();
        StringBuilder stringBuilder = new StringBuilder();
        if (type != null) {
            if (type instanceof Class) {
                stringBuilder.append(((Class)type).getName());
            } else {
                stringBuilder.append(type.toString());
            }
            stringBuilder.append("$");
            if (type2 instanceof Class) {
                stringBuilder.append(((Class)type2).getSimpleName());
            } else {
                stringBuilder.append((String)null);
            }
        } else {
            stringBuilder.append((String)null);
        }
        if (arrtype != null && arrtype.length > 0) {
            stringBuilder.append("<");
            for (int i = 0; i < arrtype.length; ++i) {
                Type type3 = arrtype[i];
                if (i >= this.reified) {
                    stringBuilder.append("?");
                    continue;
                }
                if (type3 == null) {
                    stringBuilder.append("null");
                    continue;
                }
                if (this.loop[i]) {
                    stringBuilder.append("...");
                    continue;
                }
                stringBuilder.append((String)null);
            }
            stringBuilder.append(">");
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        int n = this.original.hashCode();
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (this.loop[i] || !(this.reifiedTypeArguments[i] instanceof ReifiedParameterizedType)) continue;
            n = 31 * n + this.reifiedTypeArguments[i].hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ReifiedParameterizedType reifiedParameterizedType = (ReifiedParameterizedType)object;
        if (!this.original.equals(reifiedParameterizedType.original)) {
            return false;
        }
        if (this.reifiedTypeArguments.length != reifiedParameterizedType.reifiedTypeArguments.length) {
            return false;
        }
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (this.loop[i] != reifiedParameterizedType.loop[i]) {
                return false;
            }
            if (this.loop[i] || this.reifiedTypeArguments[i] == reifiedParameterizedType.reifiedTypeArguments[i]) continue;
            return false;
        }
        return true;
    }
}


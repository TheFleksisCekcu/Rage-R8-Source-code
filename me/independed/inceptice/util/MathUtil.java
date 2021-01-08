
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 */
package me.independed.inceptice.util;

import net.minecraft.util.math.Vec3d;

public class MathUtil {
    public static Vec3d interpolateVec3d(Vec3d vec3d, Vec3d vec3d2, float f) {
        return vec3d.subtract(vec3d2).scale((double)f).add(vec3d2);
    }
}


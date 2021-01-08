
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.util.math.Vec3d
 */
package me.independed.inceptice.util;

import me.independed.inceptice.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.math.Vec3d;

public class EntityUtil {
    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return MathUtil.interpolateVec3d(entity.getPositionVector(), new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), f);
    }

    public static boolean isAnimal(Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
}


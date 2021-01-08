
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package me.independed.inceptice.util;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class RotationUtils {
    public static float[] getAverageRotations(List list) {
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        for (Entity entity : list) {
            d += entity.posX;
            d2 += entity.getEntityBoundingBox().maxY - 2.0;
            d3 += entity.posZ;
        }
        return new float[]{RotationUtils.getRotationFromPosition(d /= (double)list.size(), d3 /= (double)list.size(), d2 /= (double)list.size())[0], RotationUtils.getRotationFromPosition(d, d3, d2)[1]};
    }

    public static float getDistanceBetweenAngles(float f, float f2) {
        float f3 = Math.abs(f - f2) % 360.0f;
        if (f3 > 180.0f) {
            f3 = 360.0f - f3;
        }
        return f3;
    }

    public static float getTrajAngleSolutionLow(float f, float f2, float f3) {
        float f4 = f3 * f3 * f3 * f3 - 0.006f * (0.006f * (f * f) + 2.0f * f2 * (f3 * f3));
        return (float)Math.toDegrees(Math.atan(((double)(f3 * f3) - Math.sqrt(f4)) / (double)(0.006f * f)));
    }

    public static float[] getRotationFromPosition(double d, double d2, double d3) {
        double d4 = d - Minecraft.getMinecraft().player.posX;
        double d5 = d2 - Minecraft.getMinecraft().player.posZ;
        double d6 = d3 - Minecraft.getMinecraft().player.posY - 0.6;
        double d7 = MathHelper.sqrt((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static float[] getNeededRotations(EntityLivingBase entityLivingBase) {
        double d = entityLivingBase.posX - Minecraft.getMinecraft().player.posX;
        double d2 = entityLivingBase.posZ - Minecraft.getMinecraft().player.posZ;
        double d3 = entityLivingBase.posY + (double)entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY));
        double d4 = MathHelper.sqrt((double)(d * d + d2 * d2));
        float f = (float)(MathHelper.atan2((double)d2, (double)d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(MathHelper.atan2((double)d3, (double)d4) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static float[] getRotations(EntityLivingBase entityLivingBase, String string) {
        if (string == "Head") {
            double d = entityLivingBase.posX;
            double d2 = entityLivingBase.posZ;
            double d3 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f);
            return RotationUtils.getRotationFromPosition(d, d2, d3);
        }
        if (string == "Chest") {
            double d = entityLivingBase.posX;
            double d4 = entityLivingBase.posZ;
            double d5 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f) - 0.75;
            return RotationUtils.getRotationFromPosition(d, d4, d5);
        }
        if (string == "Dick") {
            double d = entityLivingBase.posX;
            double d6 = entityLivingBase.posZ;
            double d7 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f) - 1.2;
            return RotationUtils.getRotationFromPosition(d, d6, d7);
        }
        if (string == "Legs") {
            double d = entityLivingBase.posX;
            double d8 = entityLivingBase.posZ;
            double d9 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f) - 1.5;
            return RotationUtils.getRotationFromPosition(d, d8, d9);
        }
        double d = entityLivingBase.posX;
        double d10 = entityLivingBase.posZ;
        double d11 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f) - 0.5;
        return RotationUtils.getRotationFromPosition(d, d10, d11);
    }

    public static float getNewAngle(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }
}


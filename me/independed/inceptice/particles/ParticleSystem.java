
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.particles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.independed.inceptice.particles.Particle;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ParticleSystem {
    private int dist;
    private List particleList;
    private static final float SPEED = 0.1f;
    private Random random;

    public void addParticles(int n) {
        for (int i = 0; i < n; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }

    public double distance(float f, float f2, float f3, float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }

    public ParticleSystem(int n) {
        n = 350;
        this.dist = 100;
        this.random = new Random();
        this.particleList = new ArrayList();
        this.addParticles(n);
    }

    public void tick(int n) {
        if (Mouse.isButtonDown((int)0)) {
            this.addParticles(1);
        }
        this.particleList.forEach(particle -> particle.tick(n, 0.1f));
    }

    private void drawLine(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f8);
        GL11.glLineWidth((float)0.5f);
        GL11.glBegin((int)1);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glVertex2f((float)f3, (float)f4);
        GL11.glEnd();
    }

    public void render() {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        for (Particle particle : this.particleList) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(particle.getAlpha() / 255.0f));
            GL11.glPointSize((float)particle.getSize());
            GL11.glBegin((int)0);
            GL11.glVertex2f((float)particle.getX(), (float)particle.getY());
            GL11.glEnd();
            int n = Mouse.getEventX() * Minecraft.getMinecraft().currentScreen.width / Minecraft.getMinecraft().displayWidth;
            int n2 = Minecraft.getMinecraft().currentScreen.height - Mouse.getEventY() * Minecraft.getMinecraft().currentScreen.height / Minecraft.getMinecraft().displayHeight - 1;
            float f = 0.0f;
            Particle particle2 = null;
            for (Particle particle3 : this.particleList) {
                float f2 = particle.getDistanceTo(particle3);
                if (f2 > (float)this.dist || this.distance(n, n2, particle.getX(), particle.getY()) > (double)this.dist && this.distance(n, n2, particle3.getX(), particle3.getY()) > (double)this.dist) continue;
                Float.compare(f, 0.0f);
                f = f2;
                particle2 = particle3;
            }
            if (particle2 == null) continue;
            float f3 = Math.min(1.0f, Math.min(1.0f, 1.0f - f / (float)this.dist));
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}


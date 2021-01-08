/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector2f
 *  org.lwjgl.opengl.Display
 */
package me.independed.inceptice.particles;

import java.util.Random;
import javax.vecmath.Vector2f;
import org.lwjgl.opengl.Display;

public class Particle {
    private Vector2f pos;
    private float alpha;
    private static final Random random = new Random();
    private float size;
    private Vector2f velocity;

    public double distance(float f, float f2, float f3, float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }

    public float getAlpha() {
        return this.alpha;
    }

    public Particle(Vector2f vector2f, float f, float f2, float f3) {
        this.velocity = vector2f;
        this.pos = new Vector2f(f, f2);
        this.size = f3;
    }

    public float getDistanceTo(Particle particle) {
        return this.getDistanceTo(particle.getX(), particle.getY());
    }

    public void setX(float f) {
        this.pos.setX(f);
    }

    public void setVelocity(Vector2f vector2f) {
        this.velocity = vector2f;
    }

    public float getX() {
        return this.pos.getX();
    }

    public static Particle generateParticle() {
        Vector2f vector2f = new Vector2f((float)(Math.random() * 2.0 - 1.0), (float)(Math.random() * 2.0 - 1.0));
        float f = random.nextInt(Display.getWidth());
        float f2 = random.nextInt(Display.getHeight());
        float f3 = (float)(Math.random() * 4.0) + 1.0f;
        return new Particle(vector2f, f, f2, f3);
    }

    public void setSize(float f) {
        this.size = f;
    }

    public float getDistanceTo(float f, float f2) {
        return (float)this.distance(this.getX(), this.getY(), f, f2);
    }

    public void tick(int n, float f) {
        f = 0.1f;
        this.pos.x += this.velocity.getX() * (float)n * f;
        this.pos.y += this.velocity.getY() * (float)n * f;
        if (this.alpha < 255.0f) {
            this.alpha += 0.05f * (float)n;
        }
        if (this.pos.getX() > (float)Display.getWidth()) {
            this.pos.setX(0.0f);
        }
        if (this.pos.getX() < 0.0f) {
            this.pos.setX((float)Display.getWidth());
        }
        if (this.pos.getY() > (float)Display.getHeight()) {
            this.pos.setY(0.0f);
        }
        if (this.pos.getY() < 0.0f) {
            this.pos.setY((float)Display.getHeight());
        }
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    public void setY(float f) {
        this.pos.setY(f);
    }

    public float getY() {
        return this.pos.getY();
    }

    public float getSize() {
        return this.size;
    }
}


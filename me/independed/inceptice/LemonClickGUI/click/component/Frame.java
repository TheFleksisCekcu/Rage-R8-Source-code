/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.LemonClickGUI.click.component;

import java.awt.Color;
import java.util.ArrayList;
import me.independed.inceptice.LemonClickGUI.click.ClickGui;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.components.Button;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.ui.Hud;
import org.lwjgl.opengl.GL11;

public class Frame {
    public int dragX;
    private int barHeight;
    private int y;
    public ArrayList components = new ArrayList();
    private int width;
    public Module$Category category;
    public double cntComp = 0.0;
    private boolean open;
    private boolean isDragging;
    private int x;
    public int dragY;

    public void refresh() {
        int n = this.barHeight;
        for (Component component : this.components) {
            component.setOff(n);
            n += component.getHeight();
        }
    }

    public void renderFrame() {
        Hud.drawRoundedRect(this.x, this.y, this.width, this.barHeight, 8.0, ClickGui.color);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Hud.renderer.drawString(this.category.name(), (float)(this.x * 2 + this.getWidth() - Hud.renderer.getStringWidth(this.category.name()) / 2) - 5.0f, ((float)this.y + 2.5f) * 2.0f + 1.0f, new Color(255, 255, 255, 255).getRGB(), true);
        Hud.renderer.drawString(this.open ? ">" : "^", (this.x + this.width - 10) * 2 + 2, ((float)this.y + 2.5f) * 2.0f + 1.0f, new Color(255, 255, 255, 255).getRGB(), true);
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            if (this.components.size() > (int)this.cntComp) {
                int n = 0;
                while ((double)n < this.cntComp) {
                    ((Component)this.components.get(n)).renderComponent();
                    ++n;
                }
                this.cntComp += 0.14;
            } else {
                for (int i = 0; i < this.components.size(); ++i) {
                    ((Component)this.components.get(i)).renderComponent();
                }
            }
        }
    }

    public void setY(int n) {
        this.y = n;
    }

    public ArrayList getComponents() {
        return this.components;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isOpen() {
        return this.open;
    }

    public Frame(Module$Category module$Category) {
        this.category = module$Category;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 20;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int n = this.barHeight;
        for (Module module : ModuleManager.getModulesByCategory(module$Category)) {
            Button button = new Button(module, this, n);
            this.components.add(button);
            n += 16;
        }
    }

    public void setX(int n) {
        this.x = n;
    }

    public void setOpen(boolean bl) {
        this.open = bl = 0;
    }

    public void updatePosition(int n, int n2) {
        if (this.isDragging) {
            this.setX(n - this.dragX);
            this.setY(n2 - this.dragY);
        }
    }

    public boolean isWithinHeader(int n, int n2) {
        return n >= this.x && n <= this.x + this.width && n2 >= this.y && n2 <= this.y + this.barHeight;
    }

    public void setDrag(boolean bl) {
        this.isDragging = bl;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}


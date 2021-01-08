/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.LemonClickGUI.click.component.components.sub;

import java.awt.Color;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.components.Button;
import me.independed.inceptice.ui.Hud;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Keybind
extends Component {
    private Button parent;
    private int offset;
    private int x;
    private boolean hovered;
    private int y;
    private boolean binding;

    public boolean isMouseOnButton(int n, int n2) {
        return n > this.x && n < this.x + 88 && n2 > this.y && n2 < this.y + 16;
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0 && this.parent.open) {
            this.binding = !this.binding;
        }
    }

    public Keybind(Button button, int n) {
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = n;
    }

    @Override
    public void updateComponent(int n, int n2) {
        this.hovered = this.isMouseOnButton(n, n2);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }

    @Override
    public void setOff(int n) {
        this.offset = n;
    }

    @Override
    public void renderComponent() {
        Hud.drawRoundedRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getWidth(), 16.0, 4.0, this.hovered ? new Color(0, 0, 0, 155).getRGB() : new Color(0, 0, 0, 125).getRGB());
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Hud.renderer.drawString(this.binding ? "Press a key..." : "Key: " + Keyboard.getKeyName((int)this.parent.mod.getKey()), (this.parent.parent.getX() + 7) * 2, (this.parent.parent.getY() + this.offset + 2) * 2 - 3, new Color(255, 10, 10, 255).getRGB(), true);
        GL11.glPopMatrix();
    }

    @Override
    public void keyTyped(char c, int n) {
        if (this.binding) {
            this.parent.mod.setKey(n);
            this.binding = false;
        }
    }
}


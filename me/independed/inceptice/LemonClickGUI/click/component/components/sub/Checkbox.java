/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.LemonClickGUI.click.component.components.sub;

import java.awt.Color;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.components.Button;
import me.independed.inceptice.settings.BooleanSetting;
import me.independed.inceptice.ui.Hud;
import org.lwjgl.opengl.GL11;

public class Checkbox
extends Component {
    private boolean hovered;
    private BooleanSetting op;
    private int x;
    private int y;
    private Button parent;
    private int offset;

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0 && this.parent.open) {
            this.op.toggle();
        }
    }

    public Checkbox(BooleanSetting booleanSetting, Button button, int n) {
        this.op = booleanSetting;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = n;
    }

    @Override
    public void setOff(int n) {
        this.offset = n;
    }

    public boolean isMouseOnButton(int n, int n2) {
        return n > this.x && n < this.x + 88 && n2 > this.y && n2 < this.y + 16;
    }

    @Override
    public void updateComponent(int n, int n2) {
        this.hovered = this.isMouseOnButton(n, n2);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }

    @Override
    public void renderComponent() {
        Hud.drawRoundedRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getWidth(), 16.0, 4.0, this.hovered ? new Color(0, 0, 0, 155).getRGB() : new Color(0, 0, 0, 125).getRGB());
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Hud.renderer.drawString(this.op.name, (this.parent.parent.getX() + 10 + 4) * 2 + 5, (this.parent.parent.getY() + this.offset + 2) * 2 - 3, new Color(255, 0, 0, 255).getRGB(), true);
        GL11.glPopMatrix();
        Hud.drawRoundedRect(this.parent.parent.getX() + 3 + 4, this.parent.parent.getY() + this.offset + 3, 6.0, 6.0, 6.0, new Color(255, 255, 255, 255).getRGB());
        if (this.op.isEnabled()) {
            Hud.drawRoundedRect(this.parent.parent.getX() + 4 + 4, this.parent.parent.getY() + this.offset + 4, 4.0, 4.0, 6.0, new Color(255, 0, 0, 255).getRGB());
        }
    }
}


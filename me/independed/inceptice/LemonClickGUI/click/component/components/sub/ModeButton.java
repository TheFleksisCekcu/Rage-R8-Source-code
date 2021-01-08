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
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;
import org.lwjgl.opengl.GL11;

public class ModeButton
extends Component {
    private int y;
    private boolean hovered;
    private int x;
    private Button parent;
    private Module mod;
    private int offset;

    @Override
    public void updateComponent(int n, int n2) {
        this.hovered = this.isMouseOnButton(n, n2);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }

    public boolean isMouseOnButton(int n, int n2) {
        return n > this.x && n < this.x + 88 && n2 > this.y && n2 < this.y + 16;
    }

    public ModeButton(Button button, Module module, int n) {
        this.parent = button;
        this.mod = module;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = n;
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0 && this.parent.open) {
            for (Setting setting : this.mod.settings) {
                if (!(setting instanceof ModeSetting)) continue;
                ModeSetting modeSetting = (ModeSetting)setting;
                modeSetting.cycle();
            }
        }
    }

    @Override
    public void renderComponent() {
        Hud.drawRoundedRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getWidth(), 16.0, 4.0, this.hovered ? new Color(0, 0, 0, 155).getRGB() : new Color(0, 0, 0, 125).getRGB());
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        String string = "no";
        for (Setting setting : this.mod.settings) {
            if (!(setting instanceof ModeSetting)) continue;
            ModeSetting modeSetting = (ModeSetting)setting;
            string = modeSetting.activeMode;
        }
        Hud.renderer.drawString("Mode: default", (this.parent.parent.getX() + 7) * 2, (this.parent.parent.getY() + this.offset + 2) * 2 - 3, new Color(255, 0, 0, 255).getRGB(), true);
        GL11.glPopMatrix();
    }

    @Override
    public void setOff(int n) {
        this.offset = n;
    }
}


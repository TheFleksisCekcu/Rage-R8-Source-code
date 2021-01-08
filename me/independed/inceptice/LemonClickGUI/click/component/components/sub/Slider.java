
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.LemonClickGUI.click.component.components.sub;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.components.Button;
import me.independed.inceptice.settings.NumberSetting;
import me.independed.inceptice.ui.Hud;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class Slider
extends Component {
    private double renderWidth;
    private boolean hovered;
    private int x;
    private NumberSetting val;
    private boolean dragging = false;
    private Button parent;
    private int y;
    private int offset;

    @Override
    public void updateComponent(int n, int n2) {
        this.hovered = this.isMouseOnButtonD(n, n2) || this.isMouseOnButtonI(n, n2);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        double d = Math.min(88, Math.max(0, n - this.x));
        double d2 = this.val.getMinimum();
        double d3 = this.val.getMaximum();
        this.renderWidth = 88.0 * (this.val.getValue() - d2) / (d3 - d2);
        if (this.dragging) {
            if (d == 0.0) {
                this.val.setValue(this.val.getMinimum());
            } else {
                double d4 = Slider.roundToPlace(d / 88.0 * (d3 - d2) + d2, 2);
                this.val.setValue(d4);
            }
        }
    }

    private static double roundToPlace(double d, int n) {
        n = 2;
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (this.isMouseOnButtonD(n, n2) && n3 == 0 && this.parent.open) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(n, n2) && n3 == 0 && this.parent.open) {
            this.dragging = true;
        }
    }

    @Override
    public void mouseReleased(int n, int n2, int n3) {
        this.dragging = false;
    }

    public boolean isMouseOnButtonD(int n, int n2) {
        return n > this.x && n < this.x + (this.parent.parent.getWidth() / 2 + 1) && n2 > this.y && n2 < this.y + 16;
    }

    @Override
    public void setOff(int n) {
        this.offset = n;
    }

    public boolean isMouseOnButtonI(int n, int n2) {
        return n > this.x + this.parent.parent.getWidth() / 2 && n < this.x + this.parent.parent.getWidth() && n2 > this.y && n2 < this.y + 16;
    }

    @Override
    public void renderComponent() {
        Hud.drawRoundedRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getWidth(), 16.0, 4.0, this.hovered ? new Color(0, 0, 0, 155).getRGB() : new Color(0, 0, 0, 125).getRGB());
        Gui.drawRect((int)(this.parent.parent.getX() + 4), (int)(this.parent.parent.getY() + this.offset), (int)(this.parent.parent.getX() + (int)this.renderWidth), (int)(this.parent.parent.getY() + this.offset + 16), (int)(this.hovered ? new Color(55, 190, 55, 255).getRGB() : new Color(55, 155, 55, 255).getRGB()));
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Hud.renderer.drawString(this.val.name + ": " + this.val.getValue(), this.parent.parent.getX() * 2 + 15, (this.parent.parent.getY() + this.offset + 2) * 2 - 3, new Color(255, 10, 10, 255).getRGB(), false);
        GL11.glPopMatrix();
    }

    public Slider(NumberSetting numberSetting, Button button, int n) {
        this.val = numberSetting;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = n;
    }
}


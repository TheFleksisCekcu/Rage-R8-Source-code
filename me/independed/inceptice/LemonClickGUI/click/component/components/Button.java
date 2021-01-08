
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.LemonClickGUI.click.component.components;

import java.awt.Color;
import java.util.ArrayList;
import me.independed.inceptice.LemonClickGUI.click.ClickGui;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.Frame;
import me.independed.inceptice.LemonClickGUI.click.component.components.sub.Checkbox;
import me.independed.inceptice.LemonClickGUI.click.component.components.sub.Keybind;
import me.independed.inceptice.LemonClickGUI.click.component.components.sub.ModeButton;
import me.independed.inceptice.LemonClickGUI.click.component.components.sub.Slider;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.settings.BooleanSetting;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.NumberSetting;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Button
extends Component {
    private int height;
    public int inUp = 0;
    public boolean open;
    private boolean isHovered;
    public int offset;
    public Module mod;
    private ArrayList subcomponents;
    public Frame parent;

    @Override
    public void setOff(int n) {
        this.offset = n;
        int n2 = this.offset + 16;
        for (Component component : this.subcomponents) {
            component.setOff(n2);
            n2 += 16;
        }
    }

    @Override
    public void renderComponent() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        if (this.isHovered && this.inUp >= 16) {
            Hud.smallRenderer.drawString(this.mod.getDescription(), 0.0f, scaledResolution.getScaledHeight() - 13, new Color(255, 0, 0, 255).getRGB(), true);
        }
        if (this.inUp < 16) {
            Hud.drawRoundedRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getWidth(), this.inUp, 8.0, this.isHovered ? (this.mod.isEnabled() ? new Color(0, 0, 0, 165).getRGB() : new Color(0, 0, 0, 155).getRGB()) : (this.mod.isEnabled() ? new Color(0, 0, 0, 125).getRGB() : new Color(0, 0, 0, 125).getRGB()));
            this.inUp += 2;
        } else {
            Hud.drawRoundedRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getWidth(), this.inUp, 8.0, this.isHovered ? (this.mod.isEnabled() ? new Color(0, 0, 0, 165).getRGB() : new Color(0, 0, 0, 155).getRGB()) : (this.mod.isEnabled() ? new Color(0, 0, 0, 125).getRGB() : new Color(0, 0, 0, 125).getRGB()));
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        if (this.inUp >= 16) {
            Hud.renderer.drawString(this.mod.getName(), (float)((this.parent.getX() + 2) * 2) + 3.5f, (float)((this.parent.getY() + this.offset + 2) * 2) - 1.5f, this.mod.isEnabled() ? ClickGui.color : new Color(255, 255, 255, 255).getRGB(), true);
        }
        if (this.mod.isToggled() && this.inUp >= 16) {
            Hud.drawRoundedRect((this.parent.getX() + 2) * 2 - 4, (this.parent.getY() + this.offset + 2) * 2 - 4, 4.0, 32.0, 6.0, ClickGui.color);
        }
        if (this.subcomponents.size() > 1 && this.inUp >= 16) {
            Hud.renderer.drawString(this.open ? ">" : "^", (float)((this.parent.getX() + this.parent.getWidth() - 10) * 2) - 1.0f, (float)((this.parent.getY() + this.offset + 2) * 2) - 2.0f, new Color(255, 0, 0, 255).getRGB(), true);
        }
        GL11.glPopMatrix();
        if (this.open && !this.subcomponents.isEmpty()) {
            for (Component component : this.subcomponents) {
                component.renderComponent();
            }
            Hud.drawRoundedRect(this.parent.getX() + 2, this.parent.getY() + this.offset + 16, 3.0, this.subcomponents.size() * 16, 4.0, ClickGui.color);
        }
    }

    public ArrayList getValues() {
        ArrayList<NumberSetting> arrayList = new ArrayList<NumberSetting>();
        for (Setting setting : this.mod.settings) {
            if (!(setting instanceof NumberSetting)) continue;
            NumberSetting numberSetting = (NumberSetting)setting;
            arrayList.add(numberSetting);
        }
        return arrayList;
    }

    @Override
    public void keyTyped(char c, int n) {
        for (Component component : this.subcomponents) {
            component.keyTyped(c, n);
        }
    }

    @Override
    public void updateComponent(int n, int n2) {
        this.isHovered = this.isMouseOnButton(n, n2);
        if (!this.subcomponents.isEmpty()) {
            for (Component component : this.subcomponents) {
                component.updateComponent(n, n2);
            }
        }
    }

    @Override
    public void mouseReleased(int n, int n2, int n3) {
        for (Component component : this.subcomponents) {
            component.mouseReleased(n, n2, n3);
        }
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(n, n2) && n3 == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (Component component : this.subcomponents) {
            component.mouseClicked(n, n2, n3);
        }
    }

    public Button(Module module, Frame frame, int n) {
        Component component;
        this.mod = module;
        this.parent = frame;
        this.offset = n + 1;
        this.subcomponents = new ArrayList();
        this.open = false;
        this.height = 16;
        int n2 = n + 16;
        if (!this.getModes().isEmpty()) {
            this.subcomponents.add(new ModeButton(this, module, n2));
            n2 += 16;
        }
        if (!this.getValues().isEmpty()) {
            for (Setting setting : this.getValues()) {
                component = new Slider((NumberSetting)setting, this, n2);
                this.subcomponents.add(component);
                n2 += 16;
            }
        }
        if (!this.getOptions().isEmpty()) {
            for (Setting setting : this.getOptions()) {
                component = new Checkbox((BooleanSetting)setting, this, n2);
                this.subcomponents.add(component);
                n2 += 16;
            }
        }
        this.subcomponents.add(new Keybind(this, n2));
    }

    public ArrayList getOptions() {
        ArrayList<BooleanSetting> arrayList = new ArrayList<BooleanSetting>();
        for (Setting setting : this.mod.settings) {
            if (!(setting instanceof BooleanSetting)) continue;
            BooleanSetting booleanSetting = (BooleanSetting)setting;
            arrayList.add(booleanSetting);
        }
        return arrayList;
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return 16 * (this.subcomponents.size() + 1);
        }
        return 16;
    }

    public boolean isMouseOnButton(int n, int n2) {
        return n > this.parent.getX() && n < this.parent.getX() + this.parent.getWidth() && n2 > this.parent.getY() + this.offset && n2 < this.parent.getY() + 16 + this.offset;
    }

    public ArrayList getModes() {
        ArrayList<ModeSetting> arrayList = new ArrayList<ModeSetting>();
        for (Setting setting : this.mod.settings) {
            if (!(setting instanceof ModeSetting)) continue;
            ModeSetting modeSetting = (ModeSetting)setting;
            arrayList.add(modeSetting);
        }
        return arrayList;
    }
}


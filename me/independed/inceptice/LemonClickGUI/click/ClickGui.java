
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package me.independed.inceptice.LemonClickGUI.click;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import me.independed.inceptice.LemonClickGUI.click.component.Component;
import me.independed.inceptice.LemonClickGUI.click.component.Frame;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.modules.player.Scaffold;
import me.independed.inceptice.particles.ParticleSystem;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.NumberSetting;
import me.independed.inceptice.ui.Hud;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ClickGui
extends GuiScreen {
    private TimerUtil timerRend = new TimerUtil();
    public static ArrayList frames;
    public static int color;
    int vX = 0;
    int bRad = 0;
    private ParticleSystem particleSystem = new ParticleSystem(350);
    int b = 0;
    int g = 0;
    int r = 0;

    protected void mouseReleased(int n, int n2, int n3) {
        for (Frame frame : frames) {
            frame.setDrag(false);
        }
        for (Frame frame : frames) {
            if (!frame.isOpen() || frame.getComponents().isEmpty()) continue;
            for (Component component : frame.getComponents()) {
                component.mouseReleased(n, n2, n3);
            }
        }
    }

    protected void mouseClicked(int n, int n2, int n3) throws IOException {
        for (Frame frame : frames) {
            if (frame.isWithinHeader(n, n2) && n3 == 0) {
                frame.setDrag(true);
                frame.dragX = n - frame.getX();
                frame.dragY = n2 - frame.getY();
            }
            if (frame.isWithinHeader(n, n2) && n3 == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (!frame.isOpen() || frame.getComponents().isEmpty()) continue;
            for (Component component : frame.getComponents()) {
                component.mouseClicked(n, n2, n3);
            }
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (this.mc.entityRenderer.getShaderGroup() != null) {
                this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }

    public void onGuiClosed() {
        if (this.mc.entityRenderer.getShaderGroup() != null) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            this.mc.entityRenderer.stopUseShader();
        }
        this.bRad = 0;
        this.vX = 0;
    }

    public ClickGui() throws IOException {
        if (Scaffold.f()) {
            frames = new ArrayList();
            int n = 100;
            for (Module$Category module$Category : Module$Category.values()) {
                Frame frame = new Frame(module$Category);
                frame.setX(n);
                frame.setY(10);
                frames.add(frame);
                n += frame.getWidth() + 5;
            }
        }
    }

    public void drawScreen(int n, int n2, float f) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        int[] arrn = new int[]{1};
        for (Object object : ModuleManager.getModuleByName((String)"ClickGui").settings) {
            Object object2;
            if (object instanceof NumberSetting) {
                object2 = (NumberSetting)object;
                if (((NumberSetting)object2).name == "Red") {
                    this.r = (int)((NumberSetting)object2).getValue();
                }
                if (((NumberSetting)object2).name == "Green") {
                    this.g = (int)((NumberSetting)object2).getValue();
                }
                if (((NumberSetting)object2).name == "Blue") {
                    this.b = (int)((NumberSetting)object2).getValue();
                }
            }
            if (object instanceof ModeSetting) {
                object2 = (ModeSetting)object;
                if (((ModeSetting)object2).activeMode == "rainbow") {
                    color = Hud.rainbow(arrn[0] * 300);
                    break;
                }
            }
            color = new Color(this.r, this.g, this.b, 255).getRGB();
        }
        ClickGui.drawRect((int)0, (int)(scaledResolution.getScaledHeight() / 2), (int)scaledResolution.getScaledWidth(), (int)(scaledResolution.getScaledHeight() / 2 + this.vX), (int)new Color(0, 0, 0, 50).getRGB());
        ClickGui.drawRect((int)0, (int)(scaledResolution.getScaledHeight() / 2), (int)scaledResolution.getScaledWidth(), (int)(scaledResolution.getScaledHeight() / 2 - this.vX), (int)new Color(0, 0, 0, 50).getRGB());
        if (this.vX < 800) {
            this.vX += 3;
        }
        Hud.drawRoundedRect(0.0, 0.0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 2.0, new Color(255, 0, 0, 35).getRGB());
        if (this.bRad < 1920) {
            this.bRad += 5;
        }
        for (Object object : frames) {
            ((Frame)object).renderFrame();
            ((Frame)object).updatePosition(n, n2);
            for (Component component : ((Frame)object).getComponents()) {
                component.updateComponent(n, n2);
            }
        }
    }

    protected void keyTyped(char c, int n) {
        for (Frame frame : frames) {
            if (!frame.isOpen() || n == 1 || frame.getComponents().isEmpty()) continue;
            for (Component component : frame.getComponents()) {
                component.keyTyped(c, n);
            }
        }
        if (n == 1) {
            this.mc.displayGuiScreen(null);
        }
    }
}


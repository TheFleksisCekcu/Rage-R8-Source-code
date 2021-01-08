
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.NonNullList
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.ui;

import java.awt.Color;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.secondclickgui.GlyphPageFontRenderer;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.util.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Hud
extends Gui {
    public TimerUtil timerUtil;
    public DecimalFormat df;
    public static GlyphPageFontRenderer targetRenderer;
    public Random random;
    public static GlyphPageFontRenderer smallRenderer;
    public static GlyphPageFontRenderer fuckrenderer;
    public static GlyphPageFontRenderer myRenderer;
    private String toAdd;
    private Minecraft mc = Minecraft.getMinecraft();
    public static GlyphPageFontRenderer renderer;

    public EntityPlayer getClosest() {
        List list = this.mc.world.loadedEntityList.stream().filter(this::lambda$getClosest$0).filter(this::lambda$getClosest$1).filter(entity -> !entity.isDead).filter(entity -> entity instanceof EntityPlayer).sorted(Comparator.comparing(this::lambda$getClosest$4)).collect(Collectors.toList());
        if (list.size() > 0) {
            return (EntityPlayer)list.get(0);
        }
        return null;
    }

    private void onRenderKeyStrokes(RenderGameOverlayEvent renderGameOverlayEvent) {
        int[] arrn = new int[]{1};
        int n = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindForward.getKeyCode()) ? 125 : 50;
        int n2 = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindLeft.getKeyCode()) ? 125 : 50;
        int n3 = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindBack.getKeyCode()) ? 125 : 50;
        int n4 = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindRight.getKeyCode()) ? 125 : 50;
        Hud.drawRoundedRect(5.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, n2).getRGB());
        Hud.drawRoundedRect(38.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, n3).getRGB());
        Hud.drawRoundedRect(71.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, n4).getRGB());
        Hud.drawRoundedRect(38.0, 217.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, n).getRGB());
        renderer.drawString("W", 42.5f, 219.0f, new Color(255, 0, 0, 255).getRGB(), true);
        renderer.drawString("A", 45.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        renderer.drawString("S", 12.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        renderer.drawString("D", 78.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        arrn[0] = arrn[0] + 1;
    }

    public static void renderItem(ItemStack itemStack, Point point) {
        GlStateManager.enableTexture2D();
        GL11.glPushAttrib((int)524288);
        GL11.glDisable((int)3089);
        GlStateManager.clear((int)256);
        GL11.glPopAttrib();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, point.x, point.y);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, itemStack, point.x, point.y);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0f;
        GlStateManager.popMatrix();
        Hud.begin();
    }

    public static int rainbow(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 10.0);
        return Color.getHSBColor((float)((d %= -360.0) / -360.0), 0.735f, 1.0f).getRGB();
    }

    public void onRenderArmor(RenderGameOverlayEvent renderGameOverlayEvent) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        NonNullList nonNullList = Minecraft.getMinecraft().player.inventory.armorInventory;
        int n = 3;
        for (int i = 0; i < nonNullList.size(); ++i) {
            Hud.renderItem((ItemStack)nonNullList.get(n--), new Point(scaledResolution.getScaledWidth() / 2 + n * 18 + 38, scaledResolution.getScaledHeight() - 55));
        }
    }

    public void onRenderInventory(RenderGameOverlayEvent renderGameOverlayEvent) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        NonNullList nonNullList = Minecraft.getMinecraft().player.inventory.mainInventory;
        int n = 27;
        int n2 = 4;
        int n3 = 0;
        while (true) {
            Hud.drawRoundedRect(scaledResolution.getScaledWidth() - 178 + n3 * 20, scaledResolution.getScaledHeight() - 100 + n2 * 20, 16.0, 16.0, 8.0, new Color(0, 0, 0, 100).getRGB());
            Hud.renderItem((ItemStack)nonNullList.get(n++), new Point(scaledResolution.getScaledWidth() - 178 + n3 * 20, scaledResolution.getScaledHeight() - 100 + n2 * 20));
            ++n3;
        }
    }

    private boolean lambda$getClosest$0(Entity entity) {
        return entity != this.mc.player;
    }

    public static void drawBorderedCircle(double d, double d2, float f, int n, int n2) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        float f2 = 0.1f;
        GL11.glScalef((float)f2, (float)f2, (float)f2);
        d = (int)(d * (double)(1.0f / f2));
        d2 = (int)(d2 * (double)(1.0f / f2));
        Hud.drawCircle(d, d2, f *= 1.0f / f2, n2);
        Hud.drawUnfilledCircle(d, d2, f, 1.0f, n);
        GL11.glScalef((float)(1.0f / f2), (float)(1.0f / f2), (float)(1.0f / f2));
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    private boolean lambda$getClosest$1(Entity entity) {
        return this.mc.player.getDistance(entity) <= 150.0f;
    }

    private boolean lambda$onRenderTargetHUD$6(Entity entity) {
        return this.mc.player.getDistance(entity) <= 5.0f;
    }

    public Hud() {
        this.df = new DecimalFormat("###.#");
        this.random = new Random();
        this.timerUtil = new TimerUtil();
    }

    public static void drawUnfilledCircle(double d, double d2, float f, float f2, int n) {
        f2 = 1.0f;
        float f3 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f4 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f5 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f6 = (float)(n & 0xFF) / 255.0f;
        GL11.glColor4f((float)f4, (float)f5, (float)f6, (float)f3);
        GL11.glLineWidth((float)f2);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)2);
        int n2 = 0;
        while (true) {
            GL11.glVertex2d((double)(d + Math.sin((double)n2 * 3.141526 / 180.0) * (double)f), (double)(d2 + Math.cos((double)n2 * 3.141526 / 180.0) * (double)f));
            ++n2;
        }
    }

    @SubscribeEvent
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent renderGameOverlayEvent) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        if (ModuleManager.getModuleByName("HUD").isToggled() && renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int[] arrn = new int[]{1};
            int n = 0;
            if (ModuleManager.getModuleByName("KeyStrokes").isToggled()) {
                this.onRenderKeyStrokes(renderGameOverlayEvent);
            }
            if (ModuleManager.getModuleByName("InventoryView").isToggled()) {
                this.onRenderInventory(renderGameOverlayEvent);
            }
            if (ModuleManager.getModuleByName("ArmorView").isToggled()) {
                this.onRenderArmor(renderGameOverlayEvent);
            }
            if (ModuleManager.getModuleByName("TargetHUD").isToggled()) {
                this.onRenderTargetHUD(renderGameOverlayEvent);
            }
            fuckrenderer.drawString("Raped by NulledSquad", 5.0f, 5.0f, Hud.rainbow(arrn[0] * 300), true);
            smallRenderer.drawString("Raped by NulledSquad", 300.0f, 7.0f, new Color(255, 255, 255, 255).getRGB(), false);
            for (Module module : ModuleManager.getSortedModules()) {
                if (!module.isToggled() || module.getName().equals("TabGUI") || module.getName().equals("Gui")) continue;
                this.toAdd = "no";
                for (Setting setting : module.settings) {
                    if (!(setting instanceof ModeSetting)) continue;
                    ModeSetting modeSetting = (ModeSetting)setting;
                    this.toAdd = modeSetting.activeMode;
                    break;
                }
                double d = n * (fontRenderer.FONT_HEIGHT + 5);
                if (this.toAdd == "no") {
                    Hud.drawRoundedRect(scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.name) - 8, (int)d, 2.0, 5 + fontRenderer.FONT_HEIGHT, 3.0, Hud.rainbow(arrn[0] * 300));
                    Hud.drawRoundedRect(scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.name) - 6, (int)d, 115.0, 5 + fontRenderer.FONT_HEIGHT, 6.0, new Color(0, 0, 0, 75).getRGB());
                    myRenderer.drawString(module.getName(), scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.getName()) - 4, (float)(1.0 + d), Hud.rainbow(arrn[0] * 300), true);
                } else {
                    Hud.drawRoundedRect(scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.name + " [" + this.toAdd + "]") - 8, (int)d, 2.0, 5 + fontRenderer.FONT_HEIGHT, 3.0, Hud.rainbow(arrn[0] * 300));
                    Hud.drawRoundedRect(scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.name + " [" + this.toAdd + "]") - 6, (int)d, 115.0, 5 + fontRenderer.FONT_HEIGHT, 6.0, new Color(0, 0, 0, 75).getRGB());
                    myRenderer.drawString(module.getName(), scaledResolution.getScaledWidth() - myRenderer.getStringWidth(module.getName() + " [" + this.toAdd + "]") - 4, (float)(1.0 + d), Hud.rainbow(arrn[0] * 300), true);
                    myRenderer.drawString(" [" + this.toAdd + "]", scaledResolution.getScaledWidth() - myRenderer.getStringWidth(" [" + this.toAdd + "]") - 4, (float)(1.0 + d), new Color(255, 5, 5, 255).getRGB(), true);
                }
                arrn[0] = arrn[0] + 1;
                ++n;
            }
        }
    }

    public static void drawCircle(double d, double d2, float f, int n) {
        float f2 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n & 0xFF) / 255.0f;
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        GL11.glBegin((int)9);
        int n2 = 0;
        while (true) {
            GL11.glVertex2d((double)(d + Math.sin((double)n2 * 3.141526 / 180.0) * (double)f), (double)(d2 + Math.cos((double)n2 * 3.141526 / 180.0) * (double)f));
            ++n2;
        }
    }

    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        float f5;
        if (f < f3) {
            f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f6, (float)f7, (float)f8, (float)f5);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)f, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void onRenderTargetHUD(RenderGameOverlayEvent renderGameOverlayEvent) {
        if (this.mc.player == null || this.mc.player.isDead) {
            return;
        }
        List list = this.mc.world.loadedEntityList.stream().filter(this::lambda$onRenderTargetHUD$5).filter(this::lambda$onRenderTargetHUD$6).filter(entity -> !entity.isDead).filter(entity -> entity instanceof EntityPlayer).filter(entity -> entity.ticksExisted > 30).filter(entity -> !entity.isInvisible()).sorted(Comparator.comparing(this::lambda$onRenderTargetHUD$11)).collect(Collectors.toList());
        if (list.size() > 0 && ModuleManager.getModuleByName("TargetHUD").isToggled()) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            int n = (int)(((EntityPlayer)list.get(0)).getHealth() * 5.0f + ((EntityPlayer)list.get(0)).getAbsorptionAmount() * 5.0f);
            int n2 = new Color(222, 222, 222, 255).getRGB();
            if (n >= 90) {
                n2 = new Color(0, 255, 0, 255).getRGB();
            }
            if (n >= 50 && n < 90) {
                n2 = new Color(155, 155, 225, 255).getRGB();
            }
            if (n > 20 && n < 50) {
                n2 = new Color(225, 155, 15, 255).getRGB();
            }
            if (n <= 20) {
                n2 = new Color(255, 0, 0, 255).getRGB();
            }
            Hud.drawRoundedRect(scaledResolution.getScaledWidth() / 2 - 55, scaledResolution.getScaledHeight() / 2 - 55, 110.0, 50.0, 6.0, new Color(0, 0, 0, 75).getRGB());
            Hud.drawRoundedRect(scaledResolution.getScaledWidth() / 2 - 50, scaledResolution.getScaledHeight() / 2 - 46, ((EntityPlayer)list.get(0)).getHealth() * 5.0f, 2.0, 3.0, n2);
            targetRenderer.drawString("Health:\u00a7c\u00a7l" + n / 5 + "\u00a7c", scaledResolution.getScaledWidth() / 2 - 50, scaledResolution.getScaledHeight() / 2 - 40, new Color(225, 225, 225, 255).getRGB(), true);
            targetRenderer.drawString("Dist:\u00a7c\u00a7l" + this.df.format(this.mc.player.getDistance((Entity)list.get(0))) + "\u00a7c", scaledResolution.getScaledWidth() / 2, scaledResolution.getScaledHeight() / 2 - 40, new Color(225, 225, 225, 255).getRGB(), true);
            targetRenderer.drawString(((Entity)list.get(0)).getName(), scaledResolution.getScaledWidth() / 2 - targetRenderer.getStringWidth(((Entity)list.get(0)).getName()) / 2, scaledResolution.getScaledHeight() / 2 - 25, new Color(225, 225, 225, 255).getRGB(), true);
        }
    }

    static {
        renderer = GlyphPageFontRenderer.create("Arial", 40, true, true, true);
        myRenderer = GlyphPageFontRenderer.create("Times New Roman Baltic", 19, true, true, true);
        fuckrenderer = GlyphPageFontRenderer.create("Courier New", 50, true, true, true);
        smallRenderer = GlyphPageFontRenderer.create("Courier New", 18, true, true, true);
        targetRenderer = GlyphPageFontRenderer.create("Courier New", 16, true, true, true);
    }

    private static void begin() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.glLineWidth((float)2.0f);
    }

    private Float lambda$onRenderTargetHUD$11(Entity entity) {
        return Float.valueOf(this.mc.player.getDistance(entity));
    }

    public static void drawCustomRect(float f, float f2, float f3, float f4, int n) {
        float f5;
        if (f < f3) {
            f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f6, (float)f7, (float)f8, (float)f5);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)f, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        Hud.drawRect(f - 1.0f, f2 - 1.0f, f, f4, new Color(255, 0, 255, 255).getRGB());
        Hud.drawRect(f, f2 - 1.0f, f3, f2, new Color(255, 0, 255, 255).getRGB());
        Hud.drawRect(f3 + 1.0f, f4 + 1.0f, f3, f2, new Color(255, 0, 255, 255).getRGB());
        Hud.drawRect(f3, f4 + 1.0f, f, f4, new Color(255, 0, 255, 255).getRGB());
    }

    private boolean lambda$onRenderTargetHUD$5(Entity entity) {
        return entity != this.mc.player;
    }

    public static void drawRoundedRect(double d, double d2, double d3, double d4, double d5, int n) {
        double d6 = d + d3;
        double d7 = d2 + d4;
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        GL11.glPushAttrib((int)0);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        d6 *= 2.0;
        d7 *= 2.0;
        Tessellator.getInstance();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f, (float)f2, (float)f3, (float)f4);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)9);
        int n2 = 0;
        while (true) {
            GL11.glVertex2d((double)((d *= 2.0) + d5 + Math.sin((double)n2 * Math.PI / 180.0) * (d5 * -1.0)), (double)((d2 *= 2.0) + d5 + Math.cos((double)n2 * Math.PI / 180.0) * (d5 * -1.0)));
            n2 += 3;
        }
    }

    private Float lambda$getClosest$4(Entity entity) {
        return Float.valueOf(this.mc.player.getDistance(entity));
    }
}


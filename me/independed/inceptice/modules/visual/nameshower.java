
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.visual.nameshower$EnchantEntry;
import me.independed.inceptice.settings.BooleanSetting;
import me.independed.inceptice.ui.Hud;
import me.independed.inceptice.util.ColorUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class nameshower
extends Module {
    public BooleanSetting invisibleSet;
    public BooleanSetting mobSet = new BooleanSetting("Mobs", false);
    public BooleanSetting playerSet;
    public BooleanSetting animalSet = new BooleanSetting("Animals", false);

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
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        nameshower.glColor(n);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)f, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        GL11.glVertex2d((double)f3, (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glEnd();
        nameshower.glColor(new Color(255, 255, 255, 255).getRGB());
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
    }

    public void renderArmor(EntityPlayer entityPlayer, int n, int n2) {
        n = 0;
        InventoryPlayer inventoryPlayer = entityPlayer.inventory;
        ItemStack itemStack = entityPlayer.getHeldItemMainhand();
        ItemStack itemStack2 = inventoryPlayer.armorItemInSlot(0);
        ItemStack itemStack3 = inventoryPlayer.armorItemInSlot(1);
        ItemStack itemStack4 = inventoryPlayer.armorItemInSlot(2);
        ItemStack itemStack5 = inventoryPlayer.armorItemInSlot(3);
        ItemStack[] arritemStack = null;
        arritemStack = itemStack != null ? new ItemStack[]{itemStack, itemStack5, itemStack4, itemStack3, itemStack2} : new ItemStack[]{itemStack5, itemStack4, itemStack3, itemStack2};
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (ItemStack itemStack6 : arritemStack) {
            if (itemStack6 == null || itemStack6.getItem() == null) continue;
            arrayList.add(itemStack6);
        }
        int n3 = 16 * arrayList.size() / 2;
        n -= n3;
        GlStateManager.disableDepth();
        for (ItemStack itemStack7 : arrayList) {
            this.renderItem(itemStack7, n, n2);
            n += 16;
        }
        GlStateManager.enableDepth();
    }

    public nameshower() {
        super("NameTags", "shows info about other players", 0, Module$Category.RENDER);
        this.playerSet = new BooleanSetting("Players", true);
        this.invisibleSet = new BooleanSetting("Invisible", true);
        this.addSettings(this.mobSet, this.animalSet, this.playerSet, this.invisibleSet);
    }

    public void renderItem(ItemStack itemStack, int n, int n2) {
        FontRenderer fontRenderer = nameshower.mc.fontRenderer;
        RenderItem renderItem = mc.getRenderItem();
        nameshower$EnchantEntry[] arrnameshower$EnchantEntry = new nameshower$EnchantEntry[]{new nameshower$EnchantEntry(Enchantments.PROTECTION, "Pr"), new nameshower$EnchantEntry(Enchantments.THORNS, "Th"), new nameshower$EnchantEntry(Enchantments.SHARPNESS, "Shar"), new nameshower$EnchantEntry(Enchantments.FIRE_ASPECT, "Fire"), new nameshower$EnchantEntry(Enchantments.KNOCKBACK, "Kb"), new nameshower$EnchantEntry(Enchantments.UNBREAKING, "Unb"), new nameshower$EnchantEntry(Enchantments.POWER, "Pow"), new nameshower$EnchantEntry(Enchantments.INFINITY, "Inf"), new nameshower$EnchantEntry(Enchantments.PUNCH, "Pun"), new nameshower$EnchantEntry(Enchantments.LOOTING, "Loot"), new nameshower$EnchantEntry(Enchantments.SILK_TOUCH, "Silk"), new nameshower$EnchantEntry(Enchantments.FORTUNE, "Fort")};
        GlStateManager.pushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(n - 3), (float)(n2 + 10), (float)0.0f);
        GlStateManager.scale((float)0.3f, (float)0.3f, (float)0.3f);
        GlStateManager.popMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        renderItem.zLevel = -100.0f;
        GlStateManager.disableDepth();
        renderItem.renderItemIntoGUI(itemStack, n, n2);
        renderItem.renderItemOverlayIntoGUI(fontRenderer, itemStack, n, n2, null);
        GlStateManager.enableDepth();
        for (nameshower$EnchantEntry nameshower$EnchantEntry : arrnameshower$EnchantEntry) {
            int n3 = EnchantmentHelper.getEnchantmentLevel((Enchantment)nameshower$EnchantEntry.getEnchant(), (ItemStack)itemStack);
            String string = "" + n3;
            if (n3 > 10) {
                string = "10+";
            }
            if (n3 <= 0) continue;
            GlStateManager.translate((float)(n - 2), (float)(n2 - 1), (float)0.0f);
            GlStateManager.scale((float)0.42f, (float)0.42f, (float)0.42f);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            fontRenderer.drawString(nameshower$EnchantEntry.getName() + " " + string, (float)(20 - fontRenderer.getStringWidth(nameshower$EnchantEntry.getName() + " " + string) / 2), 0.0f, new Color(0, 255, 0, 255).getRGB(), true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GlStateManager.scale((float)2.42f, (float)2.42f, (float)2.42f);
            GlStateManager.translate((float)(-n), (float)(-n2), (float)0.0f);
            n2 -= (int)((float)(fontRenderer.FONT_HEIGHT + 3) * 0.401f);
        }
        renderItem.zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
    }

    public static void glColor(int n) {
        GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)((float)(n >> 24 & 0xFF) / 255.0f));
    }

    void renderNameTag(EntityLivingBase entityLivingBase, String string, double d, double d2, double d3) {
        String string2;
        if (entityLivingBase instanceof EntityArmorStand || entityLivingBase == nameshower.mc.player) {
            return;
        }
        ColorUtils.color(0, 0, 0, 255);
        EntityPlayerSP entityPlayerSP = nameshower.mc.player;
        FontRenderer fontRenderer = nameshower.mc.fontRenderer;
        if (entityPlayerSP.canEntityBeSeen((Entity)entityLivingBase)) {
            ColorUtils.color(200, 200, 200, 255);
        }
        d2 += entityLivingBase.isSneaking() ? 0.5 : 0.7;
        float f = entityPlayerSP.getDistance((Entity)entityLivingBase) / 4.0f;
        if (f < 1.6f) {
            f = 1.6f;
        }
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer;
            EntityPlayer entityPlayer2 = entityPlayer = (EntityPlayer)entityLivingBase;
            string2 = "null";
            ColorUtils.color(255, 0, 0, 255);
        }
        int n = (int)entityLivingBase.getHealth();
        string = String.valueOf(string) + " \u00a7c\u00a7l" + Math.round(n) + "\u00a7c";
        string2 = mc.getRenderManager();
        float f2 = f;
        f2 /= 30.0f;
        f2 = (float)((double)f2 * 0.3);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d2 + 1.4f), (float)((float)d3));
        GL11.glNormal3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)(-((RenderManager)string2).playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((RenderManager)string2).playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)(-f2), (float)(-f2), (float)f2);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer();
        int n2 = fontRenderer.getStringWidth(string) / 2;
        GL11.glBlendFunc((int)770, (int)771);
        Hud.drawRoundedRect(-n2 - 2, -(fontRenderer.FONT_HEIGHT + 6), n2 * 2 + 3, 15.0, 6.0, new Color(0, 0, 0, 90).getRGB());
        int n3 = n2 + 2;
        int n4 = -n2 - 2;
        fontRenderer.drawString(string, (float)((n4 + n3) / 2 - n2), (float)(-(fontRenderer.FONT_HEIGHT + 2)), new Color(255, 255, 255, 255).getRGB(), true);
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            GlStateManager.translate((float)0.0f, (float)1.1f, (float)0.0f);
            this.renderArmor(entityPlayer, 0, -(fontRenderer.FONT_HEIGHT + 1) - 20);
            GlStateManager.translate((float)0.0f, (float)-1.1f, (float)0.0f);
        }
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    @Override
    public void onRenderWorldLast(float f) {
        if (nameshower.mc.player == null || nameshower.mc.player.isDead) {
            return;
        }
        List list = nameshower.mc.world.loadedEntityList.stream().filter(entity -> entity != nameshower.mc.player).filter(entity -> !entity.isDead).sorted(Comparator.comparing(entity -> Float.valueOf(nameshower.mc.player.getDistance(entity)))).collect(Collectors.toList());
        for (Entity entity2 : list) {
            double d;
            double d2;
            double d3;
            double d4;
            double d5;
            double d6;
            if (entity2 == null || entity2 == nameshower.mc.player) continue;
            if (this.invisibleSet.isEnabled() && entity2.isInvisible() && (entity2 instanceof EntityPlayer && this.playerSet.isEnabled() || entity2 instanceof EntityMob && this.mobSet.isEnabled() || entity2 instanceof EntityAnimal && this.animalSet.isEnabled())) {
                d6 = nameshower.mc.getRenderManager().viewerPosX;
                d5 = nameshower.mc.getRenderManager().viewerPosY;
                d4 = nameshower.mc.getRenderManager().viewerPosZ;
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f - d6;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f - d5;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f - d4;
                this.renderNameTag((EntityLivingBase)entity2, entity2.getName(), d3, d2, d);
                continue;
            }
            if (this.mobSet.isEnabled() && entity2 instanceof EntityMob) {
                d6 = nameshower.mc.getRenderManager().viewerPosX;
                d5 = nameshower.mc.getRenderManager().viewerPosY;
                d4 = nameshower.mc.getRenderManager().viewerPosZ;
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f - d6;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f - d5;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f - d4;
                this.renderNameTag((EntityLivingBase)entity2, entity2.getName(), d3, d2, d);
                continue;
            }
            if (this.animalSet.isEnabled() && entity2 instanceof EntityAnimal) {
                d6 = nameshower.mc.getRenderManager().viewerPosX;
                d5 = nameshower.mc.getRenderManager().viewerPosY;
                d4 = nameshower.mc.getRenderManager().viewerPosZ;
                d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f - d6;
                d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f - d5;
                d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f - d4;
                this.renderNameTag((EntityLivingBase)entity2, entity2.getName(), d3, d2, d);
                continue;
            }
            if (!this.playerSet.isEnabled() || !(entity2 instanceof EntityPlayer)) continue;
            d6 = nameshower.mc.getRenderManager().viewerPosX;
            d5 = nameshower.mc.getRenderManager().viewerPosY;
            d4 = nameshower.mc.getRenderManager().viewerPosZ;
            d3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f - d6;
            d2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f - d5;
            d = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f - d4;
            this.renderNameTag((EntityLivingBase)entity2, entity2.getName(), d3, d2, d);
        }
    }
}


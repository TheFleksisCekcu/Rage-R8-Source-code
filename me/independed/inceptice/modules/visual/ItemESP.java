
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import java.util.ArrayList;
import java.util.List;
import me.independed.inceptice.friends.FriendManager;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.BooleanSetting;
import me.independed.inceptice.util.EntityUtil;
import me.independed.inceptice.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class ItemESP
extends Module {
    private transient List ENTITIES;
    private transient int BOX = 0;
    public transient BooleanSetting tracers = new BooleanSetting("Tracers", false);
    public transient BooleanSetting showItemNames = new BooleanSetting("ShowName", true);

    public static void drawESPBoxes(List list, int n, float f) {
        GL11.glLineWidth((float)2.0f);
        for (Entity entity : list) {
            GL11.glPushMatrix();
            Vec3d vec3d = EntityUtil.getInterpolatedPos(entity, f);
            GL11.glTranslated((double)vec3d.x, (double)vec3d.y, (double)vec3d.z);
            GL11.glScaled((double)((double)entity.width + 0.1), (double)((double)entity.height + 0.1), (double)((double)entity.width + 0.1));
            if (entity instanceof EntityPlayer && FriendManager.isFriend(entity.getName())) {
                GL11.glColor4f((float)0.9f, (float)0.2f, (float)1.0f, (float)0.5f);
            } else if (entity instanceof EntityItem) {
                GL11.glColor4f((float)0.5f, (float)0.5f, (float)1.0f, (float)0.5f);
            } else {
                float f2 = Minecraft.getMinecraft().player.getDistance(entity) / 20.0f;
                GL11.glColor4f((float)(2.0f - f2), (float)f2, (float)0.0f, (float)0.5f);
            }
            GL11.glCallList((int)n);
            GL11.glPopMatrix();
        }
    }

    public ItemESP() {
        super("ItemESP", "shows item through the walls", 0, Module$Category.RENDER);
        this.ENTITIES = new ArrayList();
        this.settings.add(this.tracers);
        this.settings.add(this.showItemNames);
    }

    @Override
    public void onRenderWorldLast(float f) {
        GL11.glPushAttrib((int)24581);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-Minecraft.getMinecraft().getRenderManager().viewerPosX), (double)(-Minecraft.getMinecraft().getRenderManager().viewerPosY), (double)(-Minecraft.getMinecraft().getRenderManager().viewerPosZ));
        this.ENTITIES.clear();
        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (!(entity instanceof EntityItem)) continue;
            this.ENTITIES.add(entity);
        }
        ItemESP.drawESPBoxes(this.ENTITIES, this.BOX, f);
        if (this.tracers.isEnabled()) {
            RenderUtil.drawESPTracers(this.ENTITIES);
        }
        GL11.glPopAttrib();
        if (this.showItemNames.isEnabled()) {
            this.drawStackNames(f);
        }
        GL11.glPopMatrix();
    }

    private void drawStackNames(float f) {
        for (Entity entity : this.ENTITIES) {
            GL11.glPushMatrix();
            Vec3d vec3d = EntityUtil.getInterpolatedPos(entity, f);
            GL11.glTranslated((double)vec3d.x, (double)vec3d.y, (double)vec3d.z);
            ItemStack itemStack = ((EntityItem)entity).getItem();
            EntityRenderer.drawNameplate((FontRenderer)Minecraft.getMinecraft().fontRenderer, (String)(itemStack.getCount() + "x " + itemStack.getDisplayName()), (float)0.0f, (float)1.0f, (float)0.0f, (int)0, (float)Minecraft.getMinecraft().getRenderManager().playerViewY, (float)Minecraft.getMinecraft().getRenderManager().playerViewX, (Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? 1 : 0) != 0, (boolean)false);
            GL11.glPopMatrix();
        }
    }
}


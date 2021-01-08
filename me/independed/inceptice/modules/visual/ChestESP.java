
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockChest$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityMinecartChest
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityEnderChest
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import java.util.ArrayList;
import java.util.List;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.util.EntityUtil;
import me.independed.inceptice.util.RenderUtil;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

public class ChestESP
extends Module {
    private transient List TILE_ENTITIES = new ArrayList();
    private transient List ENTITIES = new ArrayList();
    private transient List TILE_BOXES = new ArrayList();

    private void drawESPBoxes(float f) {
        GL11.glLineWidth((float)2.0f);
        for (int i = 0; i < this.TILE_ENTITIES.size(); ++i) {
            TileEntity tileEntity = (TileEntity)this.TILE_ENTITIES.get(i);
            GL11.glPushMatrix();
            if (tileEntity instanceof TileEntityChest && ((TileEntityChest)tileEntity).getChestType() == BlockChest.Type.TRAP) {
                GL11.glColor4f((float)1.0f, (float)0.6f, (float)0.0f, (float)0.3f);
            } else if (tileEntity instanceof TileEntityEnderChest) {
                GL11.glColor4f((float)0.0f, (float)1.0f, (float)1.0f, (float)0.3f);
            } else if (tileEntity instanceof TileEntityChest) {
                GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)0.3f);
            }
            if (i == this.TILE_BOXES.size()) {
                i = 0;
            }
            RenderUtil.drawSolidBox((AxisAlignedBB)this.TILE_BOXES.get(i));
            RenderUtil.drawOutlinedBox((AxisAlignedBB)this.TILE_BOXES.get(i));
            GL11.glPopMatrix();
        }
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(-0.5, 0.0, -0.5, 0.5, 1.0, 0.5);
        for (Entity entity : this.ENTITIES) {
            GL11.glPushMatrix();
            Vec3d vec3d = EntityUtil.getInterpolatedPos(entity, f);
            GL11.glTranslated((double)vec3d.x, (double)(vec3d.y + 0.38), (double)vec3d.z);
            GL11.glScaled((double)0.66, (double)0.66, (double)0.66);
            GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)0.3f);
            RenderUtil.drawSolidBox(axisAlignedBB);
            RenderUtil.drawOutlinedBox(axisAlignedBB);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void onRenderWorldLast(float f) {
        GL11.glPushAttrib((int)24580);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)3.0f);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-Minecraft.getMinecraft().getRenderManager().viewerPosX), (double)(-Minecraft.getMinecraft().getRenderManager().viewerPosY), (double)(-Minecraft.getMinecraft().getRenderManager().viewerPosZ));
        this.drawESPBoxes(f);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public ChestESP() {
        super("ChestESP", "see chest through the walls", 0, Module$Category.RENDER);
    }

    public static void a(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onLocalPlayerUpdate(TickEvent.PlayerTickEvent playerTickEvent) {
        this.TILE_BOXES.clear();
        this.TILE_ENTITIES.clear();
        for (TileEntity tileEntity : Minecraft.getMinecraft().world.loadedTileEntityList) {
            AxisAlignedBB axisAlignedBB;
            TileEntityChest tileEntityChest;
            if (tileEntity instanceof TileEntityChest) {
                AxisAlignedBB axisAlignedBB2;
                BlockPos blockPos;
                tileEntityChest = (TileEntityChest)tileEntity;
                if (tileEntityChest.adjacentChestXPos != null || tileEntityChest.adjacentChestZPos != null || (axisAlignedBB = Minecraft.getMinecraft().world.getBlockState(tileEntityChest.getPos()).getBoundingBox((IBlockAccess)Minecraft.getMinecraft().world, tileEntityChest.getPos()).offset(tileEntityChest.getPos())) == null) continue;
                if (tileEntityChest.adjacentChestXNeg != null) {
                    blockPos = tileEntityChest.adjacentChestXNeg.getPos();
                    axisAlignedBB2 = Minecraft.getMinecraft().world.getBlockState(blockPos).getBoundingBox((IBlockAccess)Minecraft.getMinecraft().world, blockPos).offset(blockPos);
                    axisAlignedBB = axisAlignedBB.union(axisAlignedBB2);
                } else if (tileEntityChest.adjacentChestZNeg != null) {
                    blockPos = tileEntityChest.adjacentChestZNeg.getPos();
                    axisAlignedBB2 = Minecraft.getMinecraft().world.getBlockState(blockPos).getBoundingBox((IBlockAccess)Minecraft.getMinecraft().world, blockPos).offset(blockPos);
                    axisAlignedBB = axisAlignedBB.union(axisAlignedBB2);
                }
            } else {
                if (!(tileEntity instanceof TileEntityEnderChest)) continue;
                tileEntityChest = (TileEntityEnderChest)tileEntity;
                axisAlignedBB = Minecraft.getMinecraft().world.getBlockState(tileEntityChest.getPos()).getBoundingBox((IBlockAccess)Minecraft.getMinecraft().world, tileEntityChest.getPos()).offset(tileEntityChest.getPos());
            }
            this.TILE_ENTITIES.add(tileEntity);
            this.TILE_BOXES.add(axisAlignedBB);
        }
        this.ENTITIES.clear();
        for (TileEntity tileEntity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (!(tileEntity instanceof EntityMinecartChest)) continue;
            this.ENTITIES.add(tileEntity);
        }
    }
}


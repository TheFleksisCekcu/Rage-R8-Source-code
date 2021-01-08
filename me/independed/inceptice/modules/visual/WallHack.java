/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.independed.inceptice.modules.visual;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class WallHack
extends Module {
    @SubscribeEvent
    @SubscribeEvent
    public void onRenderEntityPre(RenderPlayerEvent.Pre pre) {
        GL11.glEnable((int)32823);
        GL11.glPolygonOffset((float)1.0f, (float)-1100000.0f);
        GL11.glDisable((int)2896);
    }

    @SubscribeEvent
    @SubscribeEvent
    public void onRenderEntityPost(RenderPlayerEvent.Post post) {
        GL11.glDisable((int)32823);
        GL11.glPolygonOffset((float)1.0f, (float)1100000.0f);
        GL11.glEnable((int)2896);
    }

    public WallHack() {
        super("WallHack", "wall hack", 50, Module$Category.RENDER);
    }
}


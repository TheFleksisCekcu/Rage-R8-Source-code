/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.independed.inceptice.handler;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.ModuleManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderWorldLastHandler {
    @SubscribeEvent
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent renderWorldLastEvent) {
        for (Module module : ModuleManager.getModuleList()) {
            if (!module.isToggled()) continue;
            module.onRenderWorldLast(renderWorldLastEvent.getPartialTicks());
        }
    }
}



/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.independed.inceptice.modules.hud;

import java.io.IOException;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.NumberSetting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClickGui
extends Module {
    public NumberSetting colorSet1 = new NumberSetting("Red", 255.0, 0.0, 255.0, 1.0);
    public ModeSetting rainbowSet;
    public NumberSetting colorSet2 = new NumberSetting("Green", 0.0, 0.0, 255.0, 1.0);
    me.independed.inceptice.LemonClickGUI.click.ClickGui clickGui;
    public NumberSetting colorSet3 = new NumberSetting("Blue", 0.0, 0.0, 255.0, 1.0);

    @SubscribeEvent
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
    }

    public ClickGui() throws IOException {
        super("ClickGui", "gui menu", 73, Module$Category.HUD);
        this.rainbowSet = new ModeSetting("Theme", "default", "rainbow");
        this.clickGui = new me.independed.inceptice.LemonClickGUI.click.ClickGui();
        this.settings.add(this.colorSet1);
        this.settings.add(this.colorSet2);
        this.settings.add(this.colorSet3);
        this.settings.add(this.rainbowSet);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen((GuiScreen)this.clickGui);
        this.toggle();
    }
}


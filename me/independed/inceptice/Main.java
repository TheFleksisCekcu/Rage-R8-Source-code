
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package me.independed.inceptice;

import java.io.IOException;
import me.independed.inceptice.LemonClickGUI.click.ClickGui;
import me.independed.inceptice.event.EventProcessor;
import me.independed.inceptice.handler.RenderWorldLastHandler;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.proxy.CommonProxy;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid="inceptice", name="Inceptice", version="0.0.1", acceptableRemoteVersions="*")
public class Main {
    @SidedProxy(clientSide="me.independed.inceptice.proxy.ClientProxy", serverSide="me.independed.inceptice.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static RenderWorldLastHandler renderWorldLastHandler;
    public static Setting setting;
    public static ModuleManager moduleManager;
    @Mod.Instance
    public static Main Instance;
    public static Hud hud;
    public static final EventBus EVENT_BUS;
    public static ClickGui clickGui;
    public EventProcessor eventProcessor;
    private boolean checked = false;

    @Mod.EventHandler
    @Mod.EventHandler
    public void init(FMLInitializationEvent fMLInitializationEvent) throws IOException {
        if (null != null) {
            MinecraftForge.EVENT_BUS.register(null);
        }
        this.eventProcessor = new EventProcessor();
        this.eventProcessor.init();
        MinecraftForge.EVENT_BUS.register((Object)new ModuleManager());
        MinecraftForge.EVENT_BUS.register((Object)new Hud());
        MinecraftForge.EVENT_BUS.register((Object)new ClickGui());
        MinecraftForge.EVENT_BUS.register((Object)new Setting());
        MinecraftForge.EVENT_BUS.register((Object)new RenderWorldLastHandler());
        clickGui = new ClickGui();
    }

    @Mod.EventHandler
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent fMLPreInitializationEvent) {
    }

    static {
        EVENT_BUS = new EventManager();
    }

    @SubscribeEvent
    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent keyInputEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
            int n = Keyboard.getEventKey();
            if (n <= 0) {
                return;
            }
            for (Module module : ModuleManager.modules) {
                if (module.getKey() != n || n <= 0) continue;
                module.toggle();
            }
        }
    }

    @Mod.EventHandler
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) {
    }
}


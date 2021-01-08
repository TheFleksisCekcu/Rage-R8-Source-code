
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package me.independed.inceptice.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager$1;
import me.independed.inceptice.modules.ModuleManager$2;
import me.independed.inceptice.modules.combat.AutoPotion;
import me.independed.inceptice.modules.combat.AutoTotem;
import me.independed.inceptice.modules.combat.BowSpam;
import me.independed.inceptice.modules.combat.Criticals;
import me.independed.inceptice.modules.combat.Fraerok1;
import me.independed.inceptice.modules.combat.Fraerok2;
import me.independed.inceptice.modules.combat.Fraerok3;
import me.independed.inceptice.modules.combat.Fraerok4;
import me.independed.inceptice.modules.combat.Fraerok5;
import me.independed.inceptice.modules.combat.Fraerok6;
import me.independed.inceptice.modules.combat.PVPBot;
import me.independed.inceptice.modules.combat.wTap;
import me.independed.inceptice.modules.ghost.Panic;
import me.independed.inceptice.modules.hud.ArmorView;
import me.independed.inceptice.modules.hud.ClickGui;
import me.independed.inceptice.modules.hud.HUD;
import me.independed.inceptice.modules.hud.InventoryView;
import me.independed.inceptice.modules.hud.KeyStrokes;
import me.independed.inceptice.modules.hud.TabGUI;
import me.independed.inceptice.modules.hud.TargetHUD;
import me.independed.inceptice.modules.misc.FakeCreative;
import me.independed.inceptice.modules.misc.FastUse;
import me.independed.inceptice.modules.misc.MCF;
import me.independed.inceptice.modules.misc.PingSpoof;
import me.independed.inceptice.modules.misc.Spammer;
import me.independed.inceptice.modules.movement.AirWalk;
import me.independed.inceptice.modules.movement.AutoParkour;
import me.independed.inceptice.modules.movement.AutoWalk;
import me.independed.inceptice.modules.movement.Fly;
import me.independed.inceptice.modules.movement.HighJump;
import me.independed.inceptice.modules.movement.InventoryMove;
import me.independed.inceptice.modules.movement.Jesus;
import me.independed.inceptice.modules.movement.LongJump;
import me.independed.inceptice.modules.movement.NoWeb;
import me.independed.inceptice.modules.movement.Spider;
import me.independed.inceptice.modules.movement.Sprint;
import me.independed.inceptice.modules.movement.Strafe;
import me.independed.inceptice.modules.movement.Timer;
import me.independed.inceptice.modules.movement.WaterSpeed;
import me.independed.inceptice.modules.movement.fraerok8;
import me.independed.inceptice.modules.movement.fsjahfk4;
import me.independed.inceptice.modules.movement.spidiki5;
import me.independed.inceptice.modules.player.AirStack;
import me.independed.inceptice.modules.player.AutoGApple;
import me.independed.inceptice.modules.player.Derp;
import me.independed.inceptice.modules.player.FakeHacker;
import me.independed.inceptice.modules.player.FakePlayer;
import me.independed.inceptice.modules.player.GodMode;
import me.independed.inceptice.modules.player.NoFall;
import me.independed.inceptice.modules.player.NoPush;
import me.independed.inceptice.modules.player.Nuker;
import me.independed.inceptice.modules.player.Scaffold;
import me.independed.inceptice.modules.player.Teleport;
import me.independed.inceptice.modules.visual.ChestESP;
import me.independed.inceptice.modules.visual.Esp;
import me.independed.inceptice.modules.visual.FuckYou;
import me.independed.inceptice.modules.visual.FullBright;
import me.independed.inceptice.modules.visual.ItemESP;
import me.independed.inceptice.modules.visual.NoHurtCam;
import me.independed.inceptice.modules.visual.Particles;
import me.independed.inceptice.modules.visual.Tracers;
import me.independed.inceptice.modules.visual.Trajectories;
import me.independed.inceptice.modules.visual.WallHack;
import me.independed.inceptice.modules.visual.nameshower;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ModuleManager {
    public static ArrayList modules;

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
            for (Module module : modules) {
                if (module.getKey() != n || n <= 0) continue;
                module.toggle();
            }
        }
    }

    public static List getModulesByCategory(Module$Category module$Category) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : ModuleManager.getAllSortedModules()) {
            if (module.category != module$Category) continue;
            arrayList.add(module);
        }
        return arrayList;
    }

    public static Module getModuleByName(String string) {
        for (Module module : modules) {
            if (module.name != string) continue;
            return module;
        }
        return null;
    }

    public static List getAllSortedModules() {
        ArrayList arrayList = new ArrayList(ModuleManager.getModuleList());
        arrayList.sort(new ModuleManager$2());
        return arrayList;
    }

    public static List getSortedModules() {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : ModuleManager.getModuleList()) {
            if (!module.isToggled()) continue;
            arrayList.add(module);
        }
        arrayList.sort(new ModuleManager$1());
        return arrayList;
    }

    public static ArrayList getModuleList() {
        return modules;
    }

    public ModuleManager() throws IOException {
        if (Scaffold.f()) {
            modules = new ArrayList();
            modules.add(new Sprint());
            modules.add(new Fraerok2());
            modules.add(new WallHack());
            modules.add(new FullBright());
            modules.add(new TabGUI());
            modules.add(new Fraerok3());
            modules.add(new NoFall());
            modules.add(new Fraerok1());
            modules.add(new Fly());
            modules.add(new Criticals());
            modules.add(new Esp());
            modules.add(new ClickGui());
            modules.add(new fsjahfk4());
            modules.add(new nameshower());
            modules.add(new Fraerok4());
            modules.add(new Tracers());
            modules.add(new Trajectories());
            modules.add(new BowSpam());
            modules.add(new Fraerok6());
            modules.add(new InventoryMove());
            modules.add(new fraerok8());
            modules.add(new LongJump());
            modules.add(new spidiki5());
            modules.add(new HighJump());
            modules.add(new AutoParkour());
            modules.add(new wTap());
            modules.add(new AutoTotem());
            modules.add(new Panic());
            modules.add(new FakePlayer());
            modules.add(new Derp());
            modules.add(new Jesus());
            modules.add(new FakeCreative());
            modules.add(new ChestESP());
            modules.add(new AirWalk());
            modules.add(new PingSpoof());
            modules.add(new HUD());
            modules.add(new Strafe());
            modules.add(new KeyStrokes());
            modules.add(new Teleport());
            modules.add(new InventoryView());
            modules.add(new ArmorView());
            modules.add(new FuckYou());
            modules.add(new Timer());
            modules.add(new AutoWalk());
            modules.add(new Fraerok5());
            modules.add(new AutoGApple());
            modules.add(new PVPBot());
            modules.add(new NoPush());
            modules.add(new AutoPotion());
            modules.add(new FakeHacker());
            modules.add(new MCF());
            modules.add(new TargetHUD());
            modules.add(new ItemESP());
            modules.add(new Particles());
            modules.add(new WaterSpeed());
            modules.add(new Nuker());
            modules.add(new NoHurtCam());
            modules.add(new GodMode());
            modules.add(new Spammer());
            modules.add(new Spider());
            modules.add(new NoWeb());
            modules.add(new AirStack());
            modules.add(new FastUse());
        }
    }
}


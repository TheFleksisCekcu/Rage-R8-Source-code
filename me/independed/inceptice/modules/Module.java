
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.sound.PlaySoundEvent
 *  net.minecraftforge.common.MinecraftForge
 */
package me.independed.inceptice.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.independed.inceptice.Main;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;

public class Module {
    public boolean toggled;
    public String name;
    public int index = 0;
    private boolean state;
    public Module$Category category;
    public boolean expanded;
    private boolean visible;
    public int key;
    protected static final Minecraft mc = Minecraft.getMinecraft();
    public List settings = new ArrayList();
    public String description;

    public Module(String string, String string2, int n, Module$Category module$Category) {
        this.name = string;
        this.description = string2;
        this.key = n;
        this.category = module$Category;
        this.toggled = false;
    }

    public void onPlaySound(PlaySoundEvent playSoundEvent) {
    }

    public void onRenderWorldLast(float f) {
    }

    public String getName() {
        return this.name;
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        Main.EVENT_BUS.unsubscribe((Object)this);
    }

    public void onUpdate() {
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void onLocalPlayerUpdate() {
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getKey() {
        return this.key;
    }

    public void setState(boolean bl) {
        if (bl) {
            this.state = true;
            this.onEnable();
        } else {
            this.state = false;
            this.onDisable();
        }
    }

    public boolean getState() {
        return this.state;
    }

    public void onClientTick() {
    }

    public void setVisible(boolean bl) {
        this.visible = bl;
    }

    public String getDescription() {
        return this.description;
    }

    public String getActiveModeSetting() {
        for (Setting setting : this.settings) {
            if (!(setting instanceof ModeSetting)) continue;
            ModeSetting modeSetting = (ModeSetting)setting;
            return modeSetting.activeMode;
        }
        return null;
    }

    public void setKey(int n) {
        this.key = n;
    }

    public static ArrayList getSortedCategories() {
        ArrayList<Module$Category> arrayList = new ArrayList<Module$Category>();
        for (Module$Category module$Category3 : Module$Category.values()) {
            arrayList.add(module$Category3);
        }
        arrayList.sort((module$Category, module$Category2) -> {
            String string = module$Category.name;
            String string2 = module$Category2.name;
            int n = Hud.myRenderer.getStringWidth(string2) - Hud.myRenderer.getStringWidth(string);
            return n != 0 ? n : string2.compareTo(string);
        });
        return arrayList;
    }

    public void onPlayerTick() {
    }

    public void addSettings(Setting ... arrsetting) {
        this.settings.addAll(Arrays.asList(arrsetting));
    }

    /*
     * Exception decompiling
     */
    public Setting getSettingByName(String var1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl5 : ALOAD_2 - null : trying to set 1 previously set to 0
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.populateStackInfo(Op02WithProcessedDataAndRefs.java:203)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.populateStackInfo(Op02WithProcessedDataAndRefs.java:1542)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:400)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
         * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
         * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
         * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
         * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
         * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
         * org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException(Decompilation failed);
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setDescription(String string) {
        this.description = string;
    }

    public boolean isEnabled() {
        return this.toggled;
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        Main.EVENT_BUS.subscribe((Object)this);
    }

    public void setToggled(boolean bl) {
        this.toggled = bl;
        if (bl) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void setup() {
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public Module$Category getCategory() {
        return this.category;
    }
}


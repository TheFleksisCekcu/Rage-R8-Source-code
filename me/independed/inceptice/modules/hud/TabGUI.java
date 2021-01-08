
/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package me.independed.inceptice.modules.hud;

import java.util.List;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.Module$Category;
import me.independed.inceptice.modules.ModuleManager;
import me.independed.inceptice.settings.BooleanSetting;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.NumberSetting;
import me.independed.inceptice.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class TabGUI
extends Module {
    public NumberSetting redSet = new NumberSetting("Red", 0.0, 0.0, 255.0, 1.0);
    public int currentTab = 0;
    public boolean expanded;
    public NumberSetting greenSet = new NumberSetting("Green", 0.0, 0.0, 255.0, 1.0);
    public NumberSetting blueSet = new NumberSetting("Blue", 0.0, 0.0, 255.0, 1.0);

    /*
     * Exception decompiling
     */
    @SubscribeEvent
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent var1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl314 : ALOAD - null : trying to set 3 previously set to 0
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

    @SubscribeEvent
    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent keyInputEvent) {
        block33: {
            block34: {
                Setting setting;
                Module module;
                if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
                    return;
                }
                if (!Keyboard.isCreated() || !Keyboard.getEventKeyState()) break block33;
                int n = Keyboard.getEventKey();
                if (n <= 0) {
                    return;
                }
                Module$Category module$Category = Module$Category.values()[this.currentTab];
                List list = ModuleManager.getModulesByCategory(module$Category);
                if (n == 200) {
                    if (this.expanded) {
                        if (this.expanded && !list.isEmpty() && ((Module)list.get((int)module$Category.moduleIndex)).expanded) {
                            module = (Module)list.get(module$Category.moduleIndex);
                            if (!module.settings.isEmpty()) {
                                if (((Setting)module.settings.get((int)module.index)).focused) {
                                    setting = (Setting)((Module)list.get((int)module$Category.moduleIndex)).settings.get(module.index);
                                    if (setting instanceof NumberSetting) {
                                        ((NumberSetting)setting).increment(true);
                                    }
                                } else {
                                    module.index = module.index <= 0 ? module.settings.size() - 1 : --module.index;
                                }
                            }
                        } else {
                            module$Category.moduleIndex = module$Category.moduleIndex <= 0 ? list.size() - 1 : --module$Category.moduleIndex;
                        }
                    } else {
                        this.currentTab = this.currentTab <= 0 ? Module$Category.values().length - 1 : --this.currentTab;
                    }
                }
                if (n == 208) {
                    if (this.expanded) {
                        if (this.expanded && !list.isEmpty() && ((Module)list.get((int)module$Category.moduleIndex)).expanded) {
                            module = (Module)list.get(module$Category.moduleIndex);
                            if (!module.settings.isEmpty()) {
                                if (((Setting)module.settings.get((int)module.index)).focused) {
                                    setting = (Setting)((Module)list.get((int)module$Category.moduleIndex)).settings.get(module.index);
                                    if (setting instanceof NumberSetting) {
                                        ((NumberSetting)setting).increment(false);
                                    }
                                } else {
                                    module.index = module.index >= module.settings.size() - 1 ? 0 : ++module.index;
                                }
                            }
                        } else {
                            module$Category.moduleIndex = module$Category.moduleIndex >= list.size() - 1 ? 0 : ++module$Category.moduleIndex;
                        }
                    } else {
                        this.currentTab = this.currentTab >= Module$Category.values().length - 1 ? 0 : ++this.currentTab;
                    }
                }
                if (n == 28 && this.expanded && list.size() != 0) {
                    module = (Module)list.get(module$Category.moduleIndex);
                    if (!module.expanded && !module.settings.isEmpty()) {
                        module.expanded = true;
                    } else if (module.expanded && !module.settings.isEmpty()) {
                        boolean bl = ((Setting)module.settings.get((int)module.index)).focused = !((Setting)module.settings.get((int)module.index)).focused;
                    }
                }
                if (n == 205) {
                    if (this.expanded && list.size() != 0) {
                        module = (Module)list.get(module$Category.moduleIndex);
                        if (!module.settings.isEmpty()) {
                            if (this.expanded && !list.isEmpty() && module.expanded) {
                                setting = (Setting)((Module)list.get((int)module$Category.moduleIndex)).settings.get(module.index);
                                if (setting instanceof BooleanSetting) {
                                    ((BooleanSetting)setting).toggle();
                                }
                                if (setting instanceof ModeSetting) {
                                    ((ModeSetting)setting).cycle();
                                }
                            } else {
                                module.toggle();
                            }
                        } else {
                            module.toggle();
                        }
                    } else {
                        this.expanded = true;
                    }
                }
                if (n != 203) break block33;
                if (!this.expanded || list.isEmpty() || !((Module)list.get((int)module$Category.moduleIndex)).expanded) break block34;
                module = (Module)list.get(module$Category.moduleIndex);
                if (module.settings.isEmpty() || ((Setting)module.settings.get((int)module.index)).focused) break block33;
                ((Module)list.get((int)module$Category.moduleIndex)).expanded = false;
                break block33;
            }
            this.expanded = false;
        }
    }

    public TabGUI() {
        super("TabGUI", "tab gui", 54, Module$Category.HUD);
        this.toggle();
        this.addSettings(this.redSet, this.greenSet, this.blueSet);
    }
}


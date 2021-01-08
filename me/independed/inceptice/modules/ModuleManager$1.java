/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules;

import java.util.Comparator;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.settings.ModeSetting;
import me.independed.inceptice.settings.Setting;
import me.independed.inceptice.ui.Hud;

final class ModuleManager$1
implements Comparator {
    public int compare(Object object, Object object2) {
        return this.compare((Module)object, (Module)object2);
    }

    ModuleManager$1() {
    }

    public int compare(Module module, Module module2) {
        Object object2;
        String string = "no";
        for (Object object2 : module.settings) {
            if (!(object2 instanceof ModeSetting)) continue;
            ModeSetting modeSetting = (ModeSetting)object2;
            string = modeSetting.activeMode;
            break;
        }
        String string2 = module.getName();
        string = "no";
        for (Setting setting : module2.settings) {
            if (!(setting instanceof ModeSetting)) continue;
            ModeSetting modeSetting = (ModeSetting)setting;
            string = modeSetting.activeMode;
            break;
        }
        object2 = module2.getName();
        int n = Hud.myRenderer.getStringWidth((String)object2) - Hud.myRenderer.getStringWidth(string2);
        return n != 0 ? n : ((String)object2).compareTo(string2);
    }
}


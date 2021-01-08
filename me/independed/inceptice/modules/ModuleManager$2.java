/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules;

import java.util.Comparator;
import me.independed.inceptice.modules.Module;
import me.independed.inceptice.ui.Hud;

final class ModuleManager$2
implements Comparator {
    ModuleManager$2() {
    }

    public int compare(Module module, Module module2) {
        String string = module.getName();
        String string2 = module2.getName();
        int n = Hud.myRenderer.getStringWidth(string2) - Hud.myRenderer.getStringWidth(string);
        return n != 0 ? n : string2.compareTo(string);
    }

    public int compare(Object object, Object object2) {
        return this.compare((Module)object, (Module)object2);
    }
}


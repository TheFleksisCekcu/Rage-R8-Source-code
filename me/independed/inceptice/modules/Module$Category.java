/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.modules;

import me.independed.inceptice.modules.Module;
import me.independed.inceptice.modules.ModuleManager;

public enum Module$Category {
    MOVEMENT("MOVEMENT"),
    COMBAT("COMBAT"),
    RENDER("RENDER"),
    PLAYER("PLAYER"),
    WORLD("MISC"),
    GHOST("GHOST"),
    HUD("HUD");

    public int moduleIndex;
    public String name;

    public static int placeInListPlayer(Module module) {
        int n = 2;
        for (Module module2 : ModuleManager.getModuleList()) {
            if (module2.getCategory().equals((Object)PLAYER) && !module2.equals(module)) {
                ++n;
                continue;
            }
            if (!module2.getCategory().equals((Object)PLAYER) || !module2.equals(module)) continue;
            return n;
        }
        return 2;
    }

    public static int size(Module$Category module$Category) {
        int n = 0;
        for (Module module : ModuleManager.getModuleList()) {
            if (module.getCategory() != module$Category) continue;
            ++n;
        }
        return n;
    }

    public static int placeInListMovement(Module module) {
        int n = 2;
        for (Module module2 : ModuleManager.getModuleList()) {
            if (module2.getCategory().equals((Object)MOVEMENT) && !module2.equals(module)) {
                ++n;
                continue;
            }
            if (!module2.getCategory().equals((Object)MOVEMENT) || !module2.equals(module)) continue;
            return n;
        }
        return 2;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private Module$Category() {
        void var3_1;
        this.name = var3_1;
    }

    public static int placeInListCombat(Module module) {
        int n = 2;
        for (Module module2 : ModuleManager.getModuleList()) {
            if (module2.getCategory().equals((Object)COMBAT) && !module2.equals(module)) {
                ++n;
                continue;
            }
            if (!module2.getCategory().equals((Object)COMBAT) || !module2.equals(module)) continue;
            return n;
        }
        return 2;
    }

    public static int placeInListRender(Module module) {
        int n = 2;
        for (Module module2 : ModuleManager.getModuleList()) {
            if (module2.getCategory().equals((Object)RENDER) && !module2.equals(module)) {
                ++n;
                continue;
            }
            if (!module2.getCategory().equals((Object)RENDER) || !module2.equals(module)) continue;
            return n;
        }
        return 2;
    }
}


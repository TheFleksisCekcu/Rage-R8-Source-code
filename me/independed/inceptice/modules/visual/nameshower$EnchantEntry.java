/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 */
package me.independed.inceptice.modules.visual;

import net.minecraft.enchantment.Enchantment;

public class nameshower$EnchantEntry {
    private Enchantment enchant;
    private String name;

    public String getName() {
        return this.name;
    }

    public nameshower$EnchantEntry(Enchantment enchantment, String string) {
        this.enchant = enchantment;
        this.name = string;
    }

    public Enchantment getEnchant() {
        return this.enchant;
    }
}


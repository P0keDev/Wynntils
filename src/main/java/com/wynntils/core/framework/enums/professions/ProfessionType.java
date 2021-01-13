/*
 *  * Copyright © Wynntils - 2018 - 2021.
 */

package com.wynntils.core.framework.enums.professions;

import com.wynntils.core.utils.StringUtils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum ProfessionType {

    // gathering
    WOODCUTTING("Ⓒ", Items.DIAMOND_AXE, 84),
    MINING("Ⓑ", Items.DIAMOND_AXE, 83),
    FISHING("Ⓚ", Items.DIAMOND_AXE, 82),
    FARMING("Ⓙ", Items.DIAMOND_AXE, 81),

    // crafting
    ALCHEMISM("Ⓛ", Items.DIAMOND_AXE, 90),
    ARMOURING("Ⓗ", Items.DIAMOND_AXE, 89),
    COOKING("Ⓐ", Items.DIAMOND_AXE, 86),
    JEWELING("Ⓓ", Items.DIAMOND_AXE, 87),
    SCRIBING("Ⓔ", Items.DIAMOND_AXE, 92),
    TAILORING("Ⓕ", Items.DIAMOND_AXE, 91),
    WEAPONSMITHING("Ⓖ", Items.DIAMOND_AXE, 85),
    WOODWORKING("Ⓘ", Items.DIAMOND_AXE, 88),

    // handled by leaderboard
    OVERALL("", Items.AIR, 0);

    String icon; Item iconItem; int meta;

    ProfessionType(String icon, Item iconItem, int meta) {
        this.icon = icon; this.iconItem = iconItem; this.meta = meta;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return StringUtils.capitalizeFirst(name().toLowerCase());
    }

    public ItemStack getIconItemStack() {
        return new ItemStack(iconItem, 1, meta);
    }

    public static ProfessionType fromMessage(String input) {
        for(ProfessionType type : values()) {
            if (!input.toLowerCase().contains(type.getIcon().toLowerCase() + " " + type.getName().toLowerCase())) continue;

            return type;
        }

        return null;
    }

}

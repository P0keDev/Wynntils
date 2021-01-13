package com.wynntils.webapi.profiles.ingredient.enums;

import com.google.gson.annotations.SerializedName;

import net.minecraft.util.text.TextFormatting;

public enum IngredientTier {

    @SerializedName("0")
    TIER_0(0, TextFormatting.GRAY + "[" + TextFormatting.DARK_GRAY + "✫✫✫" + TextFormatting.GRAY + "]"),
    @SerializedName("1")
    TIER_1(1, TextFormatting.GOLD + " [" + TextFormatting.YELLOW + "✫" + TextFormatting.DARK_GRAY + "✫✫" + TextFormatting.GOLD + "]"),
    @SerializedName("2")
    TIER_2(2, TextFormatting.DARK_PURPLE + " [" + TextFormatting.LIGHT_PURPLE + "✫✫" + TextFormatting.DARK_GRAY + "✫" + TextFormatting.DARK_PURPLE + "]"),
    @SerializedName("3")
    TIER_3(3, TextFormatting.DARK_AQUA + " [" + TextFormatting.AQUA + "✫✫✫" + TextFormatting.DARK_AQUA + "]");

    int tier; String stars;

    IngredientTier(int tier, String stars) {
        this.tier = tier; this.stars = stars;
    }

    public String asDisplayName() {
        return stars;
    }

}

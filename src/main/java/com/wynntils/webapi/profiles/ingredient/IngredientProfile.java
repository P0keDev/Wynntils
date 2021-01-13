package com.wynntils.webapi.profiles.ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wynntils.core.framework.enums.professions.ProfessionType;
import com.wynntils.core.utils.ItemUtils;
import com.wynntils.webapi.WebManager;
import com.wynntils.webapi.profiles.ingredient.enums.IngredientTier;
import com.wynntils.webapi.profiles.ingredient.objects.IngredientIdentificationContainer;
import com.wynntils.webapi.profiles.ingredient.objects.IngredientModifiersContainer;
import com.wynntils.webapi.profiles.ingredient.objects.ItemModifiersContainer;
import com.wynntils.webapi.profiles.item.IdentificationOrderer;
import com.wynntils.webapi.profiles.item.objects.IdentificationContainer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagInt;

import static net.minecraft.util.text.TextFormatting.*;

public class IngredientProfile {

    String displayName;
    IngredientTier tier;
    int level;
    boolean untradeable;
    String material;

    List<ProfessionType> professions = new ArrayList<>();
    Map<String, IngredientIdentificationContainer> statuses = new HashMap<>();

    ItemModifiersContainer itemModifiers;
    IngredientModifiersContainer ingredientModifiers;

    public IngredientProfile(String displayName, IngredientTier tier, int level, boolean untradeable,
            String material, List<ProfessionType> professions, Map<String, IngredientIdentificationContainer> statuses,
            ItemModifiersContainer itemModifiers, IngredientModifiersContainer ingredientModifiers) {}

    public String getDisplayName() {
        return displayName;
    }

    public IngredientTier getTier() {
        return tier;
    }

    public int getLevel() {
        return level;
    }

    public boolean isUntradeable() {
        return untradeable;
    }

    public String getMaterial() {
        return material;
    }

    public List<ProfessionType> getProfessions() {
        return professions;
    }

    public Map<String, IngredientIdentificationContainer> getStatuses() {
        return statuses;
    }

    public ItemModifiersContainer getItemModifiers() {
        return itemModifiers;
    }

    public IngredientModifiersContainer getIngredientModifiers() {
        return ingredientModifiers;
    }

    private ItemStack generateStack() {
        // create stack
        String[] split = material.split(":");
        ItemStack stack = new ItemStack(Item.getItemById(Integer.parseInt(split[0])));
        if (split.length > 1 && Integer.parseInt(split[1]) != 0)
            stack.setItemDamage(Integer.parseInt(split[1]));

        stack.setTagInfo("Unbreakable", new NBTTagByte((byte) 1));

        if (material.equals("397:3")) { // custom head
            String textureString = WebManager.getIngredientHeadTexture(displayName);
            if (textureString != null) ItemUtils.setSkullTexture(stack, textureString);
        }

        stack.setStackDisplayName(GRAY + displayName + " " + tier.asDisplayName());

        List<String> itemLore = new ArrayList<>();

        itemLore.add(DARK_GRAY + "Crafting Ingredient");
        itemLore.add(" ");

        // ids
        if (statuses.size() > 0) {
            for (String idName : statuses.keySet()) {
                IngredientIdentificationContainer id = statuses.get(idName);

                itemLore.add(getIDLore(id, idName));
            }
            itemLore.add(" ");
        }

        // ingredient modifiers
        if (ingredientModifiers.hasModifiers()) {
            if (ingredientModifiers.getLeft() != 0) {
                itemLore.add((ingredientModifiers.getLeft() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getLeft() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients to the left of this one)");
            }
            if (ingredientModifiers.getRight() != 0) {
                itemLore.add((ingredientModifiers.getRight() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getRight() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients to the right of this one)");
            }
            if (ingredientModifiers.getAbove() != 0) {
                itemLore.add((ingredientModifiers.getAbove() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getAbove() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients above this one)");
            }
            if (ingredientModifiers.getUnder() != 0) {
                itemLore.add((ingredientModifiers.getUnder() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getUnder() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients under this one)");
            }
            if (ingredientModifiers.getTouching() != 0) {
                itemLore.add((ingredientModifiers.getTouching() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getTouching() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients touching this one)");
            }
            if (ingredientModifiers.getNotTouching() != 0) {
                itemLore.add((ingredientModifiers.getNotTouching() > 0 ? GREEN + "+" : RED.toString()) +
                        ingredientModifiers.getNotTouching() + "%" + GRAY + " Ingredient Effectiveness");
                itemLore.add(GRAY + "(To ingredients not touching this one)");
            }

            itemLore.add(" ");
        }

        // item modifiers
        if (itemModifiers.hasModifiers()) {
            String durationDurability = "";
            if (itemModifiers.getDurability() != 0) {
                durationDurability += (itemModifiers.getDurability() > 0 ? GREEN + "+" : RED.toString()) +
                        itemModifiers.getDurability() + " Durability";
            }
            if (itemModifiers.getDuration() != 0) {
                if (!durationDurability.isEmpty()) durationDurability += GRAY + " or ";
                durationDurability += (itemModifiers.getDuration() > 0 ? GREEN + "+" : RED.toString()) +
                        itemModifiers.getDuration() + " Duration";
            }
            if (!durationDurability.isEmpty()) itemLore.add(durationDurability);

            if (itemModifiers.getCharges() != 0)
                itemLore.add((itemModifiers.getCharges() > 0 ? GREEN + "+" : RED.toString()) +
                        itemModifiers.getCharges() + " Charges");

            if (itemModifiers.getStrength() != 0)
                itemLore.add((itemModifiers.getStrength() > 0 ? RED + "+" : GREEN.toString()) +
                        itemModifiers.getStrength() + " Strength Min.");
            if (itemModifiers.getDexterity() != 0)
                itemLore.add((itemModifiers.getDexterity() > 0 ? RED + "+" : GREEN.toString()) +
                        itemModifiers.getDexterity() + " Dexterity Min.");
            if (itemModifiers.getIntelligence() != 0)
                itemLore.add((itemModifiers.getIntelligence() > 0 ? RED + "+" : GREEN.toString()) +
                        itemModifiers.getIntelligence() + " Intelligence Min.");
            if (itemModifiers.getDefense() != 0)
                itemLore.add((itemModifiers.getDefense() > 0 ? RED + "+" : GREEN.toString()) +
                        itemModifiers.getDefense() + " Defense Min.");
            if (itemModifiers.getAgility() != 0)
                itemLore.add((itemModifiers.getAgility() > 0 ? RED + "+" : GREEN.toString()) +
                        itemModifiers.getAgility() + " Agility Min.");

            itemLore.add(" ");
        }

        // level and profs
        itemLore.add(GREEN + "✔ " + GRAY + " Crafting Lv. Min: " + level);

        for (ProfessionType prof : professions) {
            itemLore.add(DARK_GRAY + "✔ " + WHITE + prof.getIcon() + " " + GRAY + prof.getName());
        }

        // untradeable
        if (untradeable)
            itemLore.add(RED + "Untradable Item");

        ItemUtils.replaceLore(stack, itemLore);
        return stack;
    }

    private static String getIDLore(IngredientIdentificationContainer id, String idName) {
        int min = id.getMin();
        int max = id.getMax();

        String lore;

        if (id.hasConstantValue())
            if (IdentificationOrderer.INSTANCE.isInverted(idName))
                lore = (min < 0 ? GREEN.toString() : RED + "+") + min;
            else
                lore = (min < 0 ? RED.toString() : GREEN + "+") + min;
        else
            if (IdentificationOrderer.INSTANCE.isInverted(idName))
                lore = min >= 0 ? RED + "+" + min + DARK_RED + " to " + RED + max :
                    GREEN.toString() + min + DARK_GREEN + " to " + GREEN + max;
            else
                lore = min >= 0 ? GREEN + "+" + min + DARK_GREEN + " to " + GREEN + "+" + max :
                    RED.toString() + min + DARK_RED + " to " + max;

        return lore + id.getType().getInGame() + " " + GRAY + IdentificationContainer.getAsLongName(idName);
    }

}

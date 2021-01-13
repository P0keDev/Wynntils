package com.wynntils.webapi.profiles.ingredient.objects;

public class ItemModifiersContainer {

    int durability = 0;
    int duration = 0;
    int charges = 0;

    int strength = 0;
    int dexterity = 0;
    int intelligence = 0;
    int defense = 0;
    int agility = 0;

    public ItemModifiersContainer() {}

    public boolean hasModifiers() {
        return durability != 0 || duration != 0 || charges != 0 ||
                strength != 0 || dexterity != 0 || intelligence != 0
                || defense != 0 || agility != 0;
    }

    public int getDurability() {
        return durability;
    }

    public int getDuration() {
        return duration;
    }

    public int getCharges() {
        return charges;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

}

package com.wynntils.webapi.profiles.ingredient.objects;

import com.wynntils.webapi.profiles.item.enums.IdentificationModifier;

public class IngredientIdentificationContainer {

    private IdentificationModifier type;
    private int minimum;
    private int maximum;

    public IngredientIdentificationContainer(IdentificationModifier type, int minimum, int maximum) {
        this.type = type; this.minimum = minimum; this.maximum = maximum;
    }

    public IdentificationModifier getType() {
        return type;
    }

    public int getMin() {
        return minimum;
    }

    public int getMax() {
        return maximum;
    }

    public boolean hasConstantValue() {
        return (minimum == maximum);
    }

}

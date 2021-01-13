package com.wynntils.webapi.profiles.ingredient.objects;

public class IngredientModifiersContainer {

    int left = 0;
    int right = 0;
    int above = 0;
    int under = 0;
    int touching = 0;
    int notTouching = 0;

    public IngredientModifiersContainer() {}

    public boolean hasModifiers() {
        return left != 0 || right != 0 || above != 0 ||
                under != 0 || touching != 0 || notTouching != 0;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getAbove() {
        return above;
    }

    public int getUnder() {
        return under;
    }

    public int getTouching() {
        return touching;
    }

    public int getNotTouching() {
        return notTouching;
    }

}

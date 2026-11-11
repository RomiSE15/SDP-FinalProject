package com.restaurant.creator;

import com.restaurant.meal.Meal;

public abstract class MealCreator {
    public abstract Meal createMeal();

    public Meal orderMeal() {
        System.out.println("[Factory Method] Creating meal via " + this.getClass().getSimpleName());
        return createMeal();
    }
}
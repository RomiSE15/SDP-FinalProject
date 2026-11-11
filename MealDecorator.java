package com.restaurant.meal;

public abstract class MealDecorator implements Meal {
    protected final Meal meal;

    protected MealDecorator(Meal meal) {
        this.meal = meal;
    }
}
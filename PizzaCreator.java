package com.restaurant.creator;

import com.restaurant.meal.Meal;
import com.restaurant.meal.Pizza;

public class PizzaCreator extends MealCreator {
    @Override
    public Meal createMeal() {
        return new Pizza();
    }
}
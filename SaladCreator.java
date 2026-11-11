package com.restaurant.creator;

import com.restaurant.meal.Meal;
import com.restaurant.meal.Salad;

public class SaladCreator extends MealCreator {
    @Override
    public Meal createMeal() {
        return new Salad();
    }
}
package com.restaurant.creator;

import com.restaurant.meal.Meal;
import com.restaurant.meal.Pasta;

public class PastaCreator extends MealCreator {
    @Override
    public Meal createMeal() {
        return new Pasta();
    }
}
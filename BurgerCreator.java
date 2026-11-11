package com.restaurant.creator;

import com.restaurant.meal.Burger;
import com.restaurant.meal.Meal;

public class BurgerCreator extends MealCreator {
    @Override
    public Meal createMeal() {
        return new Burger();
    }
}
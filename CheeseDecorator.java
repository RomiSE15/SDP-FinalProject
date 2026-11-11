package com.restaurant.meal;

public class CheeseDecorator extends MealDecorator {
    public CheeseDecorator(Meal meal) {
        super(meal);
        System.out.println("[Decorator] Added Cheese");
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Cheese";
    }

    @Override
    public double getPrice() {
        return meal.getPrice() + 1.50;
    }
}
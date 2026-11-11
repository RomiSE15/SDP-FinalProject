package com.restaurant.meal;

public class ChickenDecorator extends MealDecorator {
    public ChickenDecorator(Meal meal) {
        super(meal);
        System.out.println("[Decorator] Added Chicken");
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Chicken";
    }

    @Override
    public double getPrice() {
        return meal.getPrice() + 2.50;
    }
}
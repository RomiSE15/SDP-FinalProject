package com.restaurant.meal;

public class ExtraSauceDecorator extends MealDecorator {
    public ExtraSauceDecorator(Meal meal) {
        super(meal);
        System.out.println("[Decorator] Added Extra Sauce");
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Extra Sauce";
    }

    @Override
    public double getPrice() {
        return meal.getPrice() + 0.75;
    }
}
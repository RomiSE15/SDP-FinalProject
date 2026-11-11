package com.restaurant.meal;

public class Salad implements Meal {
    @Override
    public String getDescription() { return "Salad"; }
    @Override
    public double getPrice() { return 6.75; }
}
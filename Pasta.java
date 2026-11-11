package com.restaurant.meal;

public class Pasta implements Meal {
    @Override
    public String getDescription() { return "Pasta"; }
    @Override
    public double getPrice() { return 10.00; }
}
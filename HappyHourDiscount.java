package com.restaurant.discount;

public class HappyHourDiscount implements DiscountStrategy {
    @Override public double calculate(double total) { return total * 0.20; }
    @Override public String getName() { return "Happy Hour 20%"; }
}
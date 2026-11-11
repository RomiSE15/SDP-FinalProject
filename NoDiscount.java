package com.restaurant.discount;

public class NoDiscount implements DiscountStrategy {
    @Override public double calculate(double total) { return 0; }
    @Override public String getName() { return "No Discount"; }
}
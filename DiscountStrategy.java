package com.restaurant.discount;

public interface DiscountStrategy {
    double calculate(double total);
    String getName();
}
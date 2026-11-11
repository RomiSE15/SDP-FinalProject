package com.restaurant.discount;

public class StudentDiscount implements DiscountStrategy {
    @Override public double calculate(double total) { return total * 0.15; }
    @Override public String getName() { return "Student 15%"; }
}
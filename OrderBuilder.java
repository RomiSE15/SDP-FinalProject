package com.restaurant.order;

import com.restaurant.discount.DiscountStrategy;
import com.restaurant.discount.NoDiscount;
import com.restaurant.meal.Meal;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private int id;
    private String customerName = "Guest";
    private final List<Meal> meals = new ArrayList<>();
    private DiscountStrategy discount = new NoDiscount();

    public OrderBuilder id(int id) { this.id = id; return this; }
    public OrderBuilder customer(String name) { this.customerName = name; return this; }
    public OrderBuilder addMeal(Meal meal) { meals.add(meal); return this; }
    public OrderBuilder discount(DiscountStrategy strategy) { this.discount = strategy; return this; }

    public Order build() {
        System.out.println("[Builder] Building order #" + id);
        Order order = new Order(id, customerName);
        for (Meal m : meals) order.addMeal(m);
        double subtotal = meals.stream().mapToDouble(Meal::getPrice).sum();
        order.setDiscount(discount.calculate(subtotal));
        System.out.println("[Strategy] Applied: " + discount.getName());
        return order;
    }
}
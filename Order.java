package com.restaurant.order;

import com.restaurant.meal.Meal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private final String customerName;
    private final List<Meal> meals = new ArrayList<>();
    private OrderStatus status = OrderStatus.PLACED;
    private final List<OrderObserver> observers = new ArrayList<>();
    private double discount = 0.0;

    public Order(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public void addMeal(Meal meal) { meals.add(meal); }
    public void setDiscount(double discount) { this.discount = discount; }

    public void addObserver(OrderObserver observer) { observers.add(observer); }

    public void setStatus(OrderStatus newStatus) {
        if (!isValidTransition(status, newStatus)) {
            System.out.println("[Validation] Invalid transition: " + status + " -> " + newStatus);
            return;
        }
        this.status = newStatus;
        System.out.println("[State] Status changed to " + status);
        notifyObservers();
    }

    private boolean isValidTransition(OrderStatus from, OrderStatus to) {
        return switch (from) {
            case PLACED -> to == OrderStatus.PREPARING;
            case PREPARING -> to == OrderStatus.READY;
            case READY -> to == OrderStatus.DELIVERED;
            default -> false;
        };
    }

    private void notifyObservers() {
        for (OrderObserver o : observers) o.update(this);
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public OrderStatus getStatus() { return status; }

    public double getTotal() {
        double sum = meals.stream().mapToDouble(Meal::getPrice).sum();
        return Math.max(0, sum - discount);
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        for (Meal m : meals) {
            sb.append("  - ").append(m.getDescription())
              .append(" ($").append(String.format("%.2f", m.getPrice())).append(")\n");
        }
        return sb.toString();
    }
}
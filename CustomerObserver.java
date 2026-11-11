package com.restaurant.order;

public class CustomerObserver implements OrderObserver {
    private final String name;

    public CustomerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("[Observer] [" + name + "] Order #" + order.getId() + " is " + order.getStatus());
    }
}
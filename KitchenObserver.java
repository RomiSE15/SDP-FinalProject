package com.restaurant.order;

public class KitchenObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("[Observer] [Kitchen] Order #" + order.getId() + " -> " + order.getStatus());
    }
}
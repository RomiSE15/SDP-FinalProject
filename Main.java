package com.restaurant;

import com.restaurant.order.Order;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RestaurantFacade facade = new RestaurantFacade();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Restaurant Ordering System ===");
        System.out.println("Patterns: Factory Method, Decorator, Observer, Strategy, Builder, Facade");
        System.out.println("Authors: Ramazan (Factory+Decorator), Islam (Observer+Strategy), Yerassyl (Facade+Builder)\n");

        facade.showMenu();

        Order order = facade.placeOrder(sc);

        while (order.getStatus() != com.restaurant.order.OrderStatus.DELIVERED) {
            System.out.print("Update status? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
            facade.updateStatus(order, sc);
        }

        System.out.println("Done. Final total: $" + String.format("%.2f", order.getTotal()));
        sc.close();
    }
}
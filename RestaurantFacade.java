package com.restaurant;

import com.restaurant.creator.*;
import com.restaurant.discount.*;
import com.restaurant.meal.*;
import com.restaurant.order.*;
import java.util.Scanner;

public class RestaurantFacade {
    private int nextId = 1;

    public void showMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Burger  ($8.99)");
        System.out.println("2. Pizza   ($12.50)");
        System.out.println("3. Salad   ($6.75)");
        System.out.println("4. Pasta   ($10.00)");
        System.out.println("Toppings: cheese, chicken, sauce (can stack)");
        System.out.println("Discounts: none, student, happyhour");
        System.out.println("============\n");
    }

    public Order placeOrder(Scanner sc) {
        System.out.print("Customer name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Guest";

        OrderBuilder builder = new OrderBuilder()
                .id(nextId++)
                .customer(name);

        int count = 0;
        while (count <= 0) {
            System.out.print("How many meals? ");
            try {
                count = Integer.parseInt(sc.nextLine().trim());
                if (count <= 0) System.out.println("Enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }

        for (int i = 0; i < count; i++) {
            Meal meal = null;
            while (meal == null) {
                System.out.print("Meal type (burger/pizza/salad/pasta): ");
                String type = sc.nextLine().trim().toLowerCase();
                try {
                    meal = createMeal(type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown meal. Try again.");
                }
            }

            System.out.print("Toppings (comma separated, empty for none): ");
            String tops = sc.nextLine().trim();
            if (!tops.isEmpty()) {
                for (String t : tops.split(",")) {
                    meal = applyTopping(meal, t.trim().toLowerCase());
                }
            }
            builder.addMeal(meal);
        }

        System.out.print("Discount (none/student/happyhour): ");
        String disc = sc.nextLine().trim().toLowerCase();
        builder.discount(getDiscount(disc));

        Order order = builder.build();

        order.addObserver(new CustomerObserver(name));
        order.addObserver(new KitchenObserver());
        System.out.println("[Observer] Subscribed Customer and Kitchen");

        System.out.println("\n=== Order #" + order.getId() + " ===");
        System.out.println(order.getDescription());
        System.out.println("Total: $" + String.format("%.2f", order.getTotal()));
        System.out.println();

        return order;
    }

    public void updateStatus(Order order, Scanner sc) {
        System.out.println("Current: " + order.getStatus());
        System.out.print("New status (preparing/ready/delivered): ");
        String s = sc.nextLine().trim().toUpperCase();
        try {
            order.setStatus(OrderStatus.valueOf(s));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status");
        }
    }

    private Meal createMeal(String type) {
        MealCreator creator = switch (type) {
            case "burger" -> new BurgerCreator();
            case "pizza"  -> new PizzaCreator();
            case "salad"  -> new SaladCreator();
            case "pasta"  -> new PastaCreator();
            default -> throw new IllegalArgumentException("Unknown meal: " + type);
        };
        return creator.orderMeal();
    }

    private Meal applyTopping(Meal meal, String topping) {
        return switch (topping) {
            case "cheese"  -> new CheeseDecorator(meal);
            case "chicken" -> new ChickenDecorator(meal);
            case "sauce"   -> new ExtraSauceDecorator(meal);
            default -> meal;
        };
    }

    private DiscountStrategy getDiscount(String type) {
        return switch (type) {
            case "student"   -> new StudentDiscount();
            case "happyhour" -> new HappyHourDiscount();
            default          -> new NoDiscount();
        };
    }
}
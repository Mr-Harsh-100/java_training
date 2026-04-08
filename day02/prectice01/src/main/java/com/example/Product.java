package com.example;

import lombok.ToString;

@ToString
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        System.out.println("getName() called for " + name);
        return name;
    }

    public double getPrice() {
        System.out.println("getPrice() called for " + name);
        return price;
    }
}
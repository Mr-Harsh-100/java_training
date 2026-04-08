package com.example;

import com.example.model.Product;

public class Main {
    public static void main(String[] args) {
//        Product p1 = new Product();
        Product p2 = new Product(1L, "Phone", 500.0,"discription");
        System.out.println(p2);
    }
}
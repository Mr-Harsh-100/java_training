package com.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("car04",4000),
                new Product("bike03",7000),
                new Product("car02",2000),
                new Product("car03",3000),
                new Product("car01",1000),
                new Product("bike04",8000),
                new Product("truck02",10000),
                new Product("truck01",9000),
                new Product("bike01",5000),
                new Product("truck03",11000),
                new Product("bike02",6000),
                new Product("truck04",12000)
        );

        List<String> result = products.stream()
                .filter(p -> {
                    System.out.println("Filtering...");
                    return p.getPrice() >= 1000;
                }).map(p->{
                    System.out.println("Mapping...");
                    return p.toString() + '\n';
                }).sorted()
                .toList();

        System.out.println(result);
    }
}
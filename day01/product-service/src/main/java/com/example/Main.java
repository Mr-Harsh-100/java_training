package com.example;

import com.example.model.Product;
import com.example.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
//        Product p1 = new Product();
        Product p2 = new Product(1L, "Phone", 500.0,"discription");
        System.out.println(p2);

        User yagnik = new User("harsh",10);
        yagnik.setName("harsh");

        Map<Long, User> myMap = new HashMap<>();
        myMap.put(1L,new User("harsh",10));
        myMap.put(2L,yagnik);

        Set<User> mySet = new HashSet<>();
        mySet.add(new User("harsh",10));
        mySet.add(yagnik);


        System.out.println(myMap);
        System.out.println(mySet);

        Function<User,Boolean> isAudult = u -> u.getAge() > 18;
        System.out.println(isAudult.apply(yagnik));
        // test

    }


}
package com.example;

import com.example.logger.Logger;
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
        yagnik.setName("yagnik");

        Map<Long, User> myMap = new HashMap<>();
        myMap.put(1L,new User("harsh",10));
        myMap.put(2L,yagnik);

        Set<User> mySet = new HashSet<>();
        mySet.add(new User("harsh",10));
        mySet.add(yagnik);


        Logger.log(myMap);
        System.out.println(mySet);

        Function<User,Boolean> isAudult = u -> u.getAge() > 18;
        System.out.println(isAudult.apply(yagnik));
        // test
        Logger.log("is this Object is named harsh :" + new Logger.inner());


    }

    public static boolean isThisHarsh(User u){
        return u.getName().toLowerCase() == "harsh";
    }


}
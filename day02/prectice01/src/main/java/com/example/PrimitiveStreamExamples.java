package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.OptionalDouble;
import java.util.IntSummaryStatistics;

public class PrimitiveStreamExamples {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1, 9, 7, 3, 6, 4};

        // 1. sum() - Calculate sum of elements
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum(): " + sum); // Output: 45

        // 2. average() - Calculate average
        OptionalDouble average = Arrays.stream(numbers).average();
        System.out.println("average(): " + average.orElse(0.0)); // Output: 5.0

        // 3. summaryStatistics() - Get all statistics at once
        IntSummaryStatistics stats = Arrays.stream(numbers).summaryStatistics();
        System.out.println("summaryStatistics():");
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Average: " + stats.getAverage());

        // More primitive stream examples
        LongStream longStream = LongStream.range(1, 6);
        long longSum = longStream.sum();
        System.out.println("LongStream sum: " + longSum); // Output: 15

        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);
        double doubleSum = doubleStream.sum();
        System.out.println("DoubleStream sum: " + doubleSum); // Output: 6.6
    }
}

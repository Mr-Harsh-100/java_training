package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class IntermediateOperationsExamples {
    public static void main(String[] args) {

        // 1. filter() - Filter elements based on condition
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.print("filter(): ");
        numbers.stream()
                .filter(n -> n % 2 == 0)  // Keep only even numbers
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 2 4 6 8 10

        // 2. map() - Transform elements
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        System.out.print("map(): ");
        names.stream()
                .map(String::toUpperCase)  // Convert to uppercase
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: ALICE BOB CHARLIE

        // 3. mapToInt() - Convert to primitive int stream
        List<String> stringNumbers = Arrays.asList("1", "2", "3", "4");
        IntStream intStream = stringNumbers.stream().mapToInt(Integer::parseInt);
        System.out.print("mapToInt(): ");
        intStream.forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 1 2 3 4

        // 4. mapToLong() - Convert to primitive long stream
        List<String> longStrings = Arrays.asList("100", "200", "300");
        LongStream longStream = longStrings.stream().mapToLong(Long::parseLong);
        System.out.print("mapToLong(): ");
        longStream.forEach(l -> System.out.print(l + "L "));
        System.out.println(); // Output: 100L 200L 300L

        // 5. mapToDouble() - Convert to primitive double stream
        List<String> doubleStrings = Arrays.asList("1.5", "2.5", "3.5");
        DoubleStream doubleStream = doubleStrings.stream().mapToDouble(Double::parseDouble);
        System.out.print("mapToDouble(): ");
        doubleStream.forEach(d -> System.out.print(d + " "));
        System.out.println(); // Output: 1.5 2.5 3.5

        // 6. flatMap() - Flatten nested structures
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D", "E"),
                Arrays.asList("F")
        );
        System.out.print("flatMap(): ");
        nestedList.stream()
                .flatMap(Collection::stream)  // Flatten lists into single stream
                .forEach(s -> System.out.print(s + " "));
        System.out.println(); // Output: A B C D E F

        // 7. flatMapToInt() - Flatten to int stream
        List<List<Integer>> nestedInts = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6)
        );
        System.out.print("flatMapToInt(): ");
        nestedInts.stream()
                .flatMapToInt(list -> list.stream().mapToInt(Integer::intValue))
                .forEach(i -> System.out.print(i + " "));
        System.out.println(); // Output: 1 2 3 4 5 6

        // 8. flatMapToLong() - Flatten to long stream
        List<List<Long>> nestedLongs = Arrays.asList(
                Arrays.asList(100L, 200L),
                Arrays.asList(300L, 400L)
        );
        System.out.print("flatMapToLong(): ");
        nestedLongs.stream()
                .flatMapToLong(list -> list.stream().mapToLong(Long::longValue))
                .forEach(l -> System.out.print(l + "L "));
        System.out.println();

        // 9. flatMapToDouble() - Flatten to double stream
        List<List<Double>> nestedDoubles = Arrays.asList(
                Arrays.asList(1.1, 2.2),
                Arrays.asList(3.3, 4.4)
        );
        System.out.print("flatMapToDouble(): ");
        nestedDoubles.stream()
                .flatMapToDouble(list -> list.stream().mapToDouble(Double::doubleValue))
                .forEach(d -> System.out.print(d + " "));
        System.out.println();

        // 10. distinct() - Remove duplicates
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        System.out.print("distinct(): ");
        duplicates.stream()
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 1 2 3 4 5

        // 11. sorted() - Sort elements
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.print("sorted(): ");
        unsorted.stream()
                .sorted()  // Natural order
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 1 2 3 5 8 9

        System.out.print("sorted(reverse): ");
        unsorted.stream()
                .sorted(Comparator.reverseOrder())  // Reverse order
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 9 8 5 3 2 1

        // 12. peek() - Inspect elements without modifying
        System.out.print("peek(): ");
        numbers.stream()
                .filter(n -> n > 5)
                .peek(n -> System.out.print("Filtered:" + n + " "))
                .map(n -> n * 2)
                .forEach(n -> System.out.print("Result:" + n + " "));
        System.out.println();
        // Output: Filtered:6 Result:12 Filtered:7 Result:14 ...

        // 13. limit() - Limit number of elements
        System.out.print("limit(): ");
        Stream.iterate(1, n -> n + 1)
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 1 2 3 4 5

        // 14. skip() - Skip first n elements
        System.out.print("skip(): ");
        numbers.stream()
                .skip(5)  // Skip first 5 elements
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 6 7 8 9 10

        // 15. takeWhile() - Take elements while condition is true (Java 9+)
        List<Integer> nums = Arrays.asList(2, 4, 6, 8, 3, 5, 7);
        System.out.print("takeWhile(): ");
        nums.stream()
                .takeWhile(n -> n % 2 == 0)  // Take while even
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 2 4 6 8

        // 16. dropWhile() - Drop elements while condition is true (Java 9+)
        System.out.print("dropWhile(): ");
        nums.stream()
                .dropWhile(n -> n % 2 == 0)  // Drop while even
                .forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: 3 5 7
    }
}

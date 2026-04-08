package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

public class TerminalOperationsExamples {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9, 7, 2, 6, 4);

        // 1. forEach() - Process each element
        System.out.print("forEach(): ");
        names.stream().forEach(name -> System.out.print(name + " "));
        System.out.println();

        // 2. forEachOrdered() - Process in encounter order (important for parallel)
        System.out.print("forEachOrdered(): ");
        names.parallelStream()
                .forEachOrdered(name -> System.out.print(name + " "));
        System.out.println();

        // 3. toList() - Collect to List (Java 16+)
        List<String> nameList = names.stream()
                .filter(n -> n.length() > 3)
                .toList();  // Immutable list
        System.out.println("toList(): " + nameList); // Output: [Alice, Charlie, David]

        // 4. collect() - Collect to various collections
        List<String> mutableList = names.stream()
                .collect(Collectors.toList());
        Set<String> nameSet = names.stream()
                .collect(Collectors.toSet());
        String joined = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("collect() joined: " + joined); // Alice, Bob, Charlie, David, Eve

        // 5. reduce() - Reduce stream to single value
        Optional<Integer> sum = numbers.stream()
                .reduce((a, b) -> a + b);
        System.out.println("reduce(): " + sum.orElse(0)); // Output: 45

        Integer sumWithIdentity = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("reduce(identity): " + sumWithIdentity); // Output: 45

        // 6. min() - Find minimum element
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println("min(): " + min.orElse(0)); // Output: 1

        // 7. max() - Find maximum element
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println("max(): " + max.orElse(0)); // Output: 9

        // 8. count() - Count elements
        long count = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("count(): " + count); // Output: 4 (even numbers: 8, 2, 6, 4)

        // 9. anyMatch() - Check if any element matches condition
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("anyMatch(): " + hasEven); // Output: true

        // 10. allMatch() - Check if all elements match condition
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("allMatch(): " + allPositive); // Output: true

        // 11. noneMatch() - Check if no elements match condition
        boolean noneGreaterThan10 = numbers.stream().noneMatch(n -> n > 10);
        System.out.println("noneMatch(): " + noneGreaterThan10); // Output: true

        // 12. findFirst() - Find first element
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        System.out.println("findFirst(): " + first.orElse(0)); // Output: 8

        // 13. findAny() - Find any element (useful for parallel)
        Optional<Integer> any = numbers.parallelStream()
                .filter(n -> n > 5)
                .findAny();
        System.out.println("findAny(): " + any.orElse(0));

        // 14. toArray() - Convert to array
        String[] nameArray = names.stream()
                .filter(n -> n.startsWith("A"))
                .toArray(String[]::new);
        System.out.println("toArray(): " + Arrays.toString(nameArray)); // Output: [Alice]
    }
}

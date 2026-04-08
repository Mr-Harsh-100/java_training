package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.ThreadLocalRandom;

public class StreamCreationExamples {
    public static void main(String[] args) {
        // 1. stream() - From Collection
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Stream<String> nameStream = names.stream();
        System.out.print("stream(): ");
        nameStream.forEach(n -> System.out.print(n + " "));
        System.out.println(); // Output: Alice Bob Charlie

        // 2. parallelStream() - Parallel processing
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.parallelStream()
                .forEach(n -> System.out.print(Thread.currentThread().getName() + ":" + n + " "));
        System.out.println();

        // 3. Stream.of() - From individual elements
        Stream<String> fruitStream = Stream.of("Apple", "Banana", "Orange");
        System.out.print("Stream.of(): ");
        fruitStream.forEach(f -> System.out.print(f + " "));
        System.out.println(); // Output: Apple Banana Orange

        // 4. Arrays.stream() - From array
        int[] array = {10, 20, 30, 40, 50};
        IntStream intStream = Arrays.stream(array);
        System.out.print("Arrays.stream(): ");
        intStream.forEach(i -> System.out.print(i + " "));
        System.out.println(); // Output: 10 20 30 40 50

        // 5. Stream.generate() - Infinite stream with supplier
        Stream<Integer> randomStream = Stream.generate(() ->
                ThreadLocalRandom.current().nextInt(100)).limit(5);
        System.out.print("Stream.generate(): ");
        randomStream.forEach(i -> System.out.print(i + " "));
        System.out.println();

        // 6. Stream.iterate() - Sequential infinite stream
        Stream<Integer> iterateStream = Stream.iterate(1, n -> n + 2).limit(5);
        System.out.print("Stream.iterate(): ");
        iterateStream.forEach(i -> System.out.print(i + " "));
        System.out.println(); // Output: 1 3 5 7 9

        // 7. Stream.concat() - Concatenate two streams
        Stream<String> stream1 = Stream.of("A", "B", "C");
        Stream<String> stream2 = Stream.of("D", "E", "F");
        Stream<String> concatStream = Stream.concat(stream1, stream2);
        System.out.print("Stream.concat(): ");
        concatStream.forEach(s -> System.out.print(s + " "));
        System.out.println(); // Output: A B C D E F
    }
}
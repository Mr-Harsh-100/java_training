package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Employee {
    private String name;
    private int age;
    private double salary;
    private String department;

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("%s(%d, %.2f, %s)", name, age, salary, department);
    }
}

public class CompleteStreamExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, 50000, "IT"),
                new Employee("Bob", 30, 60000, "HR"),
                new Employee("Charlie", 35, 75000, "IT"),
                new Employee("David", 28, 55000, "Finance"),
                new Employee("Eve", 32, 70000, "IT"),
                new Employee("Frank", 40, 80000, "Finance"),
                new Employee("Grace", 26, 52000, "HR")
        );

        System.out.println("=== Stream Operations Demo ===\n");

        // 1. Filter IT department employees
        System.out.println("1. IT Department Employees:");
        employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .forEach(System.out::println);

        // 2. Map to employee names and sort
        System.out.println("\n2. Employee names sorted:");
        employees.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(name -> System.out.print(name + " "));

        // 3. Calculate average salary by department
        System.out.println("\n\n3. Average salary by department:");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .forEach((dept, avg) ->
                        System.out.printf("  %s: $%.2f%n", dept, avg));

        // 4. Find highest paid employee
        System.out.println("\n4. Highest paid employee:");
        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(System.out::println);

        // 5. Count employees by age group
        System.out.println("\n5. Employees by age group:");
        employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getAge() < 30 ? "Young (<30)" : "Senior (>=30)",
                        Collectors.counting()
                ))
                .forEach((group, count) ->
                        System.out.printf("  %s: %d%n", group, count));

        // 6. Get summary statistics of salaries
        System.out.println("\n6. Salary Statistics:");
        DoubleSummaryStatistics salaryStats = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();
        System.out.printf("  Average: $%.2f%n", salaryStats.getAverage());
        System.out.printf("  Min: $%.2f%n", salaryStats.getMin());
        System.out.printf("  Max: $%.2f%n", salaryStats.getMax());
        System.out.printf("  Total: $%.2f%n", salaryStats.getSum());

        // 7. Parallel stream for large dataset simulation
        System.out.println("\n7. Parallel processing demo:");
        long start = System.currentTimeMillis();
        employees.parallelStream()
                .filter(e -> e.getSalary() > 60000)
                .forEach(e -> System.out.println("  " + Thread.currentThread().getName()
                        + ": " + e.getName()));
        long end = System.currentTimeMillis();
        System.out.println("  Time: " + (end - start) + "ms");
    }
}
package com.github.adangel.java8samples;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {
    private List<String> list = Arrays.asList("I", "B", "M");
    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

    @Test
    public void testFind() {
        Optional<String> found;
        found = list.stream().filter(s -> s.equals("B")).findFirst();
        System.out.println("Searched for B: " + found.get());

        found = list.stream().filter(s -> s.equals("X")).findFirst();
        System.out.println("Searched for X: " + found.orElse(" fallback value "));
    }
    
    public static void action(String s) {
        System.out.println(s);
    }
    @Test
    public void testForeach() {
        list.stream().sorted(String.CASE_INSENSITIVE_ORDER).forEach(System.out::println);

        System.out.println("--------------");
        Consumer<String> action = StreamTest::action;
        list.stream().sorted(Comparator.reverseOrder()).forEach(action);

        System.out.println("--------------");
        list.forEach(StreamTest::action);
    }

    @Test
    public void testMap() {
        String output = list.stream()
                .map(s -> String.valueOf((char)(s.charAt(0) - 1)))
                .collect(Collectors.joining(","));
        System.out.println(output);
    }

    @Test
    public void testAggregations() {
        System.out.println(numbers.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(",")));
        System.out.println("sum: " + numbers.stream().mapToInt(i -> i).sum());
        System.out.println("avg: " + numbers.stream().mapToInt(i -> i).average().getAsDouble());
        System.out.println("max: " + numbers.stream().mapToInt(i -> i).max().getAsInt());
        System.out.println("min: " + numbers.stream().mapToInt(i -> i).min().getAsInt());
    }

    @Test
    public void testParallel() {
        Collections.shuffle(numbers);
        System.out.println(numbers);

        System.out.println("sorted: " + numbers.parallelStream()
                .sorted()
                .map(i -> String.valueOf(i))
                .collect(Collectors.joining(",")));
    }
}

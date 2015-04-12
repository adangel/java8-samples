package com.github.adangel.java8samples;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import org.junit.Test;

public class LambdaTest {

    public class MyCollectionOfStrings {
        private Collection<String> strings = Arrays.asList("A", "B", "C");

        public String find(Function<String, Boolean> predicate) {
            String result = null;
            for (String s : strings) {
                if (predicate.apply(s)) {
                    result = s;
                    break;
                }
            }
            return result;
        }
    }

    private MyCollectionOfStrings col = new MyCollectionOfStrings();

    @Test
    public void testFind() {
        System.out.println("Find C: " + col.find(new Function<String, Boolean>() {
            @Override
            public Boolean apply(String t) {
                return t.equals("C");
            }
        }));
        System.out.println("Find B: " + col.find(s -> s.equals("B")));
        System.out.println("Find X: " + col.find(s -> s.equals("X")));
    }
}

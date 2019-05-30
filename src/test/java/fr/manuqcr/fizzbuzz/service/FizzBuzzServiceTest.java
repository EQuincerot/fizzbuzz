package fr.manuqcr.fizzbuzz.service;

import fr.manuqcr.fizzbuzz.model.Request;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

class FizzBuzzServiceTest {

    @ParameterizedTest
    @MethodSource("params")
    void testFizzBuzz(Request r, List<String> expectedResult) {
        assertEquals(expectedResult, new FizzBuzzService().fizzBuzz(r));
    }


    static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(new Request(3, 5, 1, "fizz", "buzz"),
                        asList("1")),
                Arguments.of(new Request(3, 5, 5, "fizz", "buzz"),
                        asList("1", "2", "fizz", "4", "buzz")),
                Arguments.of(new Request(3, 5, 10, "fizz", "buzz"),
                        asList("1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz", "buzz")),
                Arguments.of(new Request(2, 3, 6, "fizz", "buzz"),
                        asList("1", "fizz", "buzz", "fizz", "5", "fizzbuzz"))
        );
    }

    @ParameterizedTest
    @MethodSource("moduloParams")
    void testModulo(int i, int factor, boolean expected) {
        assertEquals(expected, new FizzBuzzService().isFactor(i,factor));
    }

    static Stream<Arguments> moduloParams() {
        return Stream.of(
                Arguments.of(6, 3, true),
                Arguments.of(3, 6, false),
                Arguments.of(6,4, false)
        );
    }


}
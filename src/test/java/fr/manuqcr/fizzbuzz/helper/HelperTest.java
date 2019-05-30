package fr.manuqcr.fizzbuzz.helper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HelperTest {

    @ParameterizedTest
    @MethodSource("invalidPositiveInt")
    void testIsPositiveInt_not_valid(Integer i) {
        assertThat(Helper.isPositiveInt(i)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("validPositiveInt")
    void testIsPositiveInt_valid(Integer i) {
        assertThat(Helper.isPositiveInt(i)).isTrue();
    }

    static Stream<Integer> invalidPositiveInt() {
        return Stream.of(null, 0, -1, -100);
    }

    static Stream<Integer> validPositiveInt() {
        return Stream.of(1, 100, 100000);
    }

    @ParameterizedTest
    @MethodSource("validString")
    void testIsNotNullWord_valid(String s) {
        assertThat(Helper.isNotNullWord(s)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidString")
    void testIsNotNullWord_invalid(String s) {
        assertThat(Helper.isNotNullWord(s)).isFalse();
    }

    private static Stream<String> validString() {
        return Stream.of("a", "azerty", "fizz", "buzz", "multiple words together");
    }

    private static Stream<String> invalidString() {
        return Stream.of(null, "",
                "&", "& are forbidden",
                "%", "% are forbidden",
                "\"", "\" are forbidden");
    }
}
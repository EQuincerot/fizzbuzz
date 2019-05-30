package fr.manuqcr.fizzbuzz.helper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HelperTest {

    @ParameterizedTest
    @MethodSource("invalidPositiveInt")
    void testIsPositiveInt_not_valid(Integer i) {
        Assertions.assertThat(Helper.isPositiveInt(i)).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("validPositiveInt")
    void testIsPositiveInt_valid(Integer i) {
        Assertions.assertThat(Helper.isPositiveInt(i)).isEqualTo(true);
    }

    static Stream<Integer> invalidPositiveInt() {
        return Stream.of(null, 0, -1, -100);
    }

    static Stream<Integer> validPositiveInt() {
        return Stream.of(1, 100, 100000);
    }
}
package fr.manuqcr.fizzbuzz.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void equals_false_for_different_objects() {
        Request some = new Request(3, 5, 10,"a", "b");
        Assertions.assertThat(some).isNotEqualTo(null);
        Assertions.assertThat(some).isNotEqualTo("fdsfsd");
    }

    @Test
    void equals_false_for_request_with_different_paramters_are_not_equal() {
        Request some = new Request(3, 5, 10,"a", "b");
        Request other = new Request(5, 6, 4,"b", "c");
        Assertions.assertThat(some).isNotEqualTo(other);

        Assertions.assertThat(some.hashCode()).isNotEqualTo(other.hashCode());
    }

    @Test
    void equals_true() {
        Request some = new Request(3, 5, 10,"a", "b");
        Request same = new Request(3, 5, 10,"a", "b");
        Assertions.assertThat(some).isEqualTo(some);
        Assertions.assertThat(some).isEqualTo(same);

        Assertions.assertThat(some.hashCode()).isEqualTo(some.hashCode());
        Assertions.assertThat(some.hashCode()).isEqualTo(same.hashCode());
    }

}
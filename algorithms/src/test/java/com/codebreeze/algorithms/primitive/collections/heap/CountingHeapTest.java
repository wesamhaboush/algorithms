package com.codebreeze.algorithms.primitive.collections.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CountingHeapTest {

    @Test
    void vanilla_case_min_high_repetition() {
        // given
        int repetition = 10;
        // when
        CountingHeap<String> sh = new CountingHeap<>(String::compareToIgnoreCase);
        for(int i = 0; i < repetition; i++) {
            Stream.of("c", "a", "D", "B").forEach(sh::insert);
        }

        // then
        assertThat(sh.size()).isEqualTo(40);
        assertThat(sh.isEmpty()).isFalse();
        for(int i = 0; i < repetition; i++) {
            assertThat(sh.extractM()).isEqualTo("a");
        }
        for(int i = 0; i < repetition; i++) {
            assertThat(sh.extractM()).isEqualTo("B");
        }
        for(int i = 0; i < repetition; i++) {
            assertThat(sh.extractM()).isEqualTo("c");
        }
        for(int i = 0; i < repetition; i++) {
            assertThat(sh.extractM()).isEqualTo("D");
        }
        for(int i = 0; i < repetition; i++) {
            assertThatThrownBy(sh::extractM).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Test
    void vanilla_case_min() {
        // given
        // when
        CountingHeap<String> sh = new CountingHeap<>(String::compareToIgnoreCase);
        Stream.of("c", "a", "D", "B").forEach(sh::insert);

        // then
        assertThat(sh.size()).isEqualTo(4);
        assertThat(sh.isEmpty()).isFalse();
        assertThat(sh.extractM()).isEqualTo("a");
        assertThat(sh.extractM()).isEqualTo("B");
        assertThat(sh.extractM()).isEqualTo("c");
        assertThat(sh.extractM()).isEqualTo("D");
        assertThatThrownBy(sh::extractM).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void vanilla_case_max() {
        // given
        // when
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        CountingHeap<String> sh = new CountingHeap<>(compareToIgnoreCase.reversed());
        Stream.of("c", "a", "D", "B").forEach(sh::insert);

        // then
        assertThat(sh.size()).isEqualTo(4);
        assertThat(sh.isEmpty()).isFalse();
        assertThat(sh.extractM()).isEqualTo("D");
        assertThat(sh.extractM()).isEqualTo("c");
        assertThat(sh.extractM()).isEqualTo("B");
        assertThat(sh.extractM()).isEqualTo("a");
        assertThatThrownBy(sh::extractM).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void fails_for_null() {
        // given/when/then
        assertThatThrownBy(() -> new CountingHeap<>(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new CountingHeap<>(comparingInt(Object::hashCode)).insert(null))
            .isInstanceOf(NullPointerException.class);
    }
}

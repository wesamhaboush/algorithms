package com.codebreeze.algorithms.primitive.collections.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleHeapTest {

    @Test
    void vanilla_case_min() {
        // given
        // when
        SimpleHeap<String> sh = new SimpleHeap<>(false, String::compareToIgnoreCase);
        Stream.of("c", "a", "D", "B").forEach(sh::insert);

        // then
        StringBuilder sb = new StringBuilder();
        sh.printHorizontal(sb);
        System.out.println(sb);
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
        SimpleHeap<String> sh = new SimpleHeap<>(true, String::compareToIgnoreCase);
        Stream.of("c", "a", "D", "B").forEach(sh::insert);

        // then
        StringBuilder sb = new StringBuilder();
        sh.printVertical(sb);
        System.out.println(sb);
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
        assertThatThrownBy(() -> new SimpleHeap<>(true,null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SimpleHeap<>(true, Comparator.comparingInt(Object::hashCode)).insert(null))
            .isInstanceOf(NullPointerException.class);
    }
}

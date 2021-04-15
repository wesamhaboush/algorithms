package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public abstract class AbstractRotateArray {

    @Test
    public void accept() {
        // given
        String[] original = {"a", "b", "c", "d", "e", "f", "g"};

        // when
        rotateArray().accept(original, 3);

        // then
        assertThat(original).containsExactly("d", "e", "f", "g", "a", "b", "c");
    }

    abstract <T> BiConsumer<T[], Integer> rotateArray();
}

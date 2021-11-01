package com.codebreeze.algorithms.rotate;


import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


abstract class AbstractRotateArrayTest {

    @Test
    public void accept_3() {
        // given
        System.out.println("3 shifts test:");
        String[] original = {"a", "b", "c", "d", "e", "f", "g"};

        // when
        BiConsumer<Object[], Integer> rotateArray = rotateArray();
        System.out.println("running " + rotateArray.getClass().getName());
        rotateArray.accept(original, 3);

        // then
        assertThat(original).containsExactly("d", "e", "f", "g", "a", "b", "c");
    }

    @Test
    public void accept_5() {
        // given
        System.out.println("5 shifts test:");
        String[] original = {"a", "b", "c", "d", "e", "f", "g"};

        // when
        BiConsumer<Object[], Integer> rotateArray = rotateArray();
        System.out.println("running " + rotateArray.getClass().getName());
        rotateArray.accept(original, 5);

        // then
        assertThat(original).containsExactly("f", "g", "a", "b", "c", "d", "e");
    }

    abstract <T> BiConsumer<T[], Integer> rotateArray();
}

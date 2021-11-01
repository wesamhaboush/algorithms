package com.codebreeze.algorithms.rotate;

import java.util.function.BiConsumer;


public class JuggleRotateArrayTest extends AbstractRotateArrayTest {

    <T> BiConsumer<T[], Integer> rotateArray() {
        return new JuggleRotateArray<>();
    }
}

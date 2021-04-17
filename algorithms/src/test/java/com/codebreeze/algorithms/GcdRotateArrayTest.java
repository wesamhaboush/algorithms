package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

public class GcdRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new GcdRotateArray<>();
    }
}

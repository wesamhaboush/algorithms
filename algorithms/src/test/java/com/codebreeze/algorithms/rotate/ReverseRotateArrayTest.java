package com.codebreeze.algorithms.rotate;

import java.util.function.BiConsumer;

class ReverseRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new ReverseRotateArray<>();
    }
}

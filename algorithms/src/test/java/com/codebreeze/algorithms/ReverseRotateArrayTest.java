package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

public class ReverseRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new ReverseRotateArray<>();
    }
}

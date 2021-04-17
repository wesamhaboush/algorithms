package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

public class SlideRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new SlideRotateArray<>();
    }
}

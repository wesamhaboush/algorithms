package com.codebreeze.algorithms.rotate;

import java.util.function.BiConsumer;

class SlideRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new SlideRotateArray<>();
    }
}

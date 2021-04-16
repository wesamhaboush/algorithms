package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

public class SlideRotateArrayTest extends AbstractRotateArray {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new SlideRotateArray<>();
    }
}

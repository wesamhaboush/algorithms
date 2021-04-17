package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

public class JuggleModuloRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new JuggleModuloRotateArray<>();
    }
}

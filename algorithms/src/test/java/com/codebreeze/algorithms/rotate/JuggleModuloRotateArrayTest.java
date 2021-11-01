package com.codebreeze.algorithms.rotate;

import com.codebreeze.algorithms.rotate.AbstractRotateArrayTest;
import com.codebreeze.algorithms.rotate.JuggleModuloRotateArray;

import java.util.function.BiConsumer;

public class JuggleModuloRotateArrayTest extends AbstractRotateArrayTest {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new JuggleModuloRotateArray<>();
    }
}

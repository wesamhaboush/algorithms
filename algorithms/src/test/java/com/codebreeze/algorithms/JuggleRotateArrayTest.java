package com.codebreeze.algorithms;

import java.util.function.BiConsumer;


public class JuggleRotateArrayTest extends AbstractRotateArray {

    <T> BiConsumer<T[], Integer> rotateArray() {
        return new JuggleRotateArray<>();
    }
}

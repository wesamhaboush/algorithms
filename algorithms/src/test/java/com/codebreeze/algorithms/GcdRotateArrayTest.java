package com.codebreeze.algorithms;

import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class GcdRotateArrayTest extends AbstractRotateArray {

    @Override
    <T> BiConsumer<T[], Integer> rotateArray() {
        return new GcdRotateArray<>();
    }
}

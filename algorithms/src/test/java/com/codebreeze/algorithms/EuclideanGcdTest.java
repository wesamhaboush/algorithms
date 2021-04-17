package com.codebreeze.algorithms;

import java.util.function.IntBinaryOperator;

class EuclideanGcdTest extends AbstractGcdTest {

    @Override
    IntBinaryOperator operator() {
        return new EuclideanGcd();
    }
}

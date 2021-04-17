package com.codebreeze.algorithms;

import java.util.function.IntBinaryOperator;

class GcdTest extends AbstractGcdTest {

    @Override
    IntBinaryOperator operator() {
        return new Gcd();
    }
}

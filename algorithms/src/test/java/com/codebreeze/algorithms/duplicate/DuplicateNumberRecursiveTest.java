package com.codebreeze.algorithms.duplicate;

import java.util.function.ToIntFunction;

class DuplicateNumberRecursiveTest extends DuplicateNumberTest {

    @Override
    public ToIntFunction<int[]> duplicateNumberImpl() {
        return new DuplicateNumberRecursive();
    }
}

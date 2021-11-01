package com.codebreeze.algorithms.duplicate;

import java.util.function.ToIntFunction;

class DuplicateNumberIterativeTest extends DuplicateNumberTest {

    @Override
    ToIntFunction<int[]> duplicateNumberImpl() {
        return new DuplicateNumberIterative();
    }
}

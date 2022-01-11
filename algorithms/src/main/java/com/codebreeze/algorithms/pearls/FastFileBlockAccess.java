package com.codebreeze.algorithms.pearls;

import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;

public class FastFileBlockAccess implements IntFunction<List<String>> {

    @Override
    public List<String> apply(int blockNumber) {
        if(blockNumber < 0) {
            throw new IllegalArgumentException(
                "cannot operate with indices of blocks less than zero, but got " + blockNumber
            );
        }

        List<String> print = new LinkedList<>();
        path(print, blockNumber);
        return print;
    }

    private void path(List<String> print, int blockNumber) {
        if(blockNumber == 0) {
            print.add("Start at 0");
        } else if(even(blockNumber)) {
            path(print, blockNumber / 2);
            print.add("Double to " + blockNumber);
        } else {
            path(print, blockNumber - 1);
            print.add("Increment to " + blockNumber);
        }
    }

    private boolean even(int blockNumber) {
        return blockNumber % 2 == 0;
    }
}

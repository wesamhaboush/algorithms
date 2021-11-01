package com.codebreeze.algorithms.tape.transpose;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import static java.util.Comparator.comparingInt;

/*
Problem:

In the early 1960s Vic Vyssotsky worked with a programmer who had to transpose a 4000-by-4000 matrix stored on magnetic
tape (each record had the same several-dozen-byte format). The original program his colleague suggested would have taken
50 hours to run; how did Vyssotsky reduce the run time to half an hour?

Solution:

The idea is to do multiple O(N) passes on the tape. 3 passes is what is suggested:
1- Add proposed [column,row] to the element O(n * m)
2- Sort based on the column number, then row number, O(n log n) using merge sort
3- clean up the column, row number from the tape, O(n)
 */
public class TapeTranspose<T> implements Consumer<T[]> {
    @Override
    public void accept(T[] ts) {
        int numberOfRows = (int) Math.sqrt(ts.length);
        if (numberOfRows * numberOfRows != ts.length) {
            throw new IllegalArgumentException("can only operate on square matrices, i.e. length must be perfect square, not this one: " + ts.length);
        }
//        int numberOfColumns = numberOfRows; // square matrix is required for this problem, so number of rows equals number of columns;
        // append to each of them the column,row number, O(n * m)
        List<MatrixIndexedValue<T>> matrixIndexedValues = new LinkedList<>();
        int i = 0;
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfRows; column++) {
                matrixIndexedValues.add(new MatrixIndexedValue<>(ts[i++], row, column));
            }
        }
        // then sort by column number, O(n * m Log (n * m))
        Collections.sort(matrixIndexedValues);

        // then strip the column number O(n)
        for (i = 0; i < matrixIndexedValues.size(); i++) {
            ts[i] = matrixIndexedValues.get(i).value();
        }
    }

    static class MatrixIndexedValue<T> implements Comparable<MatrixIndexedValue<T>> {
        public static final Comparator<MatrixIndexedValue<?>> MATRIX_INDEXED_INT_VALUE_COMPARATOR =
            comparingInt((ToIntFunction<MatrixIndexedValue<?>>) MatrixIndexedValue::column)
                .thenComparingInt(MatrixIndexedValue::row);

        private final T value;
        private final int row;
        private final int column;

        MatrixIndexedValue(T value, int row, int column) {
            this.value = value;
            this.row = row;
            this.column = column;
        }

        private int column() {
            return column;
        }

        private int row() {
            return row;
        }

        private T value() {
            return value;
        }

        @Override
        public int compareTo(MatrixIndexedValue that) {
            return MATRIX_INDEXED_INT_VALUE_COMPARATOR.compare(this, that);
        }
    }
}

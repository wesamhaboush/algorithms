package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SparseMatrixTest {

    /*
0 1 0 0 0
2 5 0 0 8
0 7 0 0 9
0 0 4 0 3

To store a sparse array, we create three arrays

Values:           2 1 5  7 4 8 9 3
Rows:             1 0 1  2 3 1 2 3
First In Columns: 0 1 4 -1 5
                : 0 1 2  3 4
     */
    @Test
    void applyAsInt() {
        // given
        final int[][] matrix = {
            {0, 1, 0, 0, 0},
            {2, 5, 0, 0, 8},
            {0, 7, 0, 0, 9},
            {0, 0, 4, 0, 3}
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        assertThat(sm.applyAsInt(0, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 1)).isEqualTo(1);
        assertThat(sm.applyAsInt(0, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 4)).isEqualTo(0);

        assertThat(sm.applyAsInt(1, 0)).isEqualTo(2);
        assertThat(sm.applyAsInt(1, 1)).isEqualTo(5);
        assertThat(sm.applyAsInt(1, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(1, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(1, 4)).isEqualTo(8);

        assertThat(sm.applyAsInt(2, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 1)).isEqualTo(7);
        assertThat(sm.applyAsInt(2, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 4)).isEqualTo(9);

        assertThat(sm.applyAsInt(3, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 1)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 2)).isEqualTo(4);
        assertThat(sm.applyAsInt(3, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 4)).isEqualTo(3);
        System.out.println(sm);
    }

    @Test
    void applyAsInt_multiple_empty_columns() {
        // given
        final int[][] matrix = {
            {0, 1, 0, 0, 0},
            {2, 5, 0, 0, 8},
            {0, 7, 0, 0, 9},
            {0, 0, 0, 0, 3}
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        assertThat(sm.applyAsInt(0, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 1)).isEqualTo(1);
        assertThat(sm.applyAsInt(0, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(0, 4)).isEqualTo(0);

        assertThat(sm.applyAsInt(1, 0)).isEqualTo(2);
        assertThat(sm.applyAsInt(1, 1)).isEqualTo(5);
        assertThat(sm.applyAsInt(1, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(1, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(1, 4)).isEqualTo(8);

        assertThat(sm.applyAsInt(2, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 1)).isEqualTo(7);
        assertThat(sm.applyAsInt(2, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(2, 4)).isEqualTo(9);

        assertThat(sm.applyAsInt(3, 0)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 1)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 2)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 3)).isEqualTo(0);
        assertThat(sm.applyAsInt(3, 4)).isEqualTo(3);
        System.out.println(sm);
    }

    @Test
    void applyAsInt_all_zeros() {
        // given
        final int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                assertThat(sm.applyAsInt(i, j)).isEqualTo(0);
            }
        }
        System.out.println(sm);
    }

    @Test
    void applyAsInt_very_sparse() {
        // given
        final int[][] matrix = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 0}
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        assertThat(sm.spaceSaving()).isEqualTo(38);
        System.out.println(sm);
    }

    @Test
    void applyAsInt_empty() {
        // given
        final int[][] matrix = {
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        assertThatCode(() -> new SparseMatrix(matrix)).doesNotThrowAnyException();
        System.out.println(sm);
    }

    @Test
    void applyAsInt_failures() {
        // given
        final int[][] unequalRows = {
            {1, 2},
            {1}
        };

        final int[][] someRowsAreNull = {
            {1, 2},
            null
        };

        final int[][] matrix = {
            {1, 2, 0},
            {3, 4, -1},
            {0, 0, -2},
        };

        // when
        SparseMatrix sm = new SparseMatrix(matrix);

        // then
        assertThatThrownBy(() -> new SparseMatrix(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SparseMatrix(unequalRows)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SparseMatrix(someRowsAreNull)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sm.applyAsInt(-1, 2)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sm.applyAsInt(3, 2)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sm.applyAsInt(2, -2)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sm.applyAsInt(2, 3)).isInstanceOf(IllegalArgumentException.class);
        System.out.println(sm);
    }
}

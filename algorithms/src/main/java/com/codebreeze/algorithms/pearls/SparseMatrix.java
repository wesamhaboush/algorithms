package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntBinaryOperator;

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

The first in column array contains the point in the rows array at which values in the column of the index start.
The rows array contains the row number where the corresponding value exist in the values array.

So, we can read the above as:
first column index 0 represents column 0, and its start is at row[0], which says that row number 1 has value 2
second column index 1 represents column 1, starting its row values at row[1] which says that row 0 has value 1,
row 1 has value 5, and row 2 has value 7.
third column index 2 represents column 2, starting its row at row[4], which says that row number 3 has value 4
fourth column index 3 represents column 3, which has no row values (-1),
finally fifth column index represents column 4, and has values starting at row[5], which has row 1 with value 8
 and row 2 with value 9, and row 3 which has value 3.

algorithm to find value at (I, J): (column I, row J)

for K := FirstInColumn[I] to FirstInColumn[I+1]-1 do
    if Row[K] = J then
        // Found it in position K
        return Value[K]
// Unsuccessful search; (I, J) empty
return 0
 */
public class SparseMatrix implements IntBinaryOperator {

    private final int[] values;
    private final int[] rows;
    private final int[] firstRowInColumn;
    private final int rowCount;
    private final int columnCount;

    public SparseMatrix(int[][] sparseMatrix) {
        if (sparseMatrix == null) {
            throw new NullPointerException("cannot operate on null sparse matrix");
        }
        if (Arrays.stream(sparseMatrix).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("cannot operate on a matrix with null rows");
        }
        if (Arrays.stream(sparseMatrix).mapToInt(arr -> arr.length).distinct().toArray().length > 1) {
            throw new IllegalArgumentException("rows have different sizes!! not a matrix then!");
        }
        // this fails if the row arrays have different sizes
        rowCount = sparseMatrix.length;
        columnCount = rowCount == 0 ? 0 :sparseMatrix[0].length;

        firstRowInColumn = new int[columnCount];
        // count how many non zero values
        int valueCount = 0;
        for (int[] row : sparseMatrix) {
            for (int value : row) {
                if (value != 0) {
                    valueCount++;
                }
            }
        }
        rows = new int[valueCount];
        values = new int[valueCount];
        int nextRowsIndex = 0;
        for (int column = 0; column < columnCount; column++) {
            boolean isLookingForFirstRowInColumn = true;
            for (int row = 0; row < rowCount; row++) {
                if (sparseMatrix[row][column] != 0) {
                    rows[nextRowsIndex] = row;
                    values[nextRowsIndex] = sparseMatrix[row][column];
                    if (isLookingForFirstRowInColumn) {
                        firstRowInColumn[column] = nextRowsIndex;
                        isLookingForFirstRowInColumn = false;
                    }
                    nextRowsIndex++;
                }
            }
            if (isLookingForFirstRowInColumn) {
                firstRowInColumn[column] = -1;
            }
        }
    }

    @Override
    public int applyAsInt(int j, int i) {
        if (j > rowCount - 1 || j < 0) {
            throw new IllegalArgumentException("row must be between 0 and " + (rowCount - 1));
        }
        if (i > columnCount - 1 || i < 0) {
            throw new IllegalArgumentException("column must be between 0 and " + (columnCount - 1));
        }

        if (firstRowInColumn[i] == -1) { // no values in that columns!
            return 0;
        }

        int k = firstRowInColumn[i];
        int kMax = i == (firstRowInColumn.length - 1) // check if it is the last column
            ? (rows.length - 1)  // if last column, don't look for next column end
            : (firstRowInColumn[nextColumn(i + 1)] - 1); // if not last column, look for next column with values

        while (k <= kMax) {
            if (rows[k] == j) {
                return values[k];
            }
            k++;
        }
        return 0;
    }

    private int nextColumn(int i) {
        while (i < firstRowInColumn.length && firstRowInColumn[i] == -1) {
            i++;
        }
        return i;
    }

    public int spaceSaving() {
        return (rowCount * columnCount) - (values.length + rows.length + firstRowInColumn.length);
    }

    public double compactnessRatio() {
        return ((double)(values.length + rows.length + firstRowInColumn.length)) / (rowCount * columnCount);
    }

    @Override
    public String toString() {
        return "values:" +
            Arrays.toString(values) +
            '\n' +
            "rows:" +
            Arrays.toString(rows) +
            '\n' +
            "firstRowInColumn:" +
            Arrays.toString(firstRowInColumn) +
            '\n' +
            "space saving:" +
            spaceSaving() +
            '\n' +
            "compactionRatio:" +
            compactnessRatio();
    }
}

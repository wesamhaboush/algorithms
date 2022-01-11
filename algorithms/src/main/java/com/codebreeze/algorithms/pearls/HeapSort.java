package com.codebreeze.algorithms.pearls;

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ArrayUtils.swap;

public class HeapSort implements Consumer<int[]> {

    private final Algorithm algorithm;

    public HeapSort(Algorithm algorithm) {
        this.algorithm = requireNonNull(algorithm, "cannot operate without a null algorithm");
    }

    @Override
    public void accept(int[] x) {
        algorithm.accept(x);
    }

    private static void heapsortUp(int[] x) {
        heapifyUp(x);

        int endIndex = x.length - 1;
        while (endIndex > 0) {
            swap(x, endIndex, 0);
            heapifyUp(x, endIndex);
            endIndex = endIndex - 1;
        }
    }

    private static void heapsortDown(int[] x) {
        heapifyDown(x);

        int endIndex = x.length - 1;
        while (endIndex > 0) {
            swap(x, endIndex, 0);
            endIndex = endIndex - 1;
            siftDown(x, 0, endIndex);
        }
    }

    private static void heapsortDownWithLeftSearch(int[] x) {
        heapifyDownWithLeftSearch(x);

        int endIndex = x.length - 1;
        while (endIndex > 0) {
            swap(x, endIndex, 0);
            endIndex = endIndex - 1;
            siftDownWithLeftSearch(x, 0, endIndex);
        }
    }

    private static void heapsortFloyd(int[] x) {
        floydHeapAlgorithm(x);

        int endIndex = x.length - 1;
        while (endIndex > 0) {
            swap(x, endIndex, 0);
            endIndex = endIndex - 1;
            siftDown(x, 0, endIndex);
        }
    }

    private static void heapifyUp(int[] x) {
        heapifyUp(x, x.length);
    }

    private static void heapifyUp(int[] x, int count) {
        int endIndex = 1;
        while (endIndex < count) {
            siftUp(x, endIndex);
            endIndex = endIndex + 1;
        }
    }

    private static void siftUp(int[] x, int endIndex) {
        int childIndex = endIndex;
        while (childIndex > 0) {
            int parentIndex = parentIndex(childIndex);
            if (x[parentIndex] < x[childIndex]) {
                swap(x, parentIndex, childIndex);
                childIndex = parentIndex;
            } else {
                return;
            }
        }
    }

    private static void heapifyDown(int[] x) {
        heapifyDown(x, x.length);
    }

    private static void heapifyDownWithLeftSearch(int[] x) {
        heapifyDownWithLeftSearch(x, x.length);
    }

    private static void heapifyDown(int[] x, int count) {
        int startIndex = parentIndex(count - 1);
        while (startIndex >= 0) {
            siftDown(x, startIndex, count - 1);
            startIndex = startIndex - 1;
        }
    }

    private static void heapifyDownWithLeftSearch(int[] x, int count) {
        int startIndex = parentIndex(count - 1);
        while (startIndex >= 0) {
            siftDownWithLeftSearch(x, startIndex, count - 1);
            startIndex = startIndex - 1;
        }
    }

    private static void siftDown(int[] x, int startIndex, int endIndex) {
        int root = startIndex;
        while (leftChildIndex(root) <= endIndex) {
            int childIndex = leftChildIndex(root);
            int swap = root;
            if (x[swap] < x[childIndex]) {
                swap = childIndex;
            }
            if (childIndex + 1 <= endIndex && x[swap] < x[childIndex + 1]) {
                swap = childIndex + 1;
            }
            if (swap == root) {
                return;
            } else {
                swap(x, root, swap);
                root = swap;
            }
        }
    }

    private static void siftDownWithLeftSearch(int[] x, int startIndex, int endIndex) {
        int j = leftSearch(x, startIndex, endIndex);
        while (x[startIndex] > x[j]) {
            j = parentIndex(j);
        }
        int keepIt = x[j];
        x[j] = x[startIndex];
        while (j > startIndex) {
            int temp = x[parentIndex(j)];
            x[parentIndex(j)] = keepIt;
            keepIt = temp;
            j = parentIndex(j);
        }
    }

    public static void floydHeapAlgorithm(int[] x) {
        for (int i = x.length / 2; i >= 0; i--) {
            floydHeapify(x, i);
        }
    }

    private static void floydHeapify(int[] x, int i) {
        int leftIndex = leftChildIndex(i);
        int rightIndex = rightChildIndex(i);
        int max;
        if (leftIndex < x.length && x[leftIndex] > x[i]) {
            max = leftIndex;
        } else {
            max = i;
        }
        if (rightIndex < x.length && x[rightIndex] > x[max]) {
            max = rightIndex;
        }
        if (max != i) {
            swap(x, i, max);
            floydHeapify(x, max);
        }
    }

    private static int leftSearch(int[] x, int root, int end) {
        int j = root;
        while (rightChildIndex(j) <= end) {
            if (x[rightChildIndex(j)] > x[leftChildIndex(j)]) {
                j = rightChildIndex(j);
            } else {
                j = leftChildIndex(j);
            }
        }
        if (leftChildIndex(j) <= end) {
            j = leftChildIndex(j);
        }
        return j;
    }

    private static int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private static int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private static int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    enum Algorithm implements Consumer<int[]> {
        SIFT_UP {
            @Override
            public void accept(int[] x) {
                heapsortUp(x);
            }
        },
        SIFT_DOWN {
            @Override
            public void accept(int[] x) {
                heapsortDown(x);
            }
        },
        SIFT_DOWN_WITH_BOTTOM_UP_LEFT_SEARCH {
            @Override
            public void accept(int[] x) {
                heapsortDownWithLeftSearch(x);
            }
        },
        FLOYD {
            @Override
            public void accept(int[] x) {
                heapsortFloyd(x);
            }
        }
    }
}

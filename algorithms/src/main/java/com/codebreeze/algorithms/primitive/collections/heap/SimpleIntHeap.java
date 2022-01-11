package com.codebreeze.algorithms.primitive.collections.heap;

import com.codebreeze.algorithms.primitive.collections.list.IntList;
import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;

import static com.codebreeze.algorithms.primitive.collections.heap.Heaps.*;

public class SimpleIntHeap implements IntHeap {
    private final IntList heap;
    private final boolean isMaxHeap;
    private int size;


    public SimpleIntHeap(boolean isMaxHeap) {
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
        this.heap = new ArrayIntList(10);
    }

    @Override
    public void insert(int element) {
        heap.add(element); // heap.set(size, element);
        siftUp(heap, size);
        size++;
    }

    @Override
    public int extractM() {
        if (isEmpty()) {
            throw new IllegalStateException("cannot extract values from empty heap");
        }
        int popped = heap.get(0);
        heap.set(0, heap.get(size - 1));
        heap.removeAt(size - 1);
        size = size - 1;
        siftDown(heap, size - 1);
        return popped;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp(IntList x, int n) {
        int i = n;
        while (true) {
            if (i == 0) {
                break;
            }
            int p = parentIndex(i);
            if (isMaxHeap) {
                if (x.get(p) >= x.get(i)) { // < or >
                    break;
                }
            } else {
                if (x.get(p) <= x.get(i)) { // < or >
                    break;
                }
            }
            x.swap(p, i);
            i = p;
        }
    }

    private void siftDown(IntList x, int n) {
        int i = 0;
        while (true) {
            int c = leftChildIndex(i);
            if (c > n) {
                break;
            }
            int r = rightChildIndex(i);
            if (r <= n) {
                if (isMaxHeap) {
                    if (x.get(r) > x.get(c)) { // < or >
                        c = r;
                    }
                } else {
                    if (x.get(r) < x.get(c)) { // < or >
                        c = r;
                    }
                }
            }
            if (isMaxHeap) {
                if (x.get(i) >= x.get(c)) { // < or >
                    break;
                }
            } else {
                if (x.get(i) <= x.get(c)) { // < or >
                    break;
                }
            }
            x.swap(c, i);
            i = c;
        }
    }
}

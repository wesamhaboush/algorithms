package com.codebreeze.algorithms.primitive.collections.heap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.codebreeze.algorithms.Comparables.*;
import static com.codebreeze.algorithms.primitive.collections.Lists.valueOrNull;
import static com.codebreeze.algorithms.primitive.collections.heap.Heaps.*;
import static java.util.Objects.requireNonNull;

public class SimpleHeap<T> implements Heap<T> {
    private final List<T> heap;
    private final boolean isMaxHeap;
    private final Comparator<T> c;
    private int size;

    public SimpleHeap(boolean isMaxHeap, Comparator<T> c) {
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
        this.heap = new ArrayList<>(10);
        this.c = requireNonNull(c, "must provide a non null comparator");
    }

    @Override
    public void insert(T element) {
        requireNonNull(element);
        heap.add(element); // heap.set(size, element);
        siftUp(heap, size);
        size++;
    }

    @Override
    public T extractM() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot extract values from empty heap");
        }
        T popped = heap.get(0);
        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        size = size - 1;
        siftDown(heap, size - 1);
        return popped;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot extract values from empty heap");
        }
        return heap.get(0);
    }

    @Override
    public Stream<T> stream() {
        return heap.stream();
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp(List<T> x, int n) {
        int i = n;
        while (true) {
            if (i == 0) {
                break;
            }
            int p = parentIndex(i);
            if (isMaxHeap) {
                if (gte(c, x.get(p), x.get(i))) { // < or >
                    break;
                }
            } else {
                if (lte(c, x.get(p), x.get(i))) { // < or >
                    break;
                }
            }
            Collections.swap(x, p, i);
            i = p;
        }
    }

    private void siftDown(List<T> x, int n) {
        int i = 0;
        while (true) {
            int c = leftChildIndex(i);
            if (c > n) {
                break;
            }
            int r = rightChildIndex(i);
            if (r <= n) {
                if (isMaxHeap) {
                    if (gt(this.c, x.get(r), x.get(c))) { // < or >
                        c = r;
                    }
                } else {
                    if (lt(this.c, x.get(r), x.get(c))) { // < or >
                        c = r;
                    }
                }
            }
            if (isMaxHeap) {
                if (gte(this.c, x.get(i), x.get(c))) { // < or >
                    break;
                }
            } else {
                if (lte(this.c, x.get(i), x.get(c))) { // < or >
                    break;
                }
            }
            Collections.swap(x, c, i);
            i = c;
        }
    }

    public enum HeapOp {
        SiftUp,
        SiftDown,
        NONE
    }

    public enum TraversalOp {
        GO_LEFT,
        GO_RIGHT,
        GO_UP,
        GO_NOWHERE
    }

    public void traverse(Function<HeapNodeContext<T>, TraverseResult> update) {
        int currentNodeIndex = heap.isEmpty() ? -1 : 0; // potentially empty heap
        do {
            TraverseResult traversalResult = update.apply(heapNodeContext(currentNodeIndex));
            if(traversalResult.heapOp == HeapOp.SiftUp) {
                siftUp(heap, currentNodeIndex);
            } else if (traversalResult.heapOp == HeapOp.SiftDown) {
                siftDown(heap, currentNodeIndex);
            }
            currentNodeIndex = switch (traversalResult.nextMove()) {
                case GO_UP -> parentIndex(currentNodeIndex);
                case GO_LEFT -> leftChildIndex(currentNodeIndex);
                case GO_RIGHT -> rightChildIndex(currentNodeIndex);
                case GO_NOWHERE -> -1;
            };
        } while (currentNodeIndex != -1);
    }

    private HeapNodeContext<T> heapNodeContext(int currentNodeIndex) {
        return new HeapNodeContext<>(
            valueOrNull(heap, parentIndex(currentNodeIndex)),
            valueOrNull(heap, currentNodeIndex),
            valueOrNull(heap, leftChildIndex(currentNodeIndex)),
            valueOrNull(heap, rightChildIndex(currentNodeIndex))
        );
    }

    public void printHorizontal(StringBuilder sb) {
        print(heap, sb, "", "", 0);
    }

    public void printVertical(StringBuilder sb) {
        printNode(sb, heap);
    }

    public static record HeapNodeContext<T>(T parentNode, T currentNode, T leftChildNode, T rightChildNode) {
    }

    public static record TraverseResult(TraversalOp nextMove, HeapOp heapOp) {}
}

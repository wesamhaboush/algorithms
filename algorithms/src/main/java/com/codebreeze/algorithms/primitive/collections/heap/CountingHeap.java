package com.codebreeze.algorithms.primitive.collections.heap;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;
import static java.util.Objects.requireNonNull;

public class CountingHeap<T> implements Heap<T> {
    private final List<CountingEntry<T>> heap;
    private final Comparator<CountingEntry<T>> onlyElementC;
    private int size;

    public CountingHeap(Comparator<T> c) {
        requireNonNull(c, "must provide a non null comparator");
        this.size = 0;
        this.heap = new ArrayList<>(10);
        this.onlyElementC = (o1, o2) -> c.compare(o1.t, o2.t);
    }

    @Override
    public void insert(T element) {
        requireNonNull(element);
        CountingEntry<T> newEntry = new CountingEntry<>(element);
        int i = Collections.binarySearch(heap, newEntry, onlyElementC);
        if (i > 0) { // does it already exist?
            heap.get(i).count++;
        } else {
            heap.add(newEntry); // heap.set(size, element);
            newEntry.count++;
            heap.sort(onlyElementC);
        }
        size++;
    }

    @Override
    public T extractM() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot extract values from empty heap");
        }
        CountingEntry<T> mEntry = heap.get(0);
        T popped = mEntry.t;
        if (mEntry.count > 1) {
            mEntry.count--;
        } else {
            heap.remove(0);
        }
        size--;
        return popped;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot extract values from empty heap");
        }
        return heap.get(0).t;
    }

    @Override
    public Stream<T> stream() {
        return heap
            .stream()
            .flatMap(
                tCountingEntry -> nCopies(
                    tCountingEntry.count,
                    tCountingEntry.t
                ).stream()
            );
    }

    @Override
    public int size() {
        return size;
    }

    private static class CountingEntry<T> {
        private int count;
        private final T t;

        private CountingEntry(T t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "Entry{count=" + count + ", t=" + t + '}';
        }
    }
}

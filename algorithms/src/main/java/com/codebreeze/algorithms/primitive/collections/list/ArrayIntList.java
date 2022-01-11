package com.codebreeze.algorithms.primitive.collections.list;

import com.codebreeze.algorithms.primitive.collections.iterable.IntIterable;
import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.isEquals;

public class ArrayIntList implements IntList {
    private int[] elements;
    private int size;

    public ArrayIntList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("cannot have negative initial capacity: " + initialCapacity);
        }
        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    public ArrayIntList() {
        this(10);
    }



    @Override
    public void add(int value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void remove(int value) {
        this.elements = Arrays.stream(elements, 0, size)
            .filter(i -> i != value)
            .toArray();
        this.size = this.elements.length;
    }

    @Override
    public void removeAt(int index) {
        validateIndex(index);

        if (size == index + 1) {
            this.elements[size - 1] = 0;
        } else {
            System.arraycopy(this.elements, index + 1, this.elements, index, size - index - 1);
            this.elements[size - 1] = 0;
        }
        size--;
    }

    @Override
    public int get(int index) {
        validateIndex(index);
        return this.elements[index];
    }

    @Override
    public void set(int index, int value) {
        validateIndex(index);
        this.elements[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (this.elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IntStream stream() {
        return Arrays.stream(this.elements, 0, size);
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("cannot operate on elements outside array: " + index);
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayIntList that = (ArrayIntList) o;
        return size == that.size && isEquals(this.elements, 0, size - 1, that.elements, 0, that.size - 1, false);
    }



    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        for (int i = 0; i < size; i++) {
            result = 31 * result + elements[i];
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleIntList{elements=" + Arrays.toString(elements) + ", size=" + size + '}';
    }

    public static IntList of(IntIterable iterable) {
        IntIterator iterator = iterable.iterator();
        IntList list = new ArrayIntList();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}

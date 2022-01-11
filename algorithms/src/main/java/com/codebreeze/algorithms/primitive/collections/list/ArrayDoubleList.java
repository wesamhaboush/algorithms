package com.codebreeze.algorithms.primitive.collections.list;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.DoubleStream;

public class ArrayDoubleList implements DoubleList {
    private double[] elements;
    private int size;

    public ArrayDoubleList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("cannot have negative initial capacity: " + initialCapacity);
        }
        this.elements = new double[initialCapacity];
        this.size = 0;
    }

    public ArrayDoubleList() {
        this(10);
    }

    @Override
    public void add(double value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void remove(double value) {
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
    public double get(int index) {
        validateIndex(index);
        return this.elements[index];
    }

    @Override
    public void set(int index, double value) {
        validateIndex(index);
        this.elements[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(double value) {
        for (int i = 0; i < size; i++) {
            if (this.elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DoubleStream stream() {
        return Arrays.stream(this.elements, 0, size);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("cannot operate on elements outside array: " + index);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayDoubleList that = (ArrayDoubleList) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleDoubleList{");
        sb.append("elements=");
//            .append(Arrays.toString(elements));
        sb.append('[');
        for(int i = 0; i < size; i++) {
            if(i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append(']');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}

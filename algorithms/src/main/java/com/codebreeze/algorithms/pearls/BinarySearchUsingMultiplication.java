package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.heap.Heaps;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.IntUnaryOperator;

import static java.lang.Math.*;
import static java.util.Objects.requireNonNull;

public class BinarySearchUsingMultiplication implements IntUnaryOperator {
    private final HeapElement[] heapElements;

    public BinarySearchUsingMultiplication(int[] elements) {
        requireNonNull(elements);
        double log2 = log(elements.length + 1) / log(2);
        if (ceil(log2) != floor(log2)) {
            throw new IllegalArgumentException("can only operate on 2^k -1 size arrays");
        }
        // We can use this to calculate a better tree for any size input,
        // but we for now will confirm the size is equal to some 2^k - 1
//        int nextPower2Length = (int)Math.pow(2, Math.ceil(Math.log(elements.length)/Math.log(2)));
        heapElements = new HeapElement[elements.length];
        reorganizeIntoBinaryTreeLike(elements);
    }

    private void reorganizeIntoBinaryTreeLike(int[] elements) {
        if (elements.length == 0) {
            return;
        }
        Queue<Process> processes = new LinkedList<>();
        int i = 0;
        processes.add(new Process(0, elements.length - 1, i));
        while (!processes.isEmpty()) {
            Process p = processes.poll();
            int median = p.from + (p.to - p.from) / 2;
            heapElements[p.i] = new HeapElement(elements[median], median);
            if (p.from <= median - 1) {
                processes.add(new Process(p.from, median - 1, ++i));
            }
            if (median + 1 <= p.to) {
                processes.add(new Process(median + 1, p.to, ++i));
            }
        }
    }

    @Override
    public int applyAsInt(int targetValue) {
        int currentNode = 0;
        while (currentNode < heapElements.length) {
            int currentValue = heapElements[currentNode].value;
            int currentIndex = heapElements[currentNode].index;
            if (targetValue == currentValue) {
                return currentIndex;
            } else if (targetValue < currentValue) {
                currentNode = Heaps.leftChildIndex(currentNode); // cheap multiplication
            } else { // if(targetValue > currentValue) {
                currentNode = Heaps.rightChildIndex(currentNode); // cheap multiplication
            }
        }
        throw new NoSuchElementException("cannot find value: " + targetValue);
    }

    private static record Process(int from, int to, int i) {
    }

    private static record HeapElement(int value, int index) {
        @Override
        public String toString() {
            return "(" + value + "," + index + ")";
        }
    }
}

package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap;
import com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap.TraverseResult;
import com.codebreeze.algorithms.primitive.collections.iterable.DoubleIterable;
import com.codebreeze.algorithms.primitive.collections.iterator.DoubleIterator;
import com.codebreeze.algorithms.primitive.collections.list.ArrayDoubleList;
import com.codebreeze.algorithms.primitive.collections.list.DoubleList;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap.HeapOp.NONE;
import static com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap.HeapOp.SiftDown;
import static com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap.TraversalOp.*;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.joining;

public class BinPackingUsingPriorityQueue implements Function<DoubleIterable, BinPackingUsingPriorityQueue.Bin[]> {
    @Override
    public Bin[] apply(DoubleIterable doubleIterable) {
        Objects.requireNonNull(doubleIterable, "cannot operate on null iterators");

        SimpleHeap<Bin> weightHeap = new SimpleHeap<>(true, comparingDouble(Bin::remaining));

        DoubleIterator iterator = doubleIterable.iterator();
        while (iterator.hasNext()) {
            double boxSize = requireBoxFitsMaxBinSize(iterator.next());

            AtomicBoolean boxInserted = new AtomicBoolean(false);
            while (!boxInserted.get()) {
                weightHeap.traverse(context -> {
                    if (context.leftChildNode() != null && context.leftChildNode().canAccommodateBoxOfSize(boxSize)) { // left is preferred
                        return new TraverseResult(GO_LEFT, NONE);
                    } else if (context.rightChildNode() != null && context.rightChildNode().canAccommodateBoxOfSize(boxSize)) { // right is second preference
                        return new TraverseResult(GO_RIGHT, NONE);
                    } else if (context.currentNode() != null && context.currentNode().canAccommodateBoxOfSize(boxSize)) { // we cannot descend, then try current node!!
                        return updateTraversalResult(boxSize, boxInserted, context.currentNode());
                    } else {
                        return new TraverseResult(GO_NOWHERE, NONE); // seems this heap has no space, get out!
                    }
                });
                if(!boxInserted.get()) { // add more bins when needed
                    weightHeap.insert(new Bin(1.0));
                }
            }
        }

        return weightHeap.stream().toArray(Bin[]::new);
    }

    private TraverseResult updateTraversalResult(double boxSize, AtomicBoolean boxInserted, Bin bin) {
        bin.addBoxOfSize(boxSize);
        boxInserted.set(true);
        return new TraverseResult(GO_NOWHERE, SiftDown);
    }

    public static class Bin {
        private double remaining;
        private final DoubleList boxes = new ArrayDoubleList();

        Bin(double capacity) {
            this.remaining = capacity;
        }

        public double remaining() {
            return remaining;
        }

        public void addBoxOfSize(double size) {
            if (remaining < size) {
                throw new IllegalArgumentException(
                    "cannot add a box of size " + size + " with remaining capacity " + remaining
                );
            }
            this.remaining = remaining - size;
            this.boxes.add(size);
        }

        public boolean canAccommodateBoxOfSize(double boxSize) {
            return this.remaining >= boxSize;
        }

        @Override
        public String toString() {
            return "Bin{remaining=" +
                String.format("%.2f", remaining) +
                ", boxes=[" +
                boxes.stream().mapToObj(d -> String.format("%.2f", d)).collect(joining(",")) +
                "]}";
        }
    }

    private static double requireBoxFitsMaxBinSize(double boxSize) {
        if (boxSize > 1.0) {
            throw new IllegalArgumentException("cannot bin values greater than 1.0, but got: " + boxSize);
        }
        return boxSize;
    }
}

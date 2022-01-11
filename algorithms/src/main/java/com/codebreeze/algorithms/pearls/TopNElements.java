package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.heap.SimpleIntHeap;
import com.codebreeze.algorithms.primitive.collections.list.IntList;
import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class TopNElements implements Function<TopNElements.Specs, int[]> {
    @Override
    public int[] apply(Specs specs) {
        Objects.requireNonNull(specs, "cannot operate with null specs");

        SimpleIntHeap sih = new SimpleIntHeap(false);

        IntStream.range(0, specs.totalN)
            .forEach(i -> {
                if (sih.size() < specs.topN()) {
                    sih.insert(specs.supplier().getAsInt());
                } else {
                    int min = sih.extractM();
                    int newValue = specs.supplier().getAsInt();
                    sih.insert(Math.max(newValue, min));
                }
            });

        IntList maxNInts = new ArrayIntList(specs.topN);

        while (!sih.isEmpty()) {
            maxNInts.add(sih.extractM());
        }

        return maxNInts.stream().toArray();
    }

    public static record Specs(IntSupplier supplier, int topN, int totalN) {
        public Specs {
            Objects.requireNonNull(supplier, "cannot operate with null supplier");
            if (topN < 1) {
                throw new IllegalArgumentException("cannot operate with topN less than 1, but was: " + topN);
            }
            if (totalN < 1) {
                throw new IllegalArgumentException("cannot operate with totalN less than 1, but was: " + totalN);
            }
            if (topN > totalN) {
                throw new IllegalArgumentException("cannot operate with topN greater than totalN, topN: " + topN + ", totalN: " + totalN);
            }
        }
    }
}

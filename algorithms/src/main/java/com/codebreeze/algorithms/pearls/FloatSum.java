package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.heap.DoubleHeap;
import com.codebreeze.algorithms.primitive.collections.heap.SimpleDoubleHeap;

import java.util.Objects;
import java.util.function.ToDoubleFunction;

public class FloatSum implements ToDoubleFunction<double[]> {

    @Override
    public double applyAsDouble(double[] values) {
        Objects.requireNonNull(values, "cannot operate on null values array");

        DoubleHeap sdh = new SimpleDoubleHeap(false);

        for(double d : values) {
            sdh.insert(d);
        }

        while(sdh.size() > 1) {
            double v1 = sdh.extractM();
            double v2 = sdh.extractM();
            sdh.insert(v1 + v2);
        }

        return sdh.extractM();
    }
}

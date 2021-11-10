package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.IntBinaryOperator;

public class TurnpikeCost implements IntBinaryOperator {
    private final int[] cumulative;

    public TurnpikeCost(final int[] stationCosts) {
        // we allow empty arrays, but it is non-sense cz no cost to calculate!
        Objects.requireNonNull(stationCosts, "cannot work with null station cost vector");
        this.cumulative = new int[stationCosts.length];

        for (int i = 0; i < stationCosts.length; i++) {
            int previousCost = i == 0 ? 0 : cumulative[i - 1];
            cumulative[i] = stationCosts[i] + previousCost;
        }
    }

    @Override
    public int applyAsInt(int fromStation, int toStation) {
        if (fromStation > toStation) {
            throw new IllegalArgumentException(
                "cannot deal with from station that is larger than to station: " + fromStation + ", " + toStation
            );
        }
        if (toStation >= cumulative.length || fromStation < 0) {
            throw new IllegalArgumentException(
                String.format(
                    "there is no such station from %d to %d, options are from %d to %d only",
                    fromStation,
                    toStation,
                    0,
                    cumulative.length - 1
                )
            );
        }
        // the algorithm is simple here
        if (fromStation == 0) {
            return cumulative[toStation];
        } else {
            return cumulative[toStation] - cumulative[fromStation - 1];
        }
    }
}

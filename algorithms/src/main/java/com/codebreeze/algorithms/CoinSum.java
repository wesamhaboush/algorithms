package com.codebreeze.algorithms;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/*
Coin sums
Problem 31
In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
 */
public class CoinSum
{
    private static final Set<Integer> COINS = new HashSet<Integer>()
    {
        {
            add(100); // 1 pound = 100 pennies
            add(50);
            add(20);
            add(10);
            add(5);
            add(2);
            add(1);
        }
    };

    /*
    find each coin's max number to make 2pounds. For example:
    1pound => 2 coins max
    50p => 4 coins max
    20 => 10 coins max
    5p => 40 max
    2p => 100p max
    1p => 200p max

    now go ahead and find the sums of all possibilities in may be for loops?
     */
    public static long calculateUnmemoized(final long targetPennies, final Set<Long> coins)
    {
        if (coins.isEmpty())
        {
            if (targetPennies == 0)
            {
                // if we are at target, then there is only 1 way to get to target number of pennies
                // this is essentially a successfully base case, so we found one combination, so we
                // increment
                return 1;
            }
            else
            {
                // if target pennies is not zero, and we have coins, then we are either minus or positive
                // and we cannot achieve our target
                // this basically means we went down a failing path
                return 0;
            }
        }
        else
        {
            //get one coin and work on it
            final Long coin = coins
                    .iterator()
                    .next();
            final Set<Long> leftCoins = coins.stream()
                                             // everything except the coin we are working on
                                             .filter(c -> c != coin)
                                             .collect(toSet());
            //how many ways can you contribute to making the target with the first coin in the list?
            // for example, how many ways can 2 pennies contribute to a 10 pennies target?
            // the number = 10 / 2 + 1 = 6, i.e.: 0, 2, 4, 6, 8, 10
            final long maxCoinCombinations = targetPennies / coin + 1;
            //now find for each combination, how many ways there is to contribute from the rest of the coins
            long count = 0;
            for (int i = 0; i < maxCoinCombinations; i++)
            {
                final long leftPennies = targetPennies - coin * i;
                count += calculateUnmemoized(leftPennies, leftCoins);
            }
            return count;
        }
    }
    private static final Map<CoinsAndTargetPennies, Long> setToCount = new HashMap<>();

    public static long calculateMemoized(final long targetPennies, final Set<Long> coins)
    {
        final CoinsAndTargetPennies key = new CoinsAndTargetPennies(coins, targetPennies);
        final Long cachedCount = setToCount.get(key);
        if(cachedCount == null)
        {
            long count = 0;
            if (coins.isEmpty())
            {
                if (targetPennies == 0)
                {
                    // if we are at target, then there is only 1 way to get to target number of pennies
                    // this is essentially a successfully base case, so we found one combination, so we
                    // increment
                    count = 1;
                }
                else
                {
                    // if target pennies is not zero, and we have coins, then we are either minus or positive
                    // and we cannot achieve our target
                    // this basically means we went down a failing path
                    count = 0;
                }
            }
            else
            {
                //get one coin and work on it
                final Long coin = coins
                        .iterator()
                        .next();
                final Set<Long> leftCoins = coins.stream()
                                                 // everything except the coin we are working on
                                                 .filter(c -> c != coin)
                                                 .collect(toSet());
                //how many ways can you contribute to making the target with the first coin in the list?
                // for example, how many ways can 2 pennies contribute to a 10 pennies target?
                // the number = 10 / 2 + 1 = 6, i.e.: 0, 2, 4, 6, 8, 10
                final long maxCoinCombinations = targetPennies / coin + 1;
                //now find for each combination, how many ways there is to contribute from the rest of the coins
                for (int i = 0; i < maxCoinCombinations; i++)
                {
                    final long leftPennies = targetPennies - coin * i;
                    count += calculateMemoized(leftPennies, leftCoins);
                }
            }
            setToCount.put(key, count);
            return count;
        }
        else
        {
//            System.out.print(".");
            return cachedCount;
        }
    }

    private static class CoinsAndTargetPennies
    {
        private final Set<Long> coins;
        private final Long targetPennies;

        private CoinsAndTargetPennies(Set<Long> coins, Long targetPennies)
        {
            this.coins = coins;
            this.targetPennies = targetPennies;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            CoinsAndTargetPennies that = (CoinsAndTargetPennies) o;
            return Objects.equals(coins, that.coins) && Objects.equals(targetPennies, that.targetPennies);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(coins, targetPennies);
        }
    }
}

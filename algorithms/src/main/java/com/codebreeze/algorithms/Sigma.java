package com.codebreeze.algorithms;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Sigma
{
    public static long of(final long n)
    {
        final List<Long> primeFactors = PrimeNumbers.primeFactors(n);
        final Map<Long, Long> primeToPowersMap = frequencyMap(primeFactors);
        //σ(p^a) = (p^(a+1) − 1)/(p − 1), where p is a prime number
        //σ(a×b×...)=σ(a)×σ(b)×..., where a, b, ..., are relatively prime
        final long totalSigma = primeToPowersMap
                .entrySet()
                .stream()
                .mapToLong(primePowerPair -> {
                    final long p = primePowerPair.getKey();
                    final long a = primePowerPair.getValue();
                    final long sigmaOfPrimeToPower = (Power.apply(p, a + 1) - 1) / (p - 1);
                    return sigmaOfPrimeToPower;
                })
                .reduce(1L, (sigma, total) -> sigma * total);
        return totalSigma;
    }

    private static <T> Map<T, Long> frequencyMap(final List<T> numbers)
    {
        return numbers
                .stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}

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
                    final long sigmaOfPrimeToPower = (pow(p, a + 1) - 1) / (p - 1);
                    return sigmaOfPrimeToPower;
                })
                .reduce(1L, (sigma, total) -> sigma * total);
        return totalSigma;
    }

    private static long pow(long a, long b)
    {
        if ( b == 0)        return 1;
        if ( b == 1)        return a;
        if (isEven( b ))    return     pow ( a * a, b/2); //even a=(a^2)^b/2
        else                return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2
    }

    private static boolean isEven(long b)
    {
        return b % 2 == 0;
    }

    private static <T> Map<T, Long> frequencyMap(final List<T> numbers)
    {
        return numbers
                .stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}

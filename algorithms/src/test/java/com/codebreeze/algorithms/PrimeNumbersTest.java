package com.codebreeze.algorithms;

import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumbersTest
{
    @Test
    public void correct_primes_returned_from_single_iterators() throws Exception
    {
        final Iterator<Integer> primeNumbersIterator = PrimeNumbers.iterator();
        assertThat(primeNumbersIterator.hasNext()).isTrue();
        assertThat(primeNumbersIterator.hasNext()).isTrue();
        assertThat(primeNumbersIterator.hasNext()).isTrue();
        assertThat(primeNumbersIterator.next()).isEqualTo(2);
        assertThat(primeNumbersIterator.next()).isEqualTo(3);
        assertThat(primeNumbersIterator.next()).isEqualTo(5);
        assertThat(primeNumbersIterator.next()).isEqualTo(7);
        assertThat(primeNumbersIterator.next()).isEqualTo(11);
        assertThat(primeNumbersIterator.next()).isEqualTo(13);
        assertThat(primeNumbersIterator.next()).isEqualTo(17);
        assertThat(primeNumbersIterator.next()).isEqualTo(19);
        assertThat(primeNumbersIterator.next()).isEqualTo(23);
        assertThat(primeNumbersIterator.next()).isEqualTo(29);
        assertThat(primeNumbersIterator.next()).isEqualTo(31);
        assertThat(primeNumbersIterator.next()).isEqualTo(37);
    }

    @Test
    public void correct_primes_returned_from_two_interleaving_iterators() throws Exception
    {
        SoftAssertions softly = new SoftAssertions();
        final Iterator<Integer> primeNumbersIterator1 = PrimeNumbers.iterator();
        final Iterator<Integer> primeNumbersIterator2 = PrimeNumbers.iterator();
        final Thread thread1 = new Thread()
        {
            @Override
            public void run()
            {
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(2);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(3);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(5);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(7);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(11);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(13);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(17);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(19);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(23);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(29);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(31);
                softly.assertThat(primeNumbersIterator1.next()).isEqualTo(37);
            }
        };
        final Thread thread2 = new Thread()
        {
            @Override
            public void run()
            {
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(2);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(3);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(5);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(7);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(11);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(13);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(17);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(19);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(23);
                softly.assertThat(primeNumbersIterator2.next()).isEqualTo(29);
            }
        };
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        try {
            softly.assertAll();
        } catch (SoftAssertionError e) {
            logAssertionErrorMessage("SoftAssertion errors example", e);
        }
    }

    @Test
    public void should_find_prime_factors() throws Exception
    {
        assertThat(PrimeNumbers.primeFactors(2)).isEqualTo(asList(2L));
        assertThat(PrimeNumbers.primeFactors(3)).isEqualTo(asList(3L));
        assertThat(PrimeNumbers.primeFactors(4)).isEqualTo(asList(2L, 2L));
        assertThat(PrimeNumbers.primeFactors(5)).isEqualTo(asList(5L));
        assertThat(PrimeNumbers.primeFactors(6)).isEqualTo(asList(2L, 3L));
        assertThat(PrimeNumbers.primeFactors(7)).isEqualTo(asList(7L));
        assertThat(PrimeNumbers.primeFactors(8)).isEqualTo(asList(2L, 2L, 2L));
        assertThat(PrimeNumbers.primeFactors(9)).isEqualTo(asList(3L, 3L));
        assertThat(PrimeNumbers.primeFactors(10)).isEqualTo(asList(2L, 5L));
        assertThat(PrimeNumbers.primeFactors(11)).isEqualTo(asList(11L));
        assertThat(PrimeNumbers.primeFactors(12)).isEqualTo(asList(2L, 2L, 3L));
    }

    protected static void logAssertionErrorMessage(String assertionContext, AssertionError e) {
        System.out.println(String.format("%s assertion : %s%n", assertionContext, e.getMessage()));
    }
}

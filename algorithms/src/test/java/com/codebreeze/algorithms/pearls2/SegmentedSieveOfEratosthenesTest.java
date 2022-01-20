package com.codebreeze.algorithms.pearls2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

class SegmentedSieveOfEratosthenesTest {

    @Test
    void segmented_sieve_cached() throws URISyntaxException, IOException {
        segmented_sieve(true, 10000, 94729);
    }

    @Test
    void segmented_sieve_not_cached() throws URISyntaxException, IOException {
        segmented_sieve(false, 1000, 6919);
    }

    private void segmented_sieve(boolean cached, int numberOfEntries, int expectedNumberOfNonPrimes) throws URISyntaxException, IOException {
        // given
        Stream<String> lines = Files.lines(
            Path.of(
                requireNonNull(this.getClass().getResource("/primes-all-to-million-sorted")).toURI()
            )
        );

        // when
        SegmentedSieveOfEratosthenes ssoe = new SegmentedSieveOfEratosthenes(cached);
        AtomicInteger countPrimes = new AtomicInteger(0);
        AtomicInteger countNoPrimes = new AtomicInteger(0);
        AtomicInteger previous = new AtomicInteger(0);
        lines.limit(numberOfEntries).forEach(s -> {
            int next = parseInt(s);
            assertThat(ssoe.test(next)).isTrue();
            countPrimes.incrementAndGet();
            for(int i = previous.get() + 1; i < next; i++) {
                assertThat(ssoe.test(i)).isFalse();
                countNoPrimes.incrementAndGet();
            }
            previous.set(next);
        });

        // then
        assertThat(countPrimes.get()).isEqualTo(numberOfEntries);
        assertThat(countNoPrimes.get()).isEqualTo(expectedNumberOfNonPrimes);
    }
}

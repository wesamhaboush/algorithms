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

class SieveOfSundaramTest {

    @Test
    void vanilla_sieve() throws URISyntaxException, IOException {
        // given
        Stream<String> lines = Files.lines(
            Path.of(
                requireNonNull(this.getClass().getResource("/primes-all-to-million-sorted")).toURI()
            )
        );

        // when
        SieveOfSundaram sos = new SieveOfSundaram();

        // then
        AtomicInteger countPrimes = new AtomicInteger(0);
        AtomicInteger countNoPrimes = new AtomicInteger(0);
        AtomicInteger previous = new AtomicInteger(0);
        lines.limit(1000).forEach(s -> {
            int next = parseInt(s);
            assertThat(sos.test(next)).isTrue();
            countPrimes.incrementAndGet();
            for(int i = previous.get() + 1; i < next; i++) {
                assertThat(sos.test(i)).isFalse();
                countNoPrimes.incrementAndGet();
            }
            previous.set(next);
        });

        assertThat(countPrimes.get()).isEqualTo(1000);
        assertThat(countNoPrimes.get()).isEqualTo(6919);
    }
}

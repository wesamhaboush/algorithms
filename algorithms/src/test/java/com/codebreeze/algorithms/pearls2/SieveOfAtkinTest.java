package com.codebreeze.algorithms.pearls2;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

class SieveOfAtkinTest {

    @Test
    void apply_vanilla() throws URISyntaxException, IOException {
        // given
        Stream<String> lines = Files.lines(
            Path.of(
                requireNonNull(this.getClass().getResource("/primes-all-to-million-sorted")).toURI()
            )
        );

        // when
        SieveOfAtkin soa = new SieveOfAtkin();

        // then
        IntList cache = new ArrayIntList();
        lines.limit(1000).forEach(s -> {
            int next = parseInt(s);
            cache.add(next);
            assertThat(soa.apply(next)).containsExactly(cache.stream().toArray());
        });
    }
}

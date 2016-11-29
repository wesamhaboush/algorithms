package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class DistinctPowersTest
{

    @Test
    public void calculate() throws Exception
    {
        assertThat(DistinctPowers.calculateStoreReduced(2, 5)).isEqualTo(15);
        assertThat(DistinctPowers.calculateStoreReduced(2, 6)).isEqualTo(23);
        assertThat(DistinctPowers.calculateStoreReduced(2, 8)).isEqualTo(44);
        assertThat(DistinctPowers.calculateStoreReduced(2, 9)).isEqualTo(54);
        assertThat(DistinctPowers.calculateStoreReduced(2, 10)).isEqualTo(69);
        assertThat(DistinctPowers.calculateStoreReduced(2, 11)).isEqualTo(88);
        assertThat(DistinctPowers.calculateStoreReduced(2, 12)).isEqualTo(106);
        assertThat(DistinctPowers.calculateStoreReduced(2, 15)).isEqualTo(177);
        assertThat(DistinctPowers.calculateStoreReduced(2, 16)).isEqualTo(195);
        long start = System.currentTimeMillis();
        assertThat(DistinctPowers.calculateStoreReduced(2, 100)).isEqualTo(9183);//9183
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void calculateCounting() throws Exception
    {
        assertThat(DistinctPowers.calculateCounting(2, 5)).isEqualTo(15);
        assertThat(DistinctPowers.calculateCounting(2, 6)).isEqualTo(23);
        assertThat(DistinctPowers.calculateCounting(2, 8)).isEqualTo(44);
        assertThat(DistinctPowers.calculateCounting(2, 9)).isEqualTo(54);
        assertThat(DistinctPowers.calculateCounting(2, 10)).isEqualTo(69);
        assertThat(DistinctPowers.calculateCounting(2, 11)).isEqualTo(88);
        assertThat(DistinctPowers.calculateCounting(2, 12)).isEqualTo(106);
        assertThat(DistinctPowers.calculateCounting(2, 15)).isEqualTo(177);
        assertThat(DistinctPowers.calculateCounting(2, 16)).isEqualTo(195);
        assertThat(DistinctPowers.calculateCounting(2, 20)).isEqualTo(324L);
        //                assertThat(DistinctPowers.calculate5(2, 100)).isEqualTo(9182L);//9183
        long start = System.currentTimeMillis();
        assertThat(DistinctPowers.calculateCounting(2, 100)).isEqualTo(9183);//9183
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void testMapIteration(){
        //given
        final long iterationCount = 500;
        final long skipCount = 100;
        final Double iterationCountThatCount = Double.valueOf(iterationCount - skipCount);
        final Map<Integer, String> map = new HashMap<Integer, String>(){{
            put(1, "Java");
            put(2, "Groovy");
            put(3, "Scala");
            put(4, "Clojure");
            put(5, "jRuby");
        }};
        //when
        long sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final long start = System.nanoTime();
            final List<String> result = map
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(toList());
            final long end = System.nanoTime();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, entrySet stream getValue toList: " + sum / iterationCountThatCount);
        sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final long start = System.nanoTime();
            final List<String> result = map
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(toList());
            final long end = System.nanoTime();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, entrySet stream getValue toList: " + sum / iterationCountThatCount);
        sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final long start = System.nanoTime();
            final List<String> result = map
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(toCollection(() -> new ArrayList<>()));
            final long end = System.nanoTime();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, entrySet Stream getValue toCollection: " + sum / iterationCountThatCount);
        sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final long start = System.nanoTime();
            final List<String> result = map
                    .values()
                    .stream()
                    .collect(toCollection(() -> new ArrayList<>()));
            final long end = System.nanoTime();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, values stream toCollection: " + sum / iterationCountThatCount);
        sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final List<String> result = new ArrayList(map.entrySet().size());
            final long start = System.nanoTime();
            map
                    .entrySet()
                    .forEach( e -> result.add(e.getValue()));
            final long end = System.nanoTime();
            assert result.size() == map.entrySet().size();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, entrySet for each add to outside: " + sum / iterationCountThatCount);
        sum = 0;
        for(int i = 0; i < iterationCount; i++)
        {
            final List<String> result = new ArrayList(map.entrySet().size());
            final long start = System.nanoTime();
            for(final Map.Entry<Integer, String> entry : map.entrySet())
            {
                result.add(entry.getValue());
            }
            final long end = System.nanoTime();
            assert result.size() == map.entrySet().size();
            if( i > skipCount) //skip first 10 for warm up
            {
                sum += (end - start);
            }
            assert result.size() == 5;
        }
        System.out.println("experiment, normal loop add each value: " + sum / iterationCountThatCount);
        sum = 0;
        //then
//        assert jvmLangs1.size() == 5;
//        assert jvmLangs2.size() == 5;
    }
}

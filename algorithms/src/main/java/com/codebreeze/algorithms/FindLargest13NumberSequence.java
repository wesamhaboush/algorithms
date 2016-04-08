package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.

 73167176531330624919225119674426574742355349194934
 96983520312774506326239578318016984801869478851843
 85861560789112949495459501737958331952853208805511
 12540698747158523863050715693290963295227443043557
 66896648950445244523161731856403098711121722383113
 62229893423380308135336276614282806444486645238749
 30358907296290491560440772390713810515859307960866
 70172427121883998797908792274921901699720888093776
 65727333001053367881220235421809751254540594752243
 52584907711670556013604839586446706324415722155397
 53697817977846174064955149290862569321978468622482
 83972241375657056057490261407972968652414535100474
 82166370484403199890008895243450658541227588666881
 16427171479924442928230863465674813919123162824586
 17866458359124566529476545682848912883142607690042
 24219022671055626321111109370544217506941658960408
 07198403850962455444362981230987879927244284909188
 84580156166097919133875499200524063689912560717606
 05886116467109405077541002256983155200055935729725
 71636269561882670428252483600823257530420752963450

 Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
public class FindLargest13NumberSequence
{
    private static final  String NUMBERS_TEXT = //"10221304056078690";
         "73167176531330624919225119674426574742355349194934"
        +"96983520312774506326239578318016984801869478851843"
        +"85861560789112949495459501737958331952853208805511"
        +"12540698747158523863050715693290963295227443043557"
        +"66896648950445244523161731856403098711121722383113"
        +"62229893423380308135336276614282806444486645238749"
        +"30358907296290491560440772390713810515859307960866"
        +"70172427121883998797908792274921901699720888093776"
        +"65727333001053367881220235421809751254540594752243"
        +"52584907711670556013604839586446706324415722155397"
        +"53697817977846174064955149290862569321978468622482"
        +"83972241375657056057490261407972968652414535100474"
        +"82166370484403199890008895243450658541227588666881"
        +"16427171479924442928230863465674813919123162824586"
        +"17866458359124566529476545682848912883142607690042"
        +"24219022671055626321111109370544217506941658960408"
        +"07198403850962455444362981230987879927244284909188"
        +"84580156166097919133875499200524063689912560717606"
        +"05886116467109405077541002256983155200055935729725"
        +"71636269561882670428252483600823257530420752963450";
    private static final long[] xs = NUMBERS_TEXT.chars()
                                                .mapToLong(i -> i - '0')
                                                .toArray();

    public static Triple calculate(final int n)
    {
        int startIndex = 0;
        int endIndex = n - 1;
        //find product of first n
        long currentProduct = IntStream
                .rangeClosed(startIndex, endIndex)
                .mapToLong(i -> xs[i])
                .reduce(1, (total, value) -> value * total);
        Triple triple = new Triple();
        triple.maxProduct = currentProduct; //initialize max to first some to start with
        triple.startIndex = startIndex;
        triple.endIndex = endIndex;
        boolean wasInZeroProduct = triple.maxProduct == 0; //if first n have multiple of zero, then we have started a zero
        //"10221304056078690"
        while(endIndex < xs.length)
        {
            //keep going until you get a non zero product, or we never get there before finishing
            while(currentProduct == 0 && endIndex < xs.length)
            {
                currentProduct = IntStream
                        .rangeClosed(startIndex++, endIndex++)
                        .mapToLong(index -> xs[index])
                        .reduce(1, (total, value) -> value * total);
                wasInZeroProduct = true;
            }
            if(wasInZeroProduct)
            {
                startIndex--; //offsetting the extra increment
                endIndex--;
                boolean areWeChangingMax = currentProduct > triple.maxProduct;
                triple.startIndex = areWeChangingMax ? startIndex : triple.startIndex;
                triple.endIndex = areWeChangingMax ? endIndex : triple.endIndex;
                triple.maxProduct = Math.max(triple.maxProduct, currentProduct);
                wasInZeroProduct = false;
            }
            else
            {
                if(endIndex < xs.length)
                {
                    //the if is because at the begining there is not startIndex-1 to go back to (first iteration if n numbers are non zero).
                    currentProduct = startIndex > 0 ? xs[endIndex] * currentProduct / xs[startIndex - 1] : currentProduct;
                    boolean areWeChangingMax = currentProduct > triple.maxProduct;
                    triple.maxProduct = Math.max(triple.maxProduct, currentProduct);
                    triple.startIndex = areWeChangingMax ? startIndex : triple.startIndex;
                    triple.endIndex = areWeChangingMax ? endIndex : triple.endIndex;
                }
            }
            startIndex++;
            endIndex++;
        }
        return triple;
    }

    public static class Triple
    {
        public long maxProduct;
        public int startIndex;
        public int endIndex;
    }

    /**
     * naive solution
     * @param n
     * @return
     */
    public static Triple calculate2(int n)
    {
        List<Triple> triples = new ArrayList<>();
        int startIndex = 0;
        int endIndex = startIndex + n - 1;
        while(endIndex < xs.length)
        {
            Triple triple = new Triple();
            triple.maxProduct = IntStream
                    .rangeClosed(startIndex, endIndex)
                    .mapToLong(i -> xs[i])
                    .reduce(1l, (i, j) -> i * j);
            triple.startIndex = startIndex;
            triple.endIndex = endIndex;
            triples.add(triple);
            startIndex++;
            endIndex++;
        }
        Collections.sort(triples, (i, j) -> -Long.compare(i.maxProduct, j.maxProduct));
        return triples.get(0);
    }
}

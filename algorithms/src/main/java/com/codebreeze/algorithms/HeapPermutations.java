package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HeapPermutations
{
    /*
    procedure generate(n : integer, A : array of any):
    if n = 1 then
          output(A)
    else
        for i := 0; i < n - 1; i += 1 do
            generate(n - 1, A)
            if n is even then
                swap(A[i], A[n-1])
            else
                swap(A[0], A[n-1])
            end if
        end for
        generate(n - 1, A)
    end if
     */
    public static <T> List<List<T>> of(final List<T> items)
    {
        final List<List<T>> result = new LinkedList<>();
        permute(items.size(), new ArrayList<>(items), result);
        return result;
    }

    private static <T> void permute(final int n, final List<T> items, final List<List<T>> result)
    {
        if( n == 1 )
        {
            result.add(new ArrayList<>(items));
        }
        else
        {
            for( int i = 0; i < n - 1; i++ )
            {
                permute(n - 1, items, result);
                if( n % 2 == 0 )
                {
                    Collections.swap(items, i, n - 1);
                }
                else
                {
                    Collections.swap(items, 0, n - 1);
                }
            }
            permute(n - 1, items, result);
        }
    }
}

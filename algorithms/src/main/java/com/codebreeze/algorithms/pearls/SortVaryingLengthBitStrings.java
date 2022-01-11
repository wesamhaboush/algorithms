package com.codebreeze.algorithms.pearls;

import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class SortVaryingLengthBitStrings implements Consumer<String[]> {

    /*
procedure Sort(L, U, Depth)
    if L < U then
        for I := L to U do
            if X[I].Length < Depth then
                Swap(X[I], X[L])
                L := L+1
        M := L
        for I := L to U do
            if X[I].Bit[Depth] = 0 then
                Swap(X[I], X[M])
                M := M+1
        Sort(L, M-1, Depth+1)
        Sort(M, U, Depth+1)
     */
    @Override
    public void accept(String[] bitStrings) {
        if(bitStrings == null) {
            throw new NullPointerException("cannot operate on null arrays!!");
        }
        sort(bitStrings, 0, bitStrings.length - 1, 0);
    }

    private void sort(String[] x, int l, int u, int depth) {
        if (l < u) {
            // this basically makes sure the shorter elements come first!
            for (int i = l; i <= u; i++) {
                if (x[i].length() < depth) {
                    swap(x, i, l);
                    l = l + 1;
                }
            }
            int m = l;
            for (int i = l; i <= u; i++) {
                if (x[i].length() > depth && x[i].charAt(depth) == '0') {
                    swap(x, i, m);
                    m = m + 1;
                }
            }
            sort(x, l, m - 1, depth + 1);
            sort(x, m, u, depth + 1);
        }
    }
}

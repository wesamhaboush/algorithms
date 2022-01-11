package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.set.SimpleIntSet;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

import static java.lang.StrictMath.*;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.ArrayUtils.swap;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class SelectMFromNWithEqualProbability implements IntFunction<int[]> {
    private final int[] elements;
    private final Algorithm algorithm;

    public SelectMFromNWithEqualProbability(int[] elements, Algorithm algorithm) {
        this.elements = requireNonNull(elements, "cannot operate on null elements");
        this.algorithm = requireNonNull(algorithm, "cannot operate on null algorithm");
    }

    @Override
    public int[] apply(int m) {
        if(m < 0 || m > elements.length) {
            throw new IllegalArgumentException("can only select subsets of size 0 to " + this.elements.length);
        }
        return this.algorithm.apply(new Specs(elements, m));
    }

    enum Algorithm implements Function<Specs, int[]> {
        /*
        Select := M
        Remaining := N
        for I = 1 to N do
            if RandReal(0, 1) < Select/Remaining then
                print I
                Select := Select - 1
            Remaining := Remaining - 1
         */
        ALG1 {
            @Override
            public int[] apply(Specs specs) {
                int[] elements = specs.elements;
                int[] selected = new int[specs.m()];

                int select = specs.m();
                int remaining = specs.elements().length;

                for (int i = 0; i < specs.elements().length; i++) {
                    double r = nextDouble(0.0, 1.0);
                    double p = (1.0 * select) / remaining;
//                    System.out.println("i: " + i + ", select: " + select + ", remaining: " + remaining + ", r: " + r + ", p: " + p);
                    if (r < p) {
                        selected[select - 1] = elements[i];
                        select = select - 1;
                    }
                    remaining = remaining - 1;
                }

                return selected;
            }
        },
        /*
        Same as above but recursive

        procedure RandSelect(M, N)
                pre 0 <= M <= N
                post M distinct integers from 1..N are printed in decreasing order
            if M > 0 then
                if RandReal(0, 1) < M/N then
                    print N
                    RandSelect(M - 1, N - 1)
                else
                    RandSelect(M, N-1)
         */
        ALG1_1 {
            @Override
            public int[] apply(Specs specs) {
                int[] elements = specs.elements;
                int[] selected = new int[specs.m()];

                int select = specs.m();
                int remaining = specs.elements().length;

                apply(select, remaining, selected, elements);

                return selected;
            }

            private void apply(int m, int n, int[] selected, int[] elements) {
                if(m > 0) {
                    if(random() < ((double)m)/n) {
                        selected[m - 1] = elements[n - 1];
                        apply(m - 1, n - 1, selected, elements);
                    } else {
                        apply(m, n - 1, selected, elements);
                    }
                }
            }
        },
        /*
        Same as previous but in reverse
         */
        ALG1_2 {
            @Override
            public int[] apply(Specs specs) {
                int[] elements = specs.elements;
                int[] selected = new int[specs.m()];

                int select = specs.m();
                int remaining = specs.elements().length;

                apply(select, remaining, selected, elements);

                return selected;
            }

            private void apply(int m, int n, int[] selected, int[] elements) {
                if(m > 0) {
                    if(random() < ((double)m)/n) {
                        selected[selected.length - m] = elements[n - 1];
                        apply(m - 1, n - 1, selected, elements);
                    } else {
                        apply(m, n - 1, selected, elements);
                    }
                }
            }
        },
        /*
        Initialize Set S to empty
        Size = 0
        while Size < M do
            T := RandInt(1, N)
            if T is not in S then
                Insert T in S
                Size := Size + 1
            Print the elements of S in sorted order
         */
        ALG2 {
            @Override
            public int[] apply(Specs specs) {
                SimpleIntSet set = new SimpleIntSet();
                while (set.size() < specs.m()) {
                    int t = specs.elements()[nextInt(0, specs.elements().length)];
                    set.add(t);
                }
                return set.toArray(null);
            }
        },
        /*
        for I := 1 to N do
            Swap(X[I], X[RandInt(I, N)])
        Sort(1, M)
         */
        ALG3 {
            @Override
            public int[] apply(Specs specs) {
                for (int i = 0; i < specs.elements().length; i++) {
                    swap(specs.elements(), i, nextInt(0, specs.elements().length));
                }
                int[] result = Arrays.copyOf(specs.elements(), specs.m());
                Arrays.sort(result);
                return result;
            }
        },
        /*
        for I := 1 to N do X[I] := I
        for I := 1 to M do
            Swap(X[I], X[RandInt(I, N)])
        Sort(1, M)
         */
        ALG4 {
            @Override
            public int[] apply(Specs specs) {
                for (int i = 0; i < specs.m(); i++) {
                    swap(specs.elements(), i, nextInt(0, specs.elements().length));
                }
                int[] result = Arrays.copyOf(specs.elements(), specs.m());
                Arrays.sort(result);
                return result;
            }
        },
        /*
        random_gap(p: real) {
            u = max(random(0.0, 1.0), epsilon)
            return floor(log(u) / log(1-p))
        }

        sample(data: sequence, p: real) {
            advance(data, random_gap(p))
            while not end(data) {
                emit next(data) to output
                advance(data, random_gap(p))
            }
        }

(* S has items to sample, R will contain the result *)
ReservoirSample(S[1..n], R[1..k])
  // fill the reservoir array
  for i = 1 to k
      R[i] := S[i]

  (* random() generates a uniform (0,1) random number *)
  W := exp(log(random())/k)

  while i <= n
      i := i + floor(log(random())/log(1-W)) + 1
      if i <= n
          (* replace a random item of the reservoir with item i *)
          R[randomInteger(1,k)] := S[i]  // random index between 1 and k, inclusive
          W := W * exp(log(random())/k)
         */
        ALG5 {
            @Override
            public int[] apply(Specs specs) {
                if(specs.m() == 0) {
                    return new int[0];
                }
                int[] selected = new int[specs.m()];
                System.arraycopy(specs.elements(), 0, selected, 0, specs.m());
                double W = exp(log(random()) / specs.m());
                int i = specs.m() - 1;
                while (i < specs.elements().length) {
                    i = i + (int) floor(log(random()) / log(1 - W)) + 1;
                    if (i < specs.elements().length) {
                        selected[nextInt(0, specs.m())] = specs.elements()[i];
                        W = W * exp(log(random()) / specs.m());
                    }
                }
                return selected;
            }
        }
    }

    private static record Specs(int[] elements, int m) {
    }
}

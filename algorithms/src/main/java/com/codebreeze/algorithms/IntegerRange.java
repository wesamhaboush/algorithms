package com.codebreeze.algorithms;

public class IntegerRange
{
    private final int min;
    private final int max;

    public IntegerRange(final int low, final int high)
    {
        assert (0 <= low && low <= high);
        if (!(0 <= low && low <= high))
        {
            throw new IllegalArgumentException("number are not in low high order " + low + ", " + high);
        }

        this.min = low;
        this.max = high;
    }

    public int getMin()
    {
        return min;
    }

    public int getMax()
    {
        return max;
    }

    @Override
    public String toString()
    {
        return "[" + min + ',' + max + ']';
    }

    public boolean contains(final int value)
    {
        return min <= value && value <= max;
    }

    public boolean containsOrFail(final int value)
    {
        if (!(min <= value && value <= max))
        {
            throw new IndexOutOfBoundsException("invalid index " + value);
        }
        return true;
    }

    public boolean contains(final IntegerRange range)
    {
        return min <= range.min && range.max <= max;
    }

    public boolean containsOrFail(final IntegerRange range)
    {
        if (!(min <= range.min && range.max <= max))
        {
            throw new IndexOutOfBoundsException("invalid range " + range.min + ", " + range.max);
        }
        return true;
    }

    public int size()
    {
        return max - min + 1;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof IntegerRange))
        {
            return false;
        }

        IntegerRange positiveRange = (IntegerRange) o;

        if (max != positiveRange.max)
        {
            return false;
        }
        return min == positiveRange.min;

    }

    @Override
    public int hashCode()
    {
        return 29 * min + max;
    }

    public static IntegerRange copyOf(final IntegerRange range)
    {
        return new IntegerRange(range.min, range.max);
    }
}

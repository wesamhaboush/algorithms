package com.codebreeze.algorithms.primitive.collections.set;

import com.codebreeze.algorithms.pearls.Primes;
import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleIntSet {
    enum GrowthPolicy {
        Relative,
        Absolute
    }

    private static final GrowthPolicy DEFAULT_GROWTH_POLICY = GrowthPolicy.Relative;
    private static final double DEFAULT_GROWTH_FACTOR = 1.0;
    private static final int DEFAULT_GROWTH_CHUNK = 10;
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int size;
    private transient int[][] data;
    private GrowthPolicy growthPolicy = GrowthPolicy.Absolute;
    private double growthFactor = DEFAULT_GROWTH_FACTOR;
    private int growthChunk = DEFAULT_GROWTH_CHUNK;
    private double loadFactor = DEFAULT_LOAD_FACTOR;

    //The next size at which to expand the data[].
    private int expandAt;

    private SimpleIntSet(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("cannot work with negative capacity: " + capacity);
        }
        data = new int[capacity][];
        size = 0;
        expandAt = (int) Math.round(loadFactor * capacity);
    }

    public SimpleIntSet() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleIntSet(int[] a) {
        this();
        for (int j : a) {
            add(j);
        }
    }

    private void ensureCapacity(int elements) {
        if (elements >= expandAt) {
            int newcapacity;
            if (growthPolicy == GrowthPolicy.Relative) {
                newcapacity = (int) (data.length * (1.0 + growthFactor));
            } else {
                newcapacity = data.length + growthChunk;
            }
            if (newcapacity * loadFactor < elements) {
                newcapacity = (int) Math.round(((double) elements / loadFactor));
            }
            newcapacity = Primes.nextPrime(newcapacity);
            expandAt = (int) Math.round(loadFactor * newcapacity);

            int[][] newdata = new int[newcapacity][];

            //  re-hash
            for (int[] list : data) {
                if (list != null) {
                    for (int v : list) {
                        int index = Math.abs(Objects.hash(v)) % newdata.length;
                        newdata[index] = addList(newdata[index], v);
                    }
                }
            }

            data = newdata;
        }
    }

    private int[] addList(int[] list, int v) {
        if (list == null) {
            return new int[]{v};
        }
        int[] newlist = new int[list.length + 1];
        System.arraycopy(list, 0, newlist, 0, list.length);
        newlist[list.length] = v;
        return newlist;
    }

    private int[] removeList(int[] list, int index) {
        if (list.length == 1) {
            return null;
        }
        int[] newlist = new int[list.length - 1];
        int n = 0;
        for (int i = 0; i < index; i++) {
            newlist[n++] = list[i];
        }
        for (int i = index + 1; i < list.length; i++) {
            newlist[n++] = list[i];
        }
        return newlist;
    }

    private int searchList(int[] list, int v) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == v) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(int v) {
        ensureCapacity(size + 1);

        int index = Math.abs(Objects.hash(v)) % data.length;
        int[] list = data[index];
        if (list == null) {
            data[index] = new int[]{v};
            size++;
            return true;
        }
        for (int j : list) {
            if (j == v) {
                return false;
            }
        }
        data[index] = addList(data[index], v);
        size++;
        return true;
    }

    public IntIterator iterator() {
        return new IntIterator() {
            int currList = nextList(0);
            int currInt = 0;
            int lastList = -1;
            int lastInt;
            int lastValue;

            int nextList(int index) {
                while (index < data.length && data[index] == null) {
                    index++;
                }
                return index < data.length ? index : -1;
            }

            public boolean hasNext() {
                return currList != -1;
            }

            public int next() {
                if (currList == -1) {
                    throw new NoSuchElementException("Attempt to iterate past iterator's last element.");
                }
                lastList = currList;
                lastInt = currInt;
                lastValue = data[currList][currInt];
                if (currInt == data[currList].length - 1) {
                    currList = nextList(currList + 1);
                    currInt = 0;
                } else {
                    currInt++;
                }
                return lastValue;
            }

            public void remove() {
                if (lastList == -1) {
                    throw new IllegalStateException("Attempt to remove element from iterator that has no current element.");
                }
                if (currList == lastList) {
                    currInt--;
                }
                data[lastList] = removeList(data[lastList], lastInt);
                size--;
                lastList = -1;
            }
        };
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    public boolean contains(int v) {
        int[] list = data[Math.abs(Objects.hash(v)) % data.length];
        if (list == null) {
            return false;
        }
        return searchList(list, v) != -1;
    }

    public int hashCode() {
        int h = 0;
        for (int[] list : data) {
            if (list != null) {
                for (int j : list) {
                    h += j;
                }
            }
        }
        return h;
    }

    public boolean remove(int v) {
        int index = Math.abs(Objects.hash(v)) % data.length;
        int[] list = data[index];
        if (list != null) {
            int lindex = searchList(list, v);
            if (lindex == -1) {
                return false;
            }
            data[index] = removeList(list, lindex);
            size--;
            return true;
        }
        return false;
    }

    public int[] toArray(int[] a) {
        if (a == null || a.length < size) {
            a = new int[size];
        }

        int p = 0;
        for (int[] list : data) {
            if (list != null) {
                for (int i : list) {
                    a[p++] = i;
                }
            }
        }
        return a;
    }

    public int[] toArray() {
        return toArray(null);
    }
}

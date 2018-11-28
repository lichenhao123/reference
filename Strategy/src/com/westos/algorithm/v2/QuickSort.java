package com.westos.algorithm.v2;

import java.util.Comparator;

public class QuickSort implements Sort {
    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        q(array, 0, array.length - 1, comparator);

    }

    public <T> void q(T[] array, int lo, int hi, Comparator<? super T> comparator) {
        if (lo >= hi) {
            return;
        }
        int mi = partition(array, lo, hi, comparator);
        q(array, lo, mi - 1, comparator);
        q(array, mi + 1, hi, comparator);
    }

    private <T> int partition(T[] array, int lo, int hi, Comparator<? super T> comparator) {
        T pivot = array[lo];
        int i = lo;
        int j = hi;
        while (i < j) {
            while (i < j && comparator.compare(pivot, array[j]) <= 0) {
                j--;
            }
            if (i < j) {
                array[i++] = array[j];
            }
            while (i < j && comparator.compare(pivot, array[i]) >= 0) {
                i++;
            }
            if (i < j) {
                array[j--] = array[i];
            }
        }
        array[i] = pivot;
        return i;
    }

}

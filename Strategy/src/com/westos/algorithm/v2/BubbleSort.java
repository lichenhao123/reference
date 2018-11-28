package com.westos.algorithm.v2;

import java.util.Comparator;

public class BubbleSort implements Sort {

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        // 重复 n-1 次
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // 每重复一次，最后一个元素是最大的，所以下次不必参与比较
            for (int j = 0; j < n - 1 - i; j++) {
                // 相邻两个元素比较， 如果前大后小则交换
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            // 重复一次后没有发生交换，说明已经有序，break
            if (!swapped) {
                break;
            }
        }
    }

    private <T> void swap(T[] array, int x, int y) {
        T t = array[x];
        array[x] = array[y];
        array[y] = t;
    }
}

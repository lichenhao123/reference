package com.westos.algorithm.v2;

import java.util.Comparator;

public class SelectionSort implements Sort {

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        // 重复 n-1 次
        for (int i = 0; i < n - 1; i++) {
            // 假设i下标的元素最小
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // 找出最小元素的下标
                if (comparator.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 如果假设的i不是最小，交换
            if (i != minIndex) {
                swap(array, minIndex, i);
            }
        }
    }

    private <T> void swap(T[] array, int x, int y) {
        T t = array[x];
        array[x] = array[y];
        array[y] = t;
    }
}

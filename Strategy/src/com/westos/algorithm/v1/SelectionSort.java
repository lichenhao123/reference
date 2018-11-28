package com.westos.algorithm.v1;

public class SelectionSort {

    public void sort(int[] array, Probe probe, Probe swapProbe) {
        int n = array.length;
        // 重复 n-1 次
        for (int i = 0; i < n - 1; i++) {
            // 假设i下标的元素最小
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                probe.plus();
                // 找出最小元素的下标
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 如果假设的i不是最小，交换
            if (i != minIndex) {
                swapProbe.plus();
                swap(array, minIndex, i);
            }
        }
    }

    private void swap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }
}

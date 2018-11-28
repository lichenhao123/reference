package com.westos.algorithm.v1;

public class BubbleSort {

    public void sort(int[] array, Probe probe, Probe swapProbe) {
        int n = array.length;
        // 重复 n-1 次
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // 每重复一次，最后一个元素是最大的，所以下次不必参与比较
            for (int j = 0; j < n - 1 - i; j++) {
                probe.plus();
                // 相邻两个元素比较， 如果前大后小则交换
                if (array[j] > array[j + 1]) {
                    swapProbe.plus();
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

    private void swap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }
}

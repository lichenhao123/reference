package com.westos.algorithm.v1;

public class QuickSort {

    public void sort(int[] array, Probe probe, Probe swapProbe) {
        q(array, 0, array.length - 1, probe, swapProbe);
    }

    public void q(int[] array, int lo, int hi, Probe probe, Probe swapProbe) {
        // 递归退出条件
        if (lo >= hi) {
            return;
        }
        // 分区方法，将整个数组分为两部分，比 pivot 小的部分和比 pivot 大的部分，返回值mi是 pivot 的下标
        int mi = partition(array, lo, hi, probe, swapProbe);
        // 对左边部分，递归
        q(array, lo, mi - 1, probe, swapProbe);
        // 对右边部分，递归
        q(array, mi + 1, hi, probe, swapProbe);
    }

    private int partition(int[] array, int lo, int hi, Probe probe, Probe swapProbe) {
        // 把 pivot 拿起来放手上
        int pivot = array[lo];
        // i 和 j 分别对应左边和右边的指针
        int i = lo;
        int j = hi;
        // 两个指针不碰头，说明没完
        while (i < j) {
            // 从右向左找比 pivot 小的，填入刚刚左边空出的位置
            while (i < j && pivot <= array[j]) {
                probe.plus();
                j--;
            }
            if (i < j) {
                swapProbe.plus();
                array[i++] = array[j];
            }
            // 从左向右找比 pivot 大的，填入刚刚右边空出的位置
            while (i < j && pivot >= array[i]) {
                probe.plus();
                i++;
            }
            if (i < j) {
                swapProbe.plus();
                array[j--] = array[i];
            }
        }
        // 最后的位置留给 pivot
        array[i] = pivot;
        return i;
    }
}

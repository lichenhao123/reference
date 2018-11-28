package com.westos.algorithm.v1;

import java.util.Arrays;

public class QuickSort2 {

    public void sort(int[] array, Probe probe, Probe swapProbe) {
        q(array, 0, array.length - 1, probe, swapProbe);
    }

    public void q(int[] array, int lo, int hi, Probe probe, Probe swapProbe) {
        if (lo >= hi) {
            return;
        }
        int mi = partition(array, lo, hi, probe, swapProbe);
        q(array, lo, mi, probe, swapProbe);
        q(array, mi + 1, hi, probe, swapProbe);
    }

    private int partition(int[] array, int lo, int hi, Probe probe, Probe swapProbe) {
        int pivot = array[lo];
        int i = lo - 1;
        int j = hi + 1;
        while (true) {
            do {
                probe.plus();
                i++;
            }
            while (pivot > array[i]);
            do {
                probe.plus();
                j--;
            }
            while (pivot < array[j]);
            if (i >= j) {
                return i;
            }
            swapProbe.plus();
            swap(array, i, j);
        }
    }

    private void swap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new QuickSort2().sort(array, probe, swapProbe);
        System.out.println(Arrays.toString(array));
        System.out.println(probe.count());
        System.out.println(swapProbe.count());

        array = new int[]{3, 2, 1, 5, 4};
        probe = new Probe();
        swapProbe = new Probe();
        new QuickSort2().sort(array, probe, swapProbe);
        System.out.println(Arrays.toString(array));
        System.out.println(probe.count());
        System.out.println(swapProbe.count());
        array = new int[]{5, 4, 4, 2, 1};
        probe = new Probe();
        swapProbe = new Probe();
        new QuickSort2().sort(array, probe, swapProbe);
        System.out.println(Arrays.toString(array));
        System.out.println(probe.count());
        System.out.println(swapProbe.count());
    }
}
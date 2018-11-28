package com.westos.algorithm.v1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort {

    public void sort(int[] array, Probe probe, Probe swapProbe) {
        int n = array.length;
        int[] b = Arrays.copyOf(array, n);
        split(array, b, 0, n, probe, swapProbe);
    }

    private void split(int[] a, int[] b, int lo, int hi, Probe probe, Probe swapProbe) {
        System.out.print("[lo:" + lo + " hi:" + hi + "]");
        System.out.println(Arrays.stream(a, lo, hi).mapToObj(s -> String.valueOf(s)).collect(Collectors.joining(",")));
        if (hi - lo < 2) {
            return;
        }
        int mi = (lo + hi) / 2;

        split(b, a, lo, mi, probe, swapProbe);
        split(b, a, mi, hi, probe, swapProbe);
        merge(b, a, lo, mi, hi, probe, swapProbe);
        System.out.println("a:" + Arrays.toString(a) + " b:" + Arrays.toString(b));
    }

    private void merge(int[] a, int[] b, int lo, int mi, int hi, Probe probe, Probe swapProbe) {
        int i = lo;
        int j = mi;
        for (int k = lo; k < hi; k++) {
            probe.plus();
            if (i < mi && j >= hi || i < mi && a[i] < a[j]) {
                swapProbe.plus();
                b[k] = a[i++];
            } else {
                swapProbe.plus();
                b[k] = a[j++];
            }
        }
    }


    public static void main(String[] args) {
        int[] array = {5, 4, 3, 6, 7, 2, 1};
        new MergeSort().sort(array, new Probe(), new Probe());

//        System.out.println("//////////////////////////////////");
//        Probe probe = new Probe();
//        Probe swapProbe = new Probe();
//        int[] b = new int[5];
//        new MergeSort().merge(new int[]{1, 3, 4, 2, 5}, b, 0, 3, 5, probe, swapProbe);
//        System.out.println(probe.count());
//        System.out.println(swapProbe.count());
    }
}

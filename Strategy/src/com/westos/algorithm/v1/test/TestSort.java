package com.westos.algorithm.v1.test;

import com.westos.algorithm.v1.*;
import org.junit.Assert;
import org.junit.Test;

public class TestSort {

    private int[] sorted = {1, 2, 3, 4, 5};

    @Test
    public void testBubbleSortWorst() {
        int[] array = {5, 4, 3, 2, 1};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new BubbleSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(10, probe.count());
        Assert.assertEquals(10, swapProbe.count());
    }

    @Test
    public void testBubbleSortBest() {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new BubbleSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(4, probe.count());
        Assert.assertEquals(0, swapProbe.count());
    }

    @Test
    public void testSelectionSortWorst() {
        int[] array = {5, 4, 3, 2, 1};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new SelectionSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(10, probe.count());
        Assert.assertEquals(2, swapProbe.count());
    }

    @Test
    public void testSelectionSortBest() {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new SelectionSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(10, probe.count());
        Assert.assertEquals(0, swapProbe.count());
    }

    @Test
    public void testInsertionSortWorst() {
        int[] array = {5, 4, 3, 2, 1};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new InsertionSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(10, probe.count());
        Assert.assertEquals(10, swapProbe.count());
    }

    @Test
    public void testInsertionSortBest() {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new InsertionSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(4, probe.count());
        Assert.assertEquals(0, swapProbe.count());
    }

    @Test
    public void testQuickSortWorst1() {
        int[] array = {5, 4, 3, 2, 1};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new QuickSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(8, probe.count());
        Assert.assertEquals(2, swapProbe.count());
    }

    @Test
    public void testQuickSortWorst2() {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new QuickSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(10, probe.count());
        Assert.assertEquals(0, swapProbe.count());
    }

    @Test
    public void testQuickSortBest() {
        int[] array = {3, 2, 1, 5, 4};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new QuickSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(4, probe.count());
        Assert.assertEquals(2, swapProbe.count());
    }


    @Test
    public void testMergeSortWorst1() {
        int[] array = {5, 4, 3, 2, 1};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new MergeSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(12, probe.count());
        Assert.assertEquals(12, swapProbe.count());
    }

    @Test
    public void testMergeSortWorst2() {
        int[] array = {1, 2, 3, 4, 5};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new MergeSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(12, probe.count());
        Assert.assertEquals(12, swapProbe.count());
    }

    @Test
    public void testMergeSortBest() {
        int[] array = {3, 2, 1, 5, 4};
        Probe probe = new Probe();
        Probe swapProbe = new Probe();
        new MergeSort().sort(array, probe, swapProbe);
        Assert.assertArrayEquals(sorted, array);
        Assert.assertEquals(12, probe.count());
        Assert.assertEquals(12, swapProbe.count());
    }
}

package com.westos.algorithm.v2;

import java.util.Comparator;

public class InsertionSort implements Sort {

    @Override
    public <T> void sort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        // 重复 n-1 次
        for (int i = 1; i < n; i++) {
            // 从1开始，拿一张牌在手上
            T hand = array[i];

            // j 代表桌面上牌的下标从 i-1 开始，到 0 为止
            int j = i - 1;
            while (j >= 0) {
                // 桌面上的牌比手里的牌大，桌面上的牌逐一往后移
                if (comparator.compare(array[j], hand) > 0) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
                j--;
            }
            // 手里的牌放入桌面
            array[j + 1] = hand;
        }
    }
}

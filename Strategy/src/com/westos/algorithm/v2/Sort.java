package com.westos.algorithm.v2;

import java.util.Comparator;

public interface Sort {

    <T> void sort(T[] array, Comparator<? super T> comparator);
}

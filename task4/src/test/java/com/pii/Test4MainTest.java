package com.pii;

import org.junit.jupiter.api.Test;
import org.pii.Task4Main;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.pii.Task4Main.countSwaps;

public class Test4MainTest {

    @Test
    void testCountSwapsSwapped() {
        long[] array = {2, 2, 2};
        assertThat(countSwaps(array, Task4Main.calcPivot(array))).isEqualTo(0);
    }

    @Test
    void testCountSwaps1() {
        long[] array = {4, 2, 7};
        assertThat(countSwaps(array, Task4Main.calcPivot(array))).isEqualTo(5);
    }

    @Test
    void testCountSwaps2() {
        long[] array = {4, 2, 6, 8};
        assertThat(countSwaps(array, Task4Main.calcPivot(array))).isEqualTo(8);
    }

    @Test
    void testCountSwaps3() {
        long[] array = {1, 10, 2, 9};
        assertThat(countSwaps(array, Task4Main.calcPivot(array))).isEqualTo(16);
    }
}

package org.pii;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2MainTest {

    @BeforeAll
    static void setup() {
    }

    @Test
    void testPointsOnCircle() {
        var circle = new Task2Main.Circle(
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(5)
        );
        var points = List.of(
                new Task2Main.Point(BigDecimal.valueOf(7), BigDecimal.valueOf(3)),
                new Task2Main.Point(BigDecimal.valueOf(-3), BigDecimal.valueOf(3)),
                new Task2Main.Point(BigDecimal.valueOf(2), BigDecimal.valueOf(8))
        );
        var expected = List.of(
                Task2Main.PointPosition.ON_CIRCLE,
                Task2Main.PointPosition.ON_CIRCLE,
                Task2Main.PointPosition.ON_CIRCLE
        );
        assertEquals(expected, Task2Main.getPointPositions(circle, points));
    }

    @Test
    void testPointsInsideCircle() {
        var circle = new Task2Main.Circle(
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(5)
        );
        var points = List.of(
                new Task2Main.Point(BigDecimal.valueOf(3), BigDecimal.valueOf(4)),
                new Task2Main.Point(BigDecimal.valueOf(0), BigDecimal.valueOf(2)),
                new Task2Main.Point(BigDecimal.valueOf(2), BigDecimal.valueOf(3))
        );
        var expected = List.of(
                Task2Main.PointPosition.INSIDE_CIRCLE,
                Task2Main.PointPosition.INSIDE_CIRCLE,
                Task2Main.PointPosition.INSIDE_CIRCLE
        );
        assertEquals(expected, Task2Main.getPointPositions(circle, points));
    }

    @Test
    void testGetPointPositions() {
        var circle = new Task2Main.Circle(
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(5)
        );
        var points = List.of(
                new Task2Main.Point(BigDecimal.valueOf(4.5), BigDecimal.valueOf(6.5)),
                new Task2Main.Point(BigDecimal.valueOf(2.0), BigDecimal.valueOf(7.999)),
                new Task2Main.Point(BigDecimal.valueOf(2.0), BigDecimal.valueOf(8.001))
        );
        var expected = List.of(
                Task2Main.PointPosition.INSIDE_CIRCLE,
                Task2Main.PointPosition.INSIDE_CIRCLE,
                Task2Main.PointPosition.OUTSIDE_CIRCLE
        );
        assertEquals(expected, Task2Main.getPointPositions(circle, points));
    }
}

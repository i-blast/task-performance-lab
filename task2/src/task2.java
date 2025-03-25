package org.pii;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task2Main {

    private static final Logger LOG = Logger.getLogger(Task2Main.class.getName());

    public static void main(String[] args) {

        configureLogger();

        String circleFilePath;
        String pointsFilePath;
        if (args.length != 2) {
            LOG.severe("Need 2 string args: circle file name & points file name.");
            throw new IllegalArgumentException("Program args error.");
        }
        circleFilePath = args[0];
        pointsFilePath = args[1];

        Circle circle;
        List<Point> points;
        try {
            circle = readCircle(circleFilePath);
            LOG.fine(circle.toString());

            points = readPoints(pointsFilePath);
            points.forEach(point -> LOG.fine(point.toString()));
        } catch (IOException exc) {
            LOG.severe("Problem with input files.");
            throw new IllegalArgumentException("Files reading error.", exc);
        }

        getPointPositions(circle, points).stream()
                .map(PointPosition::getPositionId)
                .forEach(System.out::println);
    }

    public static Circle readCircle(String circleFilePath) throws IOException {

        try (var scanner = new Scanner(Files.newInputStream(Path.of(circleFilePath)))) {
            var circleCenterCoords = scanner.nextLine();
            var circleRadius = scanner.nextLine();
            return new Circle(
                    new BigDecimal(circleCenterCoords.split("\\s+")[0]),
                    new BigDecimal(circleCenterCoords.split("\\s+")[1]),
                    new BigDecimal(circleRadius)
            );
        }
    }

    public static List<Point> readPoints(String pointsFilePath) throws IOException {

        var result = new ArrayList<Point>();

        try (var scanner = new Scanner(Files.newInputStream(Path.of(pointsFilePath)))) {
            while (scanner.hasNextLine()) {
                var coords = scanner.nextLine().split("\\s+");
                result.add(new Point(
                        new BigDecimal(coords[0]),
                        new BigDecimal(coords[1])
                ));
            }
            return result;
        }
    }

    public static List<PointPosition> getPointPositions(
            Circle circle,
            List<Point> points
    ) {

        var result = new ArrayList<PointPosition>();

        for (Point point : points) {
            var dx = point.a().subtract(circle.x());
            var dy = point.b().subtract(circle.y());
            var distanceSqr = dx.multiply(dx).add(dy.multiply(dy));
            var radiusSqr = circle.r().multiply(circle.r());

            var position = switch (distanceSqr.compareTo(radiusSqr)) {
                case -1 -> PointPosition.INSIDE_CIRCLE;
                case 0 -> PointPosition.ON_CIRCLE;
                case 1 -> PointPosition.OUTSIDE_CIRCLE;
                default -> throw new IllegalStateException("Unexpected.");
            };
            result.add(position);
        }
        return result;
    }

    /**
     * @param x координата центра окружности по оси x
     * @param y координата центра окружности по оси y
     * @param r радиус окружности
     */
    record Circle(
            BigDecimal x,
            BigDecimal y,
            BigDecimal r
    ) {
    }

    /**
     * @param a координата точки по оси x
     * @param b координата точки по оси y
     */
    record Point(
            BigDecimal a,
            BigDecimal b
    ) {
    }

    enum PointPosition {

        ON_CIRCLE((byte) 0),

        INSIDE_CIRCLE((byte) 1),

        OUTSIDE_CIRCLE((byte) 2);

        private byte positionId;

        PointPosition(byte positionId) {
            this.positionId = positionId;
        }

        public byte getPositionId() {
            return positionId;
        }
    }

    private static void configureLogger() {
        var handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
//        handler.setLevel(Level.FINE);
        LOG.addHandler(handler);
        LOG.setLevel(Level.FINE);
    }
}

package org.pii;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.stream.IntStream;

public class GeneratePoints {

    private static final String POINTS_OUT_FILENAME = "points.txt";

    public static void main(String[] args) {

        try {

            var lines = IntStream.range(0, 100)
                    .mapToObj(i -> String.join(
                            " ",
                            getRandomBigDecimal().toPlainString(),
                            getRandomBigDecimal().toPlainString()
                    ))
                    .toList();
            Files.write(Path.of(POINTS_OUT_FILENAME), lines);
        } catch (IOException exc) {
            throw new RuntimeException("file write err", exc);
        }
    }

    private static BigDecimal getRandomBigDecimal() {

        var random = new Random();
        BigDecimal mantissa = BigDecimal.valueOf(1 + random.nextDouble() * 9);
        int exponent = -38 + random.nextInt(77);
        return mantissa.scaleByPowerOfTen(exponent)
                .round(new MathContext(50, RoundingMode.HALF_UP));
    }

/*    private static double getRandomDouble() {

        var random = new Random();
        return (double) 1E-38 + (double) (1E38 - 1E-38) * random.nextDouble();
    }*/
}

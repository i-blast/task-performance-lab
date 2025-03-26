package org.pii;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.pii.Task3Main.createValuesMap;
import static org.pii.Task3Main.fillReportWithValues;

public class Task3MainTest {

    private static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new Gson();
    }

    @Test
    void testFillReportWithValuesDefault() {
        var test = new Task3Main.TValue();
        test.setId(1L);
        var valuesByIds = Map.of(1L, "value");
        fillReportWithValues(List.of(test), valuesByIds);
        assertEquals("value", test.getValue());
    }

    @Test
    void testFillReportWithValuesBadId() {
        var test = new Task3Main.TValue();
        test.setId(3L);
        var valuesByIds = Map.of(1L, "test");
        fillReportWithValues(List.of(test), valuesByIds);
        assertNull(test.getValue());
    }

    @Test
    void testFillReportWithValuesEmptyList() {
        var valuesByIds = Map.of(1L, "Test Value");
        assertDoesNotThrow(() -> fillReportWithValues(List.of(), valuesByIds),
                "Метод должен корректно работать с пустым списком");
    }

    @Test
    void testFillReportWithValues_RealData() {
        Task3Main.VValues valuesData;
        try (var reader = new FileReader(Path.of("src/test/resources/values.json").toFile())) {
            valuesData = gson.fromJson(reader, Task3Main.VValues.class);
        } catch (IOException exc) {
            throw new RuntimeException("value.json error", exc);
        }

        var valuesByIds = createValuesMap(valuesData);

        Task3Main.TTests testsData;
        try (var reader = new FileReader(Path.of("src/test/resources/tests.json").toFile())) {
            testsData = gson.fromJson(reader, Task3Main.TTests.class);
        } catch (IOException exc) {
            throw new RuntimeException("tests.json err", exc);
        }

        fillReportWithValues(testsData.tests(), valuesByIds);

        assertEquals("passed", testsData.tests().get(0).getValue());
        assertEquals("failed", testsData.tests().get(3).getValue());
        var performanceTest = testsData.tests().get(2);
        assertEquals("passed", performanceTest.getValues().get(0).getValue());
        var stabilityTest = performanceTest.getValues().get(1);
        assertEquals("failed", stabilityTest.getValues().get(0).getValues().get(1).getValue()); // id: 690
        var securityTest = testsData.tests().get(3);
        assertEquals("passed", securityTest.getValues().get(0).getValue());
    }
}

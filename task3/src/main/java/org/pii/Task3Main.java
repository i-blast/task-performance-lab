package org.pii;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task3Main {

    private static final Logger LOG = Logger.getLogger(Task3Main.class.getName());

    public static void main(String[] args) {

        configureLogger();

        if (args.length != 3) {
            LOG.severe("Need 3 file paths: values.json tests.json report.json.");
            throw new IllegalArgumentException("Program args error.");
        }
        var valuesFilePath = Path.of(args[0]);
        var testsFilePath = Path.of(args[1]);
        var reportFilePath = Path.of(args[2]);

        var gson = new Gson();

        VValues valuesData;
        try (var reader = new JsonReader(new FileReader(valuesFilePath.toFile()))) {
            valuesData = gson.fromJson(reader, VValues.class);
        } catch (IOException exc) {
            LOG.severe("Values reading err.");
            throw new RuntimeException("Values file reading err", exc);
        }
        LOG.fine(gson.toJson(valuesData));
        var valuesByIds = createValuesMap(valuesData);

        TTests testsData;
        try (var reader = new JsonReader(new FileReader(testsFilePath.toFile()))) {
            testsData = gson.fromJson(reader, TTests.class);
        } catch (IOException exc) {
            LOG.severe("Tests reading err.");
            throw new RuntimeException("Tests file reading err", exc);
        }
        LOG.fine(gson.toJson(testsData));
/*        try (var writer = new FileWriter("outtest.json")) {
            gson.toJson(testsData, writer);
        } catch (IOException exc) {
            LOG.fine("write error");
        }*/

        LOG.fine(valuesByIds.toString());
        fillReportWithValues(testsData.tests(), valuesByIds);

        try (var writer = new FileWriter(reportFilePath.toFile())) {
            gson.toJson(testsData, writer);
        } catch (IOException exc) {
            LOG.severe("Failed to write report file.");
            throw new RuntimeException("Report file writing error", exc);
        }

    }

    public static Map<Long, String> createValuesMap(VValues valuesData) {

        var result = new HashMap<Long, String>();
        for (var vValue : valuesData.values()) {
            result.put(vValue.id(), vValue.value());
        }
        return result;
    }

    public static void fillReportWithValues(
            List<TValue> tests,
            Map<Long, String> valuesByIds
    ) {

        for (var testValue : tests) {
            if (valuesByIds.containsKey(testValue.id)) {
                testValue.setValue(valuesByIds.get(testValue.id));
            }
            if (testValue.values != null && !testValue.values.isEmpty()) {
                fillReportWithValues(testValue.values, valuesByIds);
            }
        }
    }

    record VValue(
            long id,
            String value
    ) {
    }

    record VValues(
            List<VValue> values
    ) {
    }

    static class TValue {

        private long id;
        private String title;
        private String value;
        private List<TValue> values = new ArrayList<>();

        TValue() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<TValue> getValues() {
            return values;
        }

        public void setValues(List<TValue> values) {
            this.values = values;
        }
    }

    record TTests(
            List<TValue> tests
    ) {
    }

    private static void configureLogger() {
        var handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
//        handler.setLevel(Level.FINE);
        LOG.addHandler(handler);
        LOG.setLevel(Level.FINE);
    }
}


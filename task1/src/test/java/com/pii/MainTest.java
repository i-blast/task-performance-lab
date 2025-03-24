package com.pii;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.pii.Main.findPath;

public class MainTest {

    @Test
    void testShouldFindPath() {
        assertThat(findPath(4, 3)).isEqualTo("13");

        assertThat(findPath(5, 4)).isEqualTo("14253");

        assertThat(findPath(3, 3)).isEqualTo("132");

        assertThat(findPath(3, 4)).isEqualTo("1");

        assertThat(findPath(4, 5)).isEqualTo("1");

        assertThat(findPath(2, 4)).isEqualTo("12");

        assertThat(findPath(1, 1)).isEqualTo("1");
    }
}

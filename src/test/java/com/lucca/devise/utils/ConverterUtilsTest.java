package com.lucca.devise.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConverterUtilsTest {

    @Test
    void roundValueTest() {
        Assertions.assertEquals(0.1235, ConverterUtils.roundValue(0.12345, 4));
        Assertions.assertEquals(0.123, ConverterUtils.roundValue(0.12345, 3));
        Assertions.assertEquals(0.12, ConverterUtils.roundValue(0.12345, 2));
        Assertions.assertEquals(0.1, ConverterUtils.roundValue(0.12345, 1));
        Assertions.assertEquals(0, ConverterUtils.roundValue(0.12345, 0));
        Assertions.assertEquals(0, ConverterUtils.roundValue(0.12345, -1));
    }
}

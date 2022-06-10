package com.lucca.devise.utils;

public class ConverterUtils {
    /**
     * Round value to n decimals
     *
     * @param value value to round
     * @param n number of decimals
     * @return round value
     */
    public static double roundValue(final double value, final int n) {
        if (n < 0) {
            return 0;
        }
        double scale = Math.pow(10, n);
        return Math.round(value * scale) / scale;
    }
}

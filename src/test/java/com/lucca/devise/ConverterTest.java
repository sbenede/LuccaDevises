package com.lucca.devise;

import com.lucca.devise.model.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConverterTest {

    @Test
    void getExchangeToDoTest() {
        Exchange devise = new Exchange("AUD", "CHF", 0.9661);
        Exchange devise2 = new Exchange("JPY", "KWU", 13.1151);
        Exchange devise3 = new Exchange("EUR", "CHF", 1.2053);
        Exchange devise4 = new Exchange("AUD", "JPY", 86.0305);
        Exchange devise5 = new Exchange("EUR", "USD", 1.2989);
        Exchange devise6 = new Exchange("JPY", "INR", 0.6571);
        Exchange devise7 = new Exchange("USD", "INR", 0.6571);
        Exchange devise8 = new Exchange("INR", "RMB", 0.6571);
        Exchange devise9 = new Exchange("RMB", "JPY", 0.6571);

        List<Exchange> devises = new ArrayList<>();
        devises.add(devise);
        devises.add(devise2);
        devises.add(devise3);
        devises.add(devise4);
        devises.add(devise5);
        devises.add(devise6);
        devises.add(devise7);
        devises.add(devise8);
        devises.add(devise9);

        String start = "EUR";
        String end = "JPY";

        List<Exchange> res = new Converter().getExchangeToDo(devises, start, end);

        // shortest way is 3
        Assertions.assertEquals(3, res.size());

        // null exchange won't crash
        devises.add(null);
        res = new Converter().getExchangeToDo(devises, start, end);
        Assertions.assertEquals(3, res.size());
    }

    @Test
    void convertTest() {
        Exchange devise = new Exchange("EUR", "CHF", 1.2053);
        Exchange devise2 = new Exchange("AUD", "CHF", 0.9661);
        Exchange devise3 = new Exchange("AUD", "JPY", 86.0305);

        List<Exchange> devises = new ArrayList<>();
        devises.add(devise);
        devises.add(devise2);
        devises.add(devise3);

        int amount = new Converter().convert(devises, "EUR", 550);
        Assertions.assertEquals(59032, amount);

        // case of empty list
        amount = new Converter().convert(Collections.emptyList(), "EUR", 550);
        Assertions.assertEquals(0, amount);

        // case of 0 to convert
        amount = new Converter().convert(devises, "EUR", 0);
        Assertions.assertEquals(0, amount);

        // case of unknown currency
        amount = new Converter().convert(devises, "NFC", 550);
        Assertions.assertEquals(550, amount);
    }
}

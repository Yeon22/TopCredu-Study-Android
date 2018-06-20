package com.example.test.my_6_calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    BigDecimalCalculator calc = new BigDecimalCalculator();

    @Test
    public void test1() {
        String txt = "(2+2)+2";
        txt = "2^3";
        txt = "|4";
        String result = calc.brackets(txt);
        System.out.println("result = " + result);
    }
}
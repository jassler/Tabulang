package de.hskempten.tests;

import de.hskempten.tabulang.nodes.Number;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    Number t1 = new Number("2341.53", null);
    Number t2 = new Number("23.61", null);
    Number t3 = new Number("0.0", null);
    Number t4 = new Number("123", null);

    @Test
    void testAdd() {
        assertEquals("2365.14", t1.add(t2).getValueString(20));
    }

    @Test
    void testSubtract() {
        assertEquals("2317.92", t1.subtract(t2).getValueString(20));
    }

    @Test
    void testMultiply() {
        assertEquals("55283.5233", t1.multiply(t2).getValueString(20));
    }

    @Test
    void testDivide() {
        assertEquals("99.17534942820838627700127064803", t1.divide(t2).getValueString(29));
    }

}
package de.hskempten.tests;

import de.hskempten.tabulang.nodes.Number;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    Number p1 = new Number("2341.53", null);
    Number p2 = new Number("23.61", null);
    Number p3 = new Number("123", null);
    Number z = new Number("0.0", null);
    Number n1 = new Number("-15", null);
    Number n2 = new Number("-13.543", null);
    Number n3 = new Number("-64.23", null);
    Number n5 = new Number("-0.00032", null);

    @Test
    void testCreateNumber() {
        String ex1 = "2341.53";
        String ex3 = "0.0";
        String ex4 = "123";
        String exn1 = "-15";
        String exn2 = "-13.543";
        String exn5 = "-0.00032";
        assertEquals(ex1, p1.getValueString(20));
        assertEquals(ex3, z.getValueString(20));
        assertEquals(ex4, p3.getValueString(0));
        assertEquals(exn1, n1.getValueString(0));
        assertEquals(exn2, n2.getValueString(20));
        assertEquals(exn5, n5.getValueString(20));
        assertEquals(ex3, n5.getValueString(3));

    }

    @Test
    void testAdd() {
        assertEquals("2365.14", p1.add(p2).getValueString(20));
        assertEquals("-28.543", n1.add(n2).getValueString(20));
        assertEquals("2327.987", p1.add(n2).getValueString(20));
        assertEquals(p1.getValueString(20), p1.add(z).getValueString(20));
        assertEquals(n1.add(p1).getValueString(20), p1.add(n1).getValueString(20));
    }

    @Test
    void testSubtract() {
        assertEquals("2317.92", p1.subtract(p2).getValueString(20));
        assertEquals("-1.457", n1.subtract(n2).getValueString(20));
        assertEquals("-2341.53", z.subtract(p1).getValueString(20));
    }

    @Test
    void testMultiply() {
        assertEquals("55283.5233", p1.multiply(p2).getValueString(20));
        assertEquals("869.86689", n2.multiply(n3).getValueString(20));
        assertEquals("-319.75023", p2.multiply(n2).getValueString(20));
        assertEquals(n2.multiply(p2).getValueString(20), p2.multiply(n2).getValueString(20));
        assertEquals("0.0", p2.multiply(z).getValueString(20));
        assertEquals("0.0", n2.multiply(z).getValueString(20));
    }

    @Test
    void testDivide() {
        assertEquals("99.17534942820838627700127064803", p1.divide(p2).getValueString(29));
        assertEquals("-172.89596101306948238942627187", p1.divide(n2).getValueString(26));
        assertEquals("-0.005783825105806886949985693115", n2.divide(p1).getValueString(30));
    }
}
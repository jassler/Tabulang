package de.hskempten.tests;

import de.hskempten.tabulang.datatypes.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    public void testTupleDefaultValues() {
        Tuple t = new Tuple(Arrays.asList("1", 2));
        assertEquals(Arrays.asList("1", 2), t.getObjects());
        assertEquals(Arrays.asList("0", "1"), t.getNames());
        assertTrue(t.isHorizontal());
    }

    @Test
    public void testTupleNameGeneration() {
        Tuple t = new Tuple(Arrays.asList(12, 13, "1", "2"));
        assertArrayEquals(new String[]{"0", "1", "2", "3"}, t.getNames().toArray(new String[0]));

        t.getNames().set(0, "4");
        assertArrayEquals(new String[]{"4", "1", "2", "3"}, t.getNames().toArray(new String[0]));
    }

    @Test
    public void testTupleAccess() {
        final Tuple t = new Tuple(Arrays.asList(12, 13, "1", "2"));
        assertEquals(12, t.get("0"));
        assertEquals(13, t.get("1"));
        assertEquals("1", t.get("2"));
        assertEquals("2", t.get("3"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> t.get("4"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> t.get("-1"));
        assertThrows(NumberFormatException.class, () -> t.get("a"));

        final Tuple u = new Tuple(Arrays.asList(1, 4, 2), Arrays.asList("alpha", "beta", "gamma"));
        assertEquals(1, u.get("alpha"));
        assertEquals(4, u.get("beta"));
        assertEquals(2, u.get("gamma"));
        assertEquals(1, u.get("0"));
        assertEquals(4, u.get("1"));
        assertEquals(2, u.get("2"));
    }

    @Test
    public void testTupleAppend() {
        Tuple t = new Tuple(Arrays.asList(12, 13));
        t.setHorizontal(false);

        t = t.concatenate(new Tuple(Arrays.asList("a", "b"), Arrays.asList("c", "d")));
        assertEquals(Arrays.asList(12, 13, "a", "b"), t.getObjects());
        assertEquals(Arrays.asList("0", "1", "c", "d"), t.getNames());
        assertFalse(t.isHorizontal());
    }

    @Test
    public void testTupleProjection() {
        Tuple t = new Tuple(Arrays.asList(12, 13, "1", "2"));

        assertEquals(new Tuple(
                Collections.emptyList()
        ), t.projection());

        assertEquals(new Tuple(
                Collections.singletonList("1"),
                Collections.singletonList("2")
        ), t.projection(2));

        assertEquals(new Tuple(
                Arrays.asList(13, "2", 12, "1", 12),
                Arrays.asList("1", "3", "0", "2", "0")
        ), t.projection(1, 3, 0, 2, 0));
    }
}
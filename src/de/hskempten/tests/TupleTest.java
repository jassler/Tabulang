package de.hskempten.tests;

import de.hskempten.tabulang.datatypes.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    public void testTupleDefaultValues() {
        Tuple<Object> t = new Tuple<>(new Object[]{"1", 2});
        assertEquals(Arrays.asList("1", 2), t.getElements());
        assertEquals(Arrays.asList("0", "1"), t.getNames().getNames());
        assertTrue(t.isHorizontal());
    }

    @Test
    public void testTupleNameGeneration() {
        Tuple<Object> t = new Tuple<>(new Object[]{12, 13, "1", "2"});
        assertEquals(Arrays.asList("0", "1", "2", "3"), t.getNames().getNames());

        t.getNames().getNames().set(0, "4");
        assertEquals(Arrays.asList("4", "1", "2", "3"), t.getNames().getNames());
    }

    @Test
    public void testTupleAccess() {
        final Tuple<Object> t = new Tuple<>(new Object[]{12, 13, "1", "2"});
        assertEquals(12, t.get("0"));
        assertEquals(13, t.get("1"));
        assertEquals("1", t.get("2"));
        assertEquals("2", t.get("3"));
        assertThrows(IndexOutOfBoundsException.class, () -> t.get("4"));
        assertThrows(IndexOutOfBoundsException.class, () -> t.get("-1"));
        assertThrows(NumberFormatException.class, () -> t.get("a"));

        final Tuple<Integer> u = new Tuple<>(new Integer[]{1, 4, 2}, new String[]{"alpha", "beta", "gamma"});
        assertEquals(1, u.get("alpha"));
        assertEquals(4, u.get("beta"));
        assertEquals(2, u.get("gamma"));
        assertEquals(1, u.get("0"));
        assertEquals(4, u.get("1"));
        assertEquals(2, u.get("2"));
    }

    @Test
    public void testTupleAppend() {
        Tuple<Object> t = new Tuple<>(new Object[]{12, 13});
        t.setHorizontal(false);

        t = t.concatenate(new Tuple<>(new String[]{"a", "b"}, new String[]{"c", "d"}));
        assertEquals(Arrays.asList(12, 13, "a", "b"), t.getElements());
        assertEquals(Arrays.asList("0", "1", "c", "d"), t.getNames().getNames());
        assertFalse(t.isHorizontal());
    }

    @Test
    public void testTupleProjection() {
        Tuple<Object> t = new Tuple<>(new Object[]{12, 13, "1", "2"});

        assertEquals(new Tuple<>(
                Collections.emptyList()
        ), t.projection());

        assertEquals(new Tuple<>(
                Collections.singletonList("1"),
                Collections.singletonList("2")
        ), t.projection(2));

        assertEquals(new Tuple<>(
                new Object[]{13, "2", 12, "1"},
                new String[]{"1", "3", "0", "2"}
        ), t.projection(1, 3, 0, 2));

        assertEquals(new Tuple<>(
                new Object[]{13, "2", 12, "1"},
                new String[]{"1", "3", "0", "2"}
        ), t.projection("1", "3", "0", "2"));

        assertEquals(new Tuple<>(
                new Object[]{13, "2", 12, "1"},
                new String[]{"beta", "delta", "alpha", "gamma"}
        ), t.newTupleWithNames(Arrays.asList("alpha", "beta", "gamma", "delta")).projection("beta", "delta", "alpha", "gamma"));
    }

    @Test
    public void testTupleNewNames() {
        Tuple<Object> t = new Tuple<>(new Object[]{12, 13, "1", "2"}, new String[]{"a", "b", "c", "d"});
        Tuple<Object> t2 = t.newTupleWithNames(Arrays.asList("do", "re", "mi", "fa"));

        // make sure it deep-copies
        t.set("a", null);

        assertEquals(new Tuple<>(
                new Object[]{12, 13, "1", "2"},
                new String[]{"do", "re", "mi", "fa"},
                true
        ), t2);
    }
}
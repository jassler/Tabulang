import de.hskempten.tabulang.datatypes.*;
import de.hskempten.tabulang.datatypes.exceptions.ArrayLengthMismatchException;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    public void testConstructors() {
    }

    @SafeVarargs
    private <T, U extends Styleable> ArrayList<U> createList(Function<T, U> constructor, T... values) {
        ArrayList<U> result = new ArrayList<>(values.length);
        for(var v : values) {
            result.add(constructor.apply(v));
        }

        return result;
    }

    @Test
    public void testThrowsArrayLengthMismatch() {
        assertThrows(ArrayLengthMismatchException.class, () -> new Tuple<>(InternalString.objToArray(""), new InternalString[]{}));
    }

    @Test
    public void testTupleDefaultValues() {
        Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray( "1", 2));
        assertEquals(Stream.of("1", 2).map(InternalDataObject::new).collect(Collectors.toList()), t.getElements());
        assertEquals(new HeaderNames(InternalString.objToList("0", "1")), t.getNames());
        assertTrue(t.isHorizontal());
    }

    @Test
    public void testTupleNameGeneration() {
        Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray(12, 13, "1", "2"));
        assertEquals(InternalString.objToList("0", "1", "2", "3"), t.getNames().getNames());

        t.getNames().getNames().set(0, new InternalString("4"));
        assertEquals(InternalString.objToList("4", "1", "2", "3"), t.getNames().getNames());
    }

    @Test
    public void testTupleAccess() {
        final Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray(12, 13, "1", "2"));
        assertEquals(12, t.get(new InternalString("0")).getObject());
        assertEquals(13, t.get(new InternalString("1")).getObject());
        assertEquals("1", t.get(new InternalString("2")).getObject());
        assertEquals("2", t.get(new InternalString("3")).getObject());
        assertThrows(TupleNameNotFoundException.class, () -> t.get(new InternalString("4")));
        assertThrows(TupleNameNotFoundException.class, () -> t.get(new InternalString("-1")));
        assertThrows(TupleNameNotFoundException.class, () -> t.get(new InternalString("a")));

        final Tuple<InternalNumber> u = new Tuple<>(createList(InternalNumber::new, 1, 4, 2), InternalString.objToList("alpha", "beta", "gamma"));

        assertEquals(1, u.get(new InternalString("alpha")).getValue());
        assertEquals(4, u.get(new InternalString("beta")).getValue());
        assertEquals(2, u.get(new InternalString("gamma")).getValue());
        assertEquals(1, u.get(new InternalString("0")).getValue());
        assertEquals(4, u.get(new InternalString("1")).getValue());
        assertEquals(2, u.get(new InternalString("2")).getValue());
    }

    @Test
    public void testTupleAppend() {
        Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray(12, 13));
        t.setHorizontal(false);

        Tuple<InternalDataObject> other = new Tuple<>(InternalDataObject.objToArray("a", "b"));
        other.getNames().getNames().set(0, new InternalString("c"));
        other.getNames().getNames().set(1, new InternalString("d"));

        t = t.concatenate(other);

        assertEquals(Stream.of(12, 13, "a", "b").map(InternalDataObject::new).collect(Collectors.toList()), t.getElements());
        assertEquals(new HeaderNames(InternalString.objToList("0", "1", "c", "d")), t.getNames());
        assertFalse(t.isHorizontal());
    }

    @Test
    public void testTupleProjection() {
        Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray(12, 13, "1", "2"));

        assertEquals(new Tuple<>(
                Collections.emptyList()
        ), t.projection());

        assertEquals(new Tuple<>(
                Collections.singletonList(new InternalDataObject("1")),
                Collections.singletonList(new InternalString("2"))
        ), t.projection(2));

        assertEquals(new Tuple<>(
                InternalDataObject.objToArray(13, "2", 12, "1"),
                InternalString.objToArray("1", "3", "0", "2")
        ), t.projection(1, 3, 0, 2));

        assertEquals(new Tuple<>(
                InternalDataObject.objToArray(13, "2", 12, "1"),
                InternalString.objToArray("1", "3", "0", "2")
        ), t.projection(InternalString.objToArray("1", "3", "0", "2")));

        assertEquals(new Tuple<>(
                InternalDataObject.objToArray(13, "2", 12, "1"),
                InternalString.objToArray("beta", "delta", "alpha", "gamma")
        ), t.newTupleWithNames(InternalString.objToList("alpha", "beta", "gamma", "delta")).projection(InternalString.objToArray("beta", "delta", "alpha", "gamma")));
    }

    @Test
    public void testTupleNewNames() {
        Tuple<InternalDataObject> t = new Tuple<>(InternalDataObject.objToArray(12, 13, "1", "2"), InternalString.objToArray("a", "b", "c", "d"));
        Tuple<InternalDataObject> t2 = t.newTupleWithNames(InternalString.objToList("do", "re", "mi", "fa"));

        // make sure it deep-copies
        t.set("a", null);

        assertEquals(new Tuple<>(
                InternalDataObject.objToArray(12, 13, "1", "2"),
                InternalString.objToArray("do", "re", "mi", "fa"),
                true
        ), t2);
    }

    @Test
    public void testToString() {
        assertEquals("", new Tuple<>(new InternalString[0]).toString());
        assertEquals("0\n4", new Tuple<>(new InternalNumber[]{new InternalNumber(4)}).toString());
        assertEquals("0 | 1\n4 | 1", new Tuple<>(Stream.of(4, 1).map(InternalNumber::new).collect(Collectors.toList())).toString());
        assertEquals("0 | 1 | 2\n4 | 1 | 4", new Tuple<>(Stream.of(4, 1, 4).map(InternalNumber::new).collect(Collectors.toList())).toString());

        Tuple<InternalString> t = new Tuple<>(InternalString.objToArray("Alpha", "Beta", "Gamma"));
        String expected = "" +
                "0     | 1    | 2\n" +
                "Alpha | Beta | Gamma";

        assertEquals(expected, t.toString());

        t.setHorizontal(false);
        expected = """
                0 | Alpha
                1 | Beta
                2 | Gamma""";

        assertEquals(expected, t.toString());

        Tuple<Tuple<InternalString>> t2 = new Tuple<Tuple<InternalString>>(new Tuple[]{
                new Tuple<>(InternalString.objToArray("a", "b")),
                new Tuple<>(InternalString.objToArray("x", "y")),
                new Tuple<>(InternalString.objToArray("o", "i"))
        });

        expected = "" +
                "0           | 1           | 2\n" +
                "0 | 1␤a | b | 0 | 1␤x | y | 0 | 1␤o | i";

        assertEquals(expected, t2.toString());
    }
}
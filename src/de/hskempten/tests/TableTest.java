package de.hskempten.tests;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.TableHeaderMismatchException;
import de.hskempten.tabulang.datatypes.Tuple;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void transpose() {
    }

    @Test
    void isTransposed() {
    }

    @Test
    void getColumnIndex() {
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}),
                new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
        );

        assertEquals(0, t.getColumnIndex("First name"));
        assertEquals(1, t.getColumnIndex("Last name"));
        assertEquals(2, t.getColumnIndex("Location"));
        assertThrows(NullPointerException.class, () -> t.getColumnIndex("Not in there"));
        assertThrows(NullPointerException.class, () -> t.getColumnIndex("first name"));
    }

    @Test
    void filter() {

        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}),
                new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
        );

        assertEquals(new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}), t.getRow(0));
        assertEquals(new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}, new String[]{"First name", "Last name", "Location"}), t.getRow(1));
        assertEquals(new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"}, new String[]{"First name", "Last name", "Location"}), t.getRow(2));

        Table<String> filtered = t.filter(tuple -> tuple.get(1).charAt(1) == 'e');

        assertEquals(
                new Table<>(
                        new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}, new String[]{"First name", "Last name", "Location"}),
                        new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
                ),
                filtered
        );
    }

    @Test
    void projection() {
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        var projected = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz"}, new String[]{"First name", "Last name"}),
                new Tuple<>(new String[]{"Jonas", "Lärch"}),
                new Tuple<>(new String[]{"Hanna", "Meher"}),
                new Tuple<>(new String[]{"Willi", "Wonky"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi"})
        );

        assertEquals(projected, t.projection(0, 1));
        assertEquals(projected, t.projection("First name", "Last name"));

        // check that no duplicate rows exist
        t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Berlin"})
        );

        projected = new Table<>(
                new Tuple<>(new String[]{"Madrid"}, new String[]{"Location"}),
                new Tuple<>(new String[]{"Kempten"}),
                new Tuple<>(new String[]{"Berlin"})
        );

        assertEquals(projected, t.projection(2));
        assertEquals(projected, t.projection("Location"));

        assertEquals(new Table<>(), t.projection());
    }

    @Test
    public void intersection() {
        Table<String> t1 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        Table<String> t2 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Nochw", "Ashie", "Rzuseh"})
        );

        Table<String> intersected = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        assertEquals(intersected, t1.intersection(t2));

        Table<String> t3 = new Table<>(new Tuple<>(new String[]{"Wrong", "Column", "Headers"}, new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.intersection(t3));
    }

    @Test
    public void union() {
        Table<String> t1 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        Table<String> t2 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Nochw", "Ashie", "Rzuseh"})
        );

        Table<String> unioned = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Nochw", "Ashie", "Rzuseh"})
        );

        assertEquals(unioned, t1.union(t2));

        Table<String> t3 = new Table<>(new Tuple<>(new String[]{"Wrong", "Column", "Headers"}, new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.union(t3));
    }

    @Test
    public void difference() {
        Table<String> t1 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        Table<String> t2 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Nochw", "Ashie", "Rzuseh"})
        );

        Table<String> differenced = new Table<>(
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"})
        );

        assertEquals(differenced, t1.difference(t2));

        Table<String> t3 = new Table<>(new Tuple<>(new String[]{"Wrong", "Column", "Headers"}, new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.difference(t3));
    }
}
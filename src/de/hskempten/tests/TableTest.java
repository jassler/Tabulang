package de.hskempten.tests;

import de.hskempten.tabulang.datatypes.Table;
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
    }
}
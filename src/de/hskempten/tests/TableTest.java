package de.hskempten.tests;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import org.junit.jupiter.api.Test;

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

        Table t = new Table(
                new String[]{"First Name", "Second Name", "Location"},
                new String[]{"One", "Two", "Three"},
                new Tuple(new Object[]{"Felix", "Fritz"}),
                new Tuple(new Object[]{"Tobias", "Teiher"}),
                new Tuple(new Object[]{"Manfred", "Meher"}),
                new Tuple(new Object[]{"Oberstdorf", "Kempten", "Berlin"}, new String[]{"Lo", "Ca", "Tion"}, false)
        );

        System.out.println(t);
    }
}
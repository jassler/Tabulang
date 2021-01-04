import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Style;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TableHeaderMismatchException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableTest {

    @Test
    void transpose() {
    }

    @Test
    void isTransposed() {
    }

    @Test
    void getColumnIndex() {
        Table<InternalString> t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Oberstdorf"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Tobias", "Teiher", "Kempten")),
                new Tuple<>(InternalString.objToArray("Manfred", "Meher", "Berlin"))
        );

        assertEquals(0, t.getColumnIndex("First name"));
        assertEquals(1, t.getColumnIndex("Last name"));
        assertEquals(2, t.getColumnIndex("Location"));
        assertThrows(NumberFormatException.class, () -> t.getColumnIndex("Not in there"));
        assertThrows(IndexOutOfBoundsException.class, () -> t.getColumnIndex("-1"));
    }

    @Test
    void filter() {

        Table<InternalString> t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Oberstdorf"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Tobias", "Teiher", "Kempten")),
                new Tuple<>(InternalString.objToArray("Manfred", "Meher", "Berlin"))
        );

        assertEquals(new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Oberstdorf"), new String[]{"First name", "Last name", "Location"}), t.getRow(0));
        assertEquals(new Tuple<>(InternalString.objToArray("Tobias", "Teiher", "Kempten"), new String[]{"First name", "Last name", "Location"}), t.getRow(1));
        assertEquals(new Tuple<>(InternalString.objToArray("Manfred", "Meher", "Berlin"), new String[]{"First name", "Last name", "Location"}), t.getRow(2));

        Table<InternalString> filtered = t.filter(tuple -> tuple.get("1").getString().charAt(1) == 'e');

        assertEquals(
                new Table<>(
                        new Tuple<>(InternalString.objToArray("Tobias", "Teiher", "Kempten"), new String[]{"First name", "Last name", "Location"}),
                        new Tuple<>(InternalString.objToArray("Manfred", "Meher", "Berlin"))
                ),
                filtered
        );
    }

    @Test
    void projection() {
        Table<InternalString> t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        var projected = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz"), new String[]{"First name", "Last name"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi"))
        );

        assertEquals(projected, t.projection(0, 1));
        assertEquals(projected, t.projection("First name", "Last name"));

        // check that no duplicate rows exist
        t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Berlin"))
        );

        projected = new Table<>(
                new Tuple<>(InternalString.objToArray("Madrid"), new String[]{"Location"}),
                new Tuple<>(InternalString.objToArray("Kempten")),
                new Tuple<>(InternalString.objToArray("Berlin"))
        );

        assertEquals(projected, t.projection(2));
        assertEquals(projected, t.projection("Location"));

        assertEquals(new Table<>(), t.projection());
    }

    @Test
    public void intersection() {
        Table<InternalString> t1 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        Table<InternalString> t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nochw", "Ashie", "Rzuseh"))
        );

        Table<InternalString> intersected = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        assertEquals(intersected, t1.intersection(t2));

        Table<InternalString> t3 = new Table<>(new Tuple<>(InternalString.objToArray("Wrong", "Column", "Headers"), new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.intersection(t3));
    }

    @Test
    public void union() {
        Table<InternalString> t1 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        Table<InternalString> t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nochw", "Ashie", "Rzuseh"))
        );

        Table<InternalString> unioned = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nochw", "Ashie", "Rzuseh"))
        );

        assertEquals(unioned, t1.union(t2));

        Table<InternalString> t3 = new Table<>(new Tuple<>(InternalString.objToArray("Wrong", "Column", "Headers"), new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.union(t3));
    }

    @Test
    public void difference() {
        Table<InternalString> t1 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        Table<InternalString> t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nochw", "Ashie", "Rzuseh"))
        );

        Table<InternalString> differenced = new Table<>(
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid"))
        );

        assertEquals(differenced, t1.difference(t2));

        Table<InternalString> t3 = new Table<>(new Tuple<>(InternalString.objToArray("Wrong", "Column", "Headers"), new String[]{"first name", "last name", "location"}));
        assertThrows(TableHeaderMismatchException.class, () -> t1.difference(t3));
    }

    @Test
    public void horizontalPairing() {
        Table<InternalString> t1 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz"), new String[]{"First name", "Last name"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi"))
        );

        Table<InternalString> t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Madrid"), new String[]{"Location"}),
                new Tuple<>(InternalString.objToArray("Berlin")),
                new Tuple<>(InternalString.objToArray("Madrid")),
                new Tuple<>(InternalString.objToArray("Rzuseh"))
        );

        Table<InternalString> paired = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Berlin")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Madrid")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Rzuseh")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", null))
        );

        assertEquals(paired, t1.horizontalPairing(t2));
    }

    @Test
    public void verticalPairing() {
        Table<InternalString> t1 = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid"))
        );

        Table<InternalString> t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Fegex", "Fritz", "Dadrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Haana", "Meher", "Berlqn")),
                new Tuple<>(InternalString.objToArray("Vasrb", "IerbA", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nocwa", "Ashsa", "Rzudsh"))
        );

        Table<InternalString> paired = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Fegex", "Fritz", "Dadrid")),
                new Tuple<>(InternalString.objToArray("Haana", "Meher", "Berlqn")),
                new Tuple<>(InternalString.objToArray("Vasrb", "IerbA", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nocwa", "Ashsa", "Rzudsh"))
        );

        assertEquals(paired, t1.verticalPairing(t2));

        t2 = new Table<>(
                new Tuple<>(InternalString.objToArray("Fegex", "Fritz", "Dadrid", "Oh what"), new String[]{"First name", "Last name", "Location", "Unexpected"}),
                new Tuple<>(InternalString.objToArray("Haana", "Meher", "Berlqn", "What is")),
                new Tuple<>(InternalString.objToArray("HaAsa", "Tahar", "DSslqn", "This"))
        );

        paired = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid", null), new String[]{"First name", "Last name", "Location", "Unexpected"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten", null)),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin", null)),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid", null)),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid", null)),
                new Tuple<>(InternalString.objToArray("Fegex", "Fritz", "Dadrid", "Oh what")),
                new Tuple<>(InternalString.objToArray("Haana", "Meher", "Berlqn", "What is")),
                new Tuple<>(InternalString.objToArray("HaAsa", "Tahar", "DSslqn", "This"))
        );

        assertEquals(paired, t1.verticalPairing(t2));

        paired = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Fegex", null, null)),
                new Tuple<>(InternalString.objToArray("Haana", null, null)),
                new Tuple<>(InternalString.objToArray("HaAsa", null, null))
        );

        assertEquals(paired, t1.verticalPairing(t2.projection(0)));
    }

    @Test
    public void styling() {
        Table<InternalString> t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Madrid"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Jonas", "Lärch", "Kempten")),
                new Tuple<>(InternalString.objToArray("Hanna", "Meher", "Berlin")),
                new Tuple<>(InternalString.objToArray("Willi", "Wonky", "Madrid")),
                new Tuple<>(InternalString.objToArray("Bierb", "Ierbi", "Madrid")),
                new Tuple<>(InternalString.objToArray("Fegex", "Fritz", "Dadrid")),
                new Tuple<>(InternalString.objToArray("Haana", "Meher", "Berlqn")),
                new Tuple<>(InternalString.objToArray("Vasrb", "IerbA", "Madrid")),
                new Tuple<>(InternalString.objToArray("Nocwa", "Ashsa", "Rzudsh"))
        );

        t.getRow(0).setStyle(new Style().setFont("Times"));
        t.getRow(2).setStyle(new Style().setFont("Monaco"));

        t.setRowHeight(3, 16.7);

        t.getRow(2).getFromIndex(2).setStyle(new Style().setUnderlined(true).setBold(true));
        t.getRow(0).getFromIndex(0).setStyle(new Style().setItalics(true));

        Style s = new Style().setItalics(true);
        assertEquals(s, t.getRow(0).iterator().next().getStyle());

        s.reset().setUnderlined(true).setBold(true);
        assertEquals(s, t.getRow(2).get("Location").getStyle());

        s.reset().setFont("Monaco");
        assertEquals(s, t.getRow(2).get("First name").computeStyle());

        s.reset().setFont("Times");
        assertEquals(s, t.getRow(0).get("Location").computeStyle());
    }

    @Test
    public void tableToString() {
        var t = new Table<>(
                new Tuple<>(InternalString.objToArray("Andreas", "Bittner", "Madrid", "24"), new String[]{"First name", "Last name", "Location", "Age"}),
                new Tuple<>(InternalString.objToArray("Kilian", "Manfred-Anderson", "Sao Paulo", "8"))
        );

        String expected = "" +
                "First name | Last name        | Location  | Age\n" +
                "-----------------------------------------------\n" +
                "Andreas    | Bittner          | Madrid    | 24\n" +
                "Kilian     | Manfred-Anderson | Sao Paulo | 8";
        assertEquals(expected, t.toString());

        t.transpose();

        expected = "" +
                "First name | Andreas Kilian\n" +
                "Last name  | Bittner Manfred-Anderson\n" +
                "Location   | Madrid  Sao Paulo\n" +
                "Age        | 24      8";
        assertEquals(expected, t.toString());
    }
}
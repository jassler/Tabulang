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
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}),
                new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
        );

        assertEquals(0, t.getColumnIndex("First name"));
        assertEquals(1, t.getColumnIndex("Last name"));
        assertEquals(2, t.getColumnIndex("Location"));
        assertThrows(NumberFormatException.class, () -> t.getColumnIndex("Not in there"));
        assertThrows(IndexOutOfBoundsException.class, () -> t.getColumnIndex("-1"));
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

    @Test
    public void horizontalPairing() {
        Table<String> t1 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz"}, new String[]{"First name", "Last name"}),
                new Tuple<>(new String[]{"Jonas", "Lärch"}),
                new Tuple<>(new String[]{"Hanna", "Meher"}),
                new Tuple<>(new String[]{"Willi", "Wonky"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi"})
        );

        Table<String> t2 = new Table<>(
                new Tuple<>(new String[]{"Madrid"}, new String[]{"Location"}),
                new Tuple<>(new String[]{"Berlin"}),
                new Tuple<>(new String[]{"Madrid"}),
                new Tuple<>(new String[]{"Rzuseh"})
        );

        Table<String> paired = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Berlin"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Madrid"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Rzuseh"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", null})
        );

        assertEquals(paired, t1.horizontalPairing(t2));
    }

    @Test
    public void verticalPairing() {
        Table<String> t1 = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"})
        );

        Table<String> t2 = new Table<>(
                new Tuple<>(new String[]{"Fegex", "Fritz", "Dadrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Haana", "Meher", "Berlqn"}),
                new Tuple<>(new String[]{"Vasrb", "IerbA", "Madrid"}),
                new Tuple<>(new String[]{"Nocwa", "Ashsa", "Rzudsh"})
        );

        Table<String> paired = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Fegex", "Fritz", "Dadrid"}),
                new Tuple<>(new String[]{"Haana", "Meher", "Berlqn"}),
                new Tuple<>(new String[]{"Vasrb", "IerbA", "Madrid"}),
                new Tuple<>(new String[]{"Nocwa", "Ashsa", "Rzudsh"})
        );

        assertEquals(paired, t1.verticalPairing(t2));

        t2 = new Table<>(
                new Tuple<>(new String[]{"Fegex", "Fritz", "Dadrid", "Oh what"}, new String[]{"First name", "Last name", "Location", "Unexpected"}),
                new Tuple<>(new String[]{"Haana", "Meher", "Berlqn", "What is"}),
                new Tuple<>(new String[]{"HaAsa", "Tahar", "DSslqn", "This"})
        );

        paired = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid", null}, new String[]{"First name", "Last name", "Location", "Unexpected"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten", null}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin", null}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid", null}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid", null}),
                new Tuple<>(new String[]{"Fegex", "Fritz", "Dadrid", "Oh what"}),
                new Tuple<>(new String[]{"Haana", "Meher", "Berlqn", "What is"}),
                new Tuple<>(new String[]{"HaAsa", "Tahar", "DSslqn", "This"})
        );

        assertEquals(paired, t1.verticalPairing(t2));

        paired = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Fegex", null, null}),
                new Tuple<>(new String[]{"Haana", null, null}),
                new Tuple<>(new String[]{"HaAsa", null, null})
        );

        assertEquals(paired, t1.verticalPairing(t2.projection(0)));
    }

    @Test
    public void styling() {
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Madrid"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Jonas", "Lärch", "Kempten"}),
                new Tuple<>(new String[]{"Hanna", "Meher", "Berlin"}),
                new Tuple<>(new String[]{"Willi", "Wonky", "Madrid"}),
                new Tuple<>(new String[]{"Bierb", "Ierbi", "Madrid"}),
                new Tuple<>(new String[]{"Fegex", "Fritz", "Dadrid"}),
                new Tuple<>(new String[]{"Haana", "Meher", "Berlqn"}),
                new Tuple<>(new String[]{"Vasrb", "IerbA", "Madrid"}),
                new Tuple<>(new String[]{"Nocwa", "Ashsa", "Rzudsh"})
        );

        t.setColumnStyle(1, new Style().setColor("#123456").setFont("Arial").setAttribute("colwidth", "12"));
        t.setRowStyle(0, new Style().setFont("Times"));
        t.setRowStyle(2, new Style().setFont("Monaco"));

        t.setRowHeight(3, 16.7);
        t.setColumnWidth(1, 123);

        t.setCellStyle(2, 2, new Style().setUnderlined(true).setBold(true));
        t.setCellStyle(0, 0, new Style().setItalics(true));

        Style s = new Style().setItalics(true);
        assertEquals(s, t.getRow(0).iterator().next().getStyle());

        s.reset().setUnderlined(true).setBold(true);
        assertEquals(s, t.getRow(2).getDataCell("Location").getStyle());

        s.reset().setFont("Monaco");
        assertEquals(s, t.getRow(2).getDataCell("First name").getStyle());

        s.reset().setFont("Times");
        assertEquals(s, t.getRow(0).getDataCell("Location").getStyle());

        s.reset().setColor("#123456").setFont("Arial").setAttribute(Style.COLUMN_WIDTH, Double.toString(123));
        assertEquals(s, t.getRow(0).getDataCell("Last name").getStyle());
    }

    @Test
    public void tableToString() {
        var t = new Table<>(
                new Tuple<>(new String[]{"Andreas", "Bittner", "Madrid", "24"}, new String[]{"First name", "Last name", "Location", "Age"}),
                new Tuple<>(new String[]{"Kilian", "Manfred-Anderson", "Sao Paulo", "8"})
        );

        String expected =
                "First name | Last name        | Location  | Age\n" +
                        "-----------------------------------------------\n" +
                        "Andreas    | Bittner          | Madrid    | 24 \n" +
                        "Kilian     | Manfred-Anderson | Sao Paulo | 8  ";
        assertEquals(expected, t.toString());

        t.transpose();

        expected =
                "First name | Andreas Kilian          \n" +
                        "Last name  | Bittner Manfred-Anderson\n" +
                        "Location   | Madrid  Sao Paulo       \n" +
                        "Age        | 24      8               ";
        assertEquals(expected, t.toString());
    }
}
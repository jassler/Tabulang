package de.hskempten.tabulang;

import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();

        for (var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");

        //l.setText("a := 9; b := 3; result := a + b * 3 - 20;");
        //l.setText("function xyz(asdf,jkl){return asdf;}a := 9; b := 3; result := a + b * 3 - 20;");
        /*l.setText("function kopfZahlNachAbschluss(fach, semester)\n" +
                "return horizontal for abschl in AbschlussListe{\n" +
                "set kopfzahlEingeschriebene(fach, abschl, semester, semester);\n" +
                "//mark background 'red' if modularisiert(fach, abschl);\n" + // keyword background not yet implemented
                "//mark background 'green' if not modularisiert(fach, abschl);\n" +
                "group before 'Gesamt' using sum(mapValue);\n" +
                "};");*/
        //l.setText("");
        l.setText("AbschlussListe := distinct abschlName from T1;\n" +
                "RelevanteAbschluesse := ('95', '82', 'B1', 'B2', '68', 'BJ', '11', '21', '22',\n" +
                "'23', '24', '25', '26', 'L1', 'L2', 'L3', 'L4', 'L5', 'L6');\n" +
                "FachListe := distinct stgName from T1;\n" +
                "aktSem := 20112;\n" +
                "tabKorpus := aside(fachUeberschriften(), kopfZahlenTabelle(aktSem));\n" +
                "UebersichtUeberStudiengaenge :=\n" +
                "onTop( 'Übersicht über Studiengänge mit Studierenden',\n" +
                "aside(' ', 'Gesamt', AbschlussListe),\n" +
                "aside(' ', kopfZahlNachAbschlussAlleFaecher(aktSem)),\n" +
                "' ',\n" +
                "tabKorpus );\n" +
                "function fachUeberschriften()\n" +
                "return vertical for fach in FachListe {\n" +
                "set fach;\n" +
                "group before getFakultaet(fach) mark 'background' as 'grey';\n" +
                "};\n" +
                "function kopfZahlenTabelle(semester)\n" +
                "return vertical for fach in FachListe {\n" +
                "set kopfZahlNachAbschluss(fach, semester);\n" +
                "group before getFakultaet(fach) using sum(mapValue) " /*+ "mark 'background' as 'grey'"*/ + ";\n" + //TODO no termR after funCall
                "};\n" +
                "function kopfZahlNachAbschlussAlleFaecher(semester)\n" +
                "return horizontal for abschl in AbschlussListe{\n" +
                "set kopfzahlEingeschriebeneAlleFaecher(abschl, semester, semester);\n" +
                "group before 'Gesamt' using sum(mapValue);\n" +
                "};\n" +
                "function kopfZahlNachAbschluss(fach, semester)\n" +
                "return horizontal for abschl in AbschlussListe{\n" +
                "set kopfzahlEingeschriebene(fach, abschl, semester, semester);\n" +
                "mark 'background' as 'red' if modularisiert(fach, abschl);\n" +
                "mark 'background' as 'green' if not modularisiert(fach, abschl);\n" +
                "group before 'Gesamt' using sum(mapValue);\n" +
                "};");
        Interpreter i = new Interpreter();

        TabulangParser parser = new TabulangParser(l, i);
        //parser.parse();
        l.reset();
        ProgramItem prg = parser.parseN();
        System.out.println("Number of statements: " + prg.getMyStatements().size());
        /*
        while(!l.isDone()) {
            // assuming that everything must be an Assignment
            // later on, we can change it to Statement or Function or something
            var a = new Assignment(l);
            i.evaluate(a);
        }
         */

        System.out.println("Evaluated \"" + l.getText() + "\" and got:\n");
        System.out.println(i);
    }

}

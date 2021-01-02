package de.hskempten.tabulang;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;

import java.util.Iterator;
import java.util.Map;

public class Tabulang {

    public static void main(String[] args) throws Exception {
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
        if (false) {
            l.setText("AbschlussListe := distinct abschlName from T1;\n" +
                    "RelevanteAbschluesse := ['95', '82', 'B1', 'B2', '68', 'BJ', '11', '21', '22',\n" +
                    "'23', '24', '25', '26', 'L1', 'L2', 'L3', 'L4', 'L5', 'L6'];\n" +
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
                    //"group before getFakultaet(fach) using sum(mapValue) " + "mark 'background' as 'grey'" + ";\n" + //TODO no termR after funCall at page 4 function kopfZahlenTabelle
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
        }
        if (false) {
            l.setText("// Beispiel 2\n" +
                    "function buildStatisticsRow(fach, abschlList, minSem, maxSem) {\n" +
                    "abs := absolventen(fach, abschlList, minSem, maxSem);\n" +
                    "return aside(count eingeschrieben(fach, abschlList, minSem, maxSem),\n" +
                    "count anfaenger(fach, abschlList, minSem, maxSem),\n" +
                    "count wechslerNach(fach, abschlList, minSem, maxSem),\n" +
                    "count wechslerVon(fach, abschlList, minSem, maxSem),\n" +
                    "count abs,\n" +
                    "average stgsem abschlussSemester(abs),\n" +
                    "average pnote abschlussNote(abs));\n" +
                    "}\n" +
                    "function buildStatisticsTable(fach, minSem, maxSem) {\n" +
                    "aListe := RelevanteAbschluesse filter abschlussExistiertFuerFach(fach);\n" +
                    "aggregatedRow := buildStatisticsRow(fach, aListe, minSem, maxSem)\n" +
                    "mark 'fontstyle' as 'fat';\n" +
                    //"detailed := vertical for abschluss in aListe\n" +
                    //"buildStatisticsRow(fach, abschluss, minSem, maxSem);\n" +   // TODO no funCall in loopStmnt at page 8 function buildStatisticsTable
                    "restRow := buildStatisticsRow(fach, AbschlussListe - RelevanteAbschluesse,\n" +
                    "minSem, maxSem) mark fontstyle as 'italic';\n" +
                    "tab := ontop(aggregatedRow, detailed);\n" +
                    "if (restRow.0 != 0) tab := ontop(tab, restRow);\n" +
                    "return tab;\n" +
                    "}\n" +
                    "function buildHeaderLinks(fachbereich)\n" +
                    "return ontop('Fächer/Abschlusse', ' ', vertical for fach in faecher{\n" +
                    "bereich:= ontop(fach mark fontstyle as 'fat',\n" +
                    "vertical for abschl in AbschlussListe filter\n" +
                    "abschlussExistiertFuerFach(fach, abschl) set abschl;);\n" +
                    "c := count T1 filter abschl in (AbschlussListe- RelevanteAbschluesse);\n" +
                    "if (c > 0) bereich := ontop(bereich, 'Restliche' mark fontstyle as 'italic');\n" +
                    //"set horizontalflip bereich;\n" + //TODO lexer doesn't recognize some keywords as keywords at page 8
                    "set bereich;\n" +
                    "});\n" +
                    "function buildTabelle(fachbereich) {\n" +
                    "jahre:= [[20071, 20112], [20111, 20112], [20101, 20102],\n" +
                    "[20091, 20092], [20081, 20082], [20071, 20072]];\n" +
                    "jahresUeberschriften := ['Gesamt(2007-2011)', 2011, 2010, 2009, 2008, 2007];\n" +
                    "faecher := Fachliste filter fachInFachbereich(fachbereich);\n" +
                    "x := 1; // Benutzen x um die grüne Hintergrundmarkierung zu erzeugen\n" +
                    "alleJahreTab:= horizontal for jahr in jahre {\n" +
                    //"tab := vertical for fach in faecher buildStatisticsTable(fach, jahr.0, jahr.1);\n" +  // TODO no funCall in loopStmnt at page 8 function buildTabelle
                    "tab := tab mark 'background' as 'green'if (x mod 2 = 0);\n" +
                    "x := x + 1;\n" +
                    "set ontop(['Alle', 'Anf', 'W+', 'W-', 'Abs', 'FS', 'Note'], tab);\n" +
                    "};\n" +
                    "return aside(buildHaderLinks(fachbereich),\n" +
                    "ontop(jahresUeberschriften, alleJahreTab));\n" +
                    "}" +
                    "");
        }
        if (false) {
            l.setText("\n" +
                    "v3 := '3';\n" +
                    "v4 := f2(4);\n" +
                    "v5 := 5;\n" +
                    "function f2(x) f1(x);\n" +
                    "function f1(x) return x;\n" +
                    "v6 := f1('6');\n" +
                    "\n" +
                    "");
        }

        if (true) {
            l.setText("\n" +
                    //"z := [[1], [1, '3.00', 5.0, 6*8, 'b' + 6]];\n" +
                    //"y := [[1, 2],[2, 3],[3, 4]];\n" +
                    "y := [6, 7, 8, 7];\n" +
                    //"a := 4;\n" +
                    //"b := 6;\n" +
                    "c := 0;\n" +
                    "d := 6.0;\n" +
                    //"f := d mod c;\n" +
                    //"g := y.'1'.'1';\n" +
                    //"if(d in y) c := 2;\n" +
                    //"spr := [1...3];\n" +
                   // "var a := 'hi' + 'hi';\n" +
                    //"if(5 < 4 iff 6 > 7) d := 10;\n" +
                    "function f1(x, z) return 5;\n" +
                    //"res := f1(d);\n" +
                    //"for x in y {set x; c := x + c;}\n" +
                    //"for x in y {set x; d := 'Informatik'; if(x = 7) d := 'Mathematik'; c := x; e := c - 1; l :=  e - 1;};\n" +
                   // "function hi(a, b) {c := 1; return a + b;}\n" +
                    //"S := for x in y {set x; c := c + 1;};\n" +
                   // "e := hi(c, d);" +
                    "\n");
        }
        Interpretation interpretation = new Interpretation();

        TabulangParser parser = new TabulangParser(l, interpretation);
        l.reset();
        ProgramItem prg = parser.parseN();
        System.out.println("NumberAST of statements: " + prg.getMyStatements().size());
        //System.out.println("Evaluating \"" + l.getText());
        ProgramAST prgAST = ASTProgramParser.instance.parse(prg);

        prgAST.executeProgram(interpretation);

        //System.out.println("Evaluated \"" + l.getText() + "\" and got:");
        //System.out.println(".......................");
        System.out.println();
        System.out.println("Environment nach Programmende: ");
        Iterator it = interpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}

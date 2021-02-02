package de.hskempten.tabulang;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.standardLibrary.StandardLibrary;
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
                    "tuple := [[1, 2]," +
                              "[2, 3]," +
                              "[3, 11]];\n" +
                    "b := 1;\n" +
                   "if(true) c := 1;\n" +
                    //"multTest := '1' * 6;\n" +
                    //"modTest := '1' mod 6;\n" +
                    //"c := average b tuple;\n" +
                    //"z := [[1], [7, '3.00', 5.0, 6*8, 'b' + 6]];\n" +
                    //"z := [[1, 2],[2, 3],[3, 6]];\n" +
                    //"tableSub := y - z;\n" +
                    "y := [6, 7, 8, 7];\n" +
                    //"a := 6 + y;\n" +
                    //"s := (c) -> {c := 2; return c;};\n" +
                    //"d := y.'0'.'2';\n" +
                    //"var f := 6 * 'y';\n" +
                    //"g := tuple.'1'.'1';\n" +
                    //"testest := if(true) return 0; return 5;\n" +
                    //"spr := [1...3];\n" +
                   // "var a := 'hi' + 'hi';\n" +
                    //"if(5 < 4 iff 6 > 7) d := 10;\n" +
                    "function f(x) { if( x <= 0 ) return 0; return 0.1 + f(x - 0.1); }\n" +
                    //"res := f1(d);\n" +
                    //"for x in y {set x; c := x + 1; mark '0' as 'grey' if x > 6;}\n" +
                    //"for x in y {set x; d := 'Informatik'; if(x = 7) d := 'Mathematik'; c := x; e := c - 1; l :=  e - 1; hiding group after d using f1(c, e) mark 'background' as 'blue';}\n" +
                   // "function hi(a, b) {c := 1; return a + b;}\n" +
                    //"S := for x in y {set x; c := c + 1;};\n" +
                    //"e := f(5);" +
                    "\n");
        }

        if (false) {
            l.setText("\n" +

                    "openDbConnection('85.214.33.119', 3306, 'sakila', 'db_user', 'HsKemptenProjekt2020');\n" +
                    //"tActor := databaseToTable('select * from abc');\n" +
                    "actorsInZMovie := databaseToTable('SELECT a.first_name, a.last_name, f.title FROM sakila.actor as a JOIN sakila.film_actor as fa ON a.actor_id = fa.actor_id JOIN sakila.film as f ON f.film_id = fa.film_id WHERE f.title LIKE \"Z%\";' );\n" +
                    "actorsInYMovie := databaseToTable('SELECT a.first_name, a.last_name, f.title FROM sakila.actor as a JOIN sakila.film_actor as fa ON a.actor_id = fa.actor_id JOIN sakila.film as f ON f.film_id = fa.film_id WHERE f.title LIKE \"Y%\";' );\n" +

                    //"tTitle := databaseToTable(queryTitle);\n" +
                    //"allenPlayedIn := tActor filter last_name = 'ALLEN';\n" +
                   // "allenMovieAmount := count vertical allenPlayedIn;\n" +
                   // "allenTransformedHorizontal := horizontal allenPlayedIn;\n" +
                    "function findActorByLastName(x, y) return x filter last_name = y;\n" +
                    "function findActorsInZorroArk(x) {names := x filter title = 'ZORRO ARK'; return names.['first_name', 'last_name'];}\n" +
                    "function uniteTables(x, y) return x unite y;\n" +
                    "function iCount(x) return count vertical x;\n" +
                    "function createTable(anzahlZeilen, anzahlSpalten) {" +
                    "   return for z in [0...anzahlZeilen - 1] {" +
                            "set [z * anzahlSpalten...(z + 1) * anzahlSpalten - 1];};}\n" +
                    //"function rating(x) {if(x = 'YOUTH KICK') return 'Super'; if(x = 'YOUNG LANGUAGE') return 'Okay'; else return 'Nicht gesehen';}\n" +
                    "function f3(y) { print(y + 1); print(y * 2); print(y / 2); print(y - 1); }\n" +
                    "function getTitles(y) for x in y {set x.'title';};\n" +


                    "res := createTable(4,3);\n" +
                    "zorroArkActors := findActorsInZorroArk(actorsInZMovie);\n" +
                    //"unitedTables := uniteTables(actorsInJMovie, actorsInYMovie);\n" +
                    //"actorsInYMovieTransposed := horizontal actorsInYMovie ;\n" +
                    //"countedZ := iCount(actorsInZMovie) ;\n" +
                    //"countedY := iCount(actorsInYMovie) ;\n" +
                    "distinctTitle := 'title';\n" +
                    "distinctTable := distinct distinctTitle from actorsInZMovie;\n" +

                    //"S := getTitles(actorsInYMovie);\n" +


                    "\n");
        }
        Interpretation interpretation = new Interpretation();
        StandardLibrary.addStandardLibrary(interpretation);
        TabulangParser parser = new TabulangParser(l, interpretation);
        l.reset();
        ProgramItem prg = parser.parse();
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

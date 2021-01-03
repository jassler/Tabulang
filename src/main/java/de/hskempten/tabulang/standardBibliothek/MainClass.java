package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    public static void main(String[] args){
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}),
                new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
        );
        var fInterface = new FunctionInterface(new ToLowerCase(), ArrayList.class);
        System.out.println(fInterface.execute(t.getColNames().getNames()));
    }



    public static void addStandardLibrary(Interpretation interpreter) {

        interpreter.getEnvironment().put("print", new InternalLibraryFunction(new ArrayList<>(Arrays.asList(new IdentifierNode("x"))), new FunctionInterface(new print(), Object.class)));
        // usw usw

    }


}

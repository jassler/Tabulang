package de.hskempten.tabulang.exampleTestInterpretation;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BigInteger number = new BigInteger("5");
        String ident = "a";
        TermRItem termR = new TermRItem();
        NumberItem ni = new NumberItem(number);
        OrdinalItem oi = new OrdinalItem(ni);
        TermItem ti = new TermItem(termR, oi);
        IdentifierItem ii = new IdentifierItem(ident);
        VarDefItem vdi = new VarDefItem(ii, ti);
        StatementItem si = new StatementItem(vdi);
        ArrayList<StatementItem> asi = new ArrayList<StatementItem>();
        asi.add(si);
        ProgramItem pi = new ProgramItem(asi);

        Interpretation i = new Interpretation();

        pi.eval(i);

    }
}

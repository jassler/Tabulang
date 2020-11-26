package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.FunCallItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.TupelItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class FunCallType implements LanguageType {

    public static FunCallType instance = new FunCallType();

    @Override
    public FunCallItem parse(Lexer l) throws ParseTimeException {
        FunCallItem item;

        /*
        TermItem myTerm;
        TupelItem myTupel;

        myTerm = TermType.instance.parse(l);
        myTupel = TupelType.instance.parse(l);

        item = new FunCallItem(myTerm, myTupel);
        return item;
        */

        IdentifierItem myIdentifier;
        TupelItem myTupel;

        myIdentifier = IdentifierType.instance.parse(l);
        myTupel = TupelType.instance.parse(l);

        item = new FunCallItem(myIdentifier, myTupel);
        return item;
    }
}

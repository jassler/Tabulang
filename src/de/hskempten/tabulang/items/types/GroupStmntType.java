package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.FunCallItem;
import de.hskempten.tabulang.items.GroupAreaItem;
import de.hskempten.tabulang.items.GroupStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class GroupStmntType implements LanguageType {

    public static GroupStmntType instance = new GroupStmntType();

    @Override
    public GroupStmntItem parse(Lexer l) throws ParseTimeException {
        GroupStmntItem item;

        String myString;
        GroupAreaItem myGroupAream;
        TermItem myTerm;
        FunCallItem myFunCall;


        //return item;
        throw new ParseTimeException(l, "Not yet implemented");
    }
}

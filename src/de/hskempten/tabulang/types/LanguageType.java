package de.hskempten.tabulang.types;

import de.hskempten.tabulang.items.LanguageItem;
import de.hskempten.tabulang.tokenizer.Lexer;

import java.util.Scanner;

public interface LanguageType {

    public LanguageItem parse(Lexer l);
}

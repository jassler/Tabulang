package de.hskempten.tabulang.types;

import de.hskempten.tabulang.items.LanguageItem;

import java.util.Scanner;

public interface LanguageType {

    public LanguageItem parse(Scanner scan);
}

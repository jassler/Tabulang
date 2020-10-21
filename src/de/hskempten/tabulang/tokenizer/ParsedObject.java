package de.hskempten.tabulang.tokenizer;

/**
 * An interface for text ranges given by two row/col coordinates
 */
public interface ParsedObject {

    int getFromLine();

    int getToLine();

    int getFromCol();

    int getToCol();

}

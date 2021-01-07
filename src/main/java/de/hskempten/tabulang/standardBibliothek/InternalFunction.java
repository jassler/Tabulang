package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.mySql.Exceptions.MySqlConnectionException;

public interface InternalFunction {
    //public boolean hasExpectedObjectType(Object... args);
    public Object compute(Object... args) throws MySqlConnectionException;
}

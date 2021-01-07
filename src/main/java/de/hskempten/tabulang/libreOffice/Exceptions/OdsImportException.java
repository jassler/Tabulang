package de.hskempten.tabulang.libreOffice.Exceptions;

import java.net.ConnectException;

public class OdsImportException extends RuntimeException {
    public OdsImportException() {}

    public OdsImportException(String message)
    {
        super(message);
    }
}

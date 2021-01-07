package de.hskempten.tabulang.libreOffice.Exceptions;

import java.net.ConnectException;

public class OdsExportException extends RuntimeException {
    public OdsExportException() {}

    public OdsExportException(String message)
    {
        super(message);
    }
}

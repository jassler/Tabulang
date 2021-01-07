package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.libreOffice.OdsImportService;

/**
 * *.ods file -> {@link de.hskempten.tabulang.datatypes.Table} object
 */
public class OdsToTable implements InternalFunction{

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);

        if(!(args[0] instanceof InternalString filename))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        var odsImportService = new OdsImportService();
        return odsImportService.ImportFile(filename.getString());
    }
}

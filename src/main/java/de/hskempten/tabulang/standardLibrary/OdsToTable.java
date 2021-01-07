package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.libreOffice.OdsImportService;

public class OdsToTable implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(1, args)){
            var odsImportService = new OdsImportService();
            return odsImportService.ImportFile(((InternalString) args[0]).getString());
        }
        return null;
    }
}

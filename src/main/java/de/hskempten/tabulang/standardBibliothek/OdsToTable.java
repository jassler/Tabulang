package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.libreOffice.OdsImportService;

public class OdsToTable implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(1, args)){
            var odsImportService = new OdsImportService();
            return odsImportService.Import((String) args[0]);
        }
        return null;
    }
}

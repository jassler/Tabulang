package de.hskempten.tabulang.libreOffice;

import com.github.jferard.fastods.OdsFactory;
import com.github.jferard.fastods.AnonymousOdsFileWriter;
import com.github.jferard.fastods.OdsDocument;
import com.github.jferard.fastods.Table;

import de.hskempten.tabulang.mySql.Models.SqlExportWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

public class CalcConnection {
    private String _instanceName;
    private String _tableName;
    private String _path;
    private String _fileName;
    private OdsFactory _odsFactory;
    private AnonymousOdsFileWriter _writer;
    private OdsDocument _document;
    private Table _table;

    public CalcConnection(String instanceName, String tableName, String path, String fileName) {
        _instanceName = instanceName;
        _tableName = tableName;
        _path = path;
        _fileName = fileName;
        CreateCalcConnection();
    }

    private void CreateCalcConnection(){
        _odsFactory = OdsFactory.create(Logger.getLogger(this._instanceName), Locale.GERMANY);
        _writer = _odsFactory.createWriter();
        _document = _writer.document();
    }

    public void CreateCalcFile(SqlExportWrapper sqlExportWrapper){
        try {
            _table = _document.addTable(this._tableName);
            CreateTableHeadline(sqlExportWrapper.getHeadlines());
            CreateTableContent(sqlExportWrapper.getContent());
            SaveCalcFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void CreateTableHeadline(ArrayList<String> headlines) {
        try {
            for(var i = 0; i < headlines.size(); i++){
                var row = _table.getRow(0);
                var cell = row.getOrCreateCell(i);
                cell.setStringValue(headlines.get(i));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void CreateTableContent(ArrayList<ArrayList<String>> content){
        try {
            for(var i = 1; i < content.size(); i++){
                var row = _table.getRow(i);
                for(var j = 0; j < content.get(i).size(); j++){
                    var cell = row.getOrCreateCell(j);
                    cell.setStringValue(content.get(i).get(j));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void SaveCalcFile(){
        try {
            _writer.saveAs(new File(this._path, this._fileName + ".ods"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

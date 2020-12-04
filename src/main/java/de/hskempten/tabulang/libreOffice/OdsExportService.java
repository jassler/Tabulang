package de.hskempten.tabulang.libreOffice;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.libreOffice.Models.Style;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.*;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class OdsExportService {
    /* PROPERTIES */
    private SpreadsheetDocument _spreadsheetDocument;
    private OdfOfficeAutomaticStyles _odfOfficeAutomaticStyles;
    private Table _initTable;
    private ArrayList<Style> _styles;

    /* PUBLIC METHODS */
    public OdsExportService(String tableName){
        try {
            _spreadsheetDocument = SpreadsheetDocument.newSpreadsheetDocument();
            _odfOfficeAutomaticStyles = _spreadsheetDocument.getContentDom().getOrCreateAutomaticStyles();
            _initTable = _spreadsheetDocument.getSheetByIndex(0);
            _initTable.setTableName(tableName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Export(de.hskempten.tabulang.datatypes.Table table, String path, String fileName){
        var content = new ArrayList<ArrayList<String>>();
        for(var item : table){
            var contentRows = new ArrayList<String>();
            for(var cell : (Tuple<String>) item){
                contentRows.add(cell.getData());
            }
            content.add(contentRows);
        }
        AddHeadlines(table.getColNames().getNames());
        AddContentFromTable(content);
        SetRowStylesFromTable(table.getRowStyles());
        SetColumnStylesFromTable(table.getColumnStyles());
        SetCellStylesFromTable(table.getCellStyles());
        SaveFile(path, fileName);
    }

    public void AddContentFromTable(ArrayList<ArrayList<String>> content){
        for(var i = 0; i < content.size(); i++){
            for(var j = 0; j < content.get(i).size(); j++){
                _initTable.getCellByPosition(j, i + 1).setStringValue(String.valueOf(content.get(i).get(j)));
            }
        }
    }

    public void InstantlyExportToFile(MSqlTableContent sqlTableContent, String path, String fileName){
        AddHeadlines(sqlTableContent.get_headlines());
        AddContent(sqlTableContent.get_content());
        SaveFile(path, fileName);
    }

    public void AddHeadlines(ArrayList<String> headlines) {
        try {
            var length = headlines.size();
            for(var i = 0; i < length; i++){
                _initTable.getCellByPosition(i, 0).setStringValue(headlines.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddContent(ArrayList<ArrayList<String>> content){
        for(var i = 0; i < content.size(); i++){
            for(var j = 0; j < content.get(i).size(); j++){
                _initTable.getCellByPosition(i, j + 1).setStringValue(String.valueOf(content.get(i).get(j)));
            }
        }
    }

    public void CreateTable(String tableName){
        _initTable = _spreadsheetDocument.addTable();
        _initTable.setTableName(tableName);
    }

    public void CreateStyle(){
        this._styles = new ArrayList<>();
    }
    
    public void SetStyleToRow(int rowIndex){
        var row = _initTable.getRowByIndex(rowIndex);
        for(int i = 0; i < row.getCellCount(); i++){
            SetStyleToCell(row.getRowIndex(), i);
        }
    }

    public void SetStyleToColumn(int columnIndex){
        var column = _initTable.getColumnByIndex(columnIndex);
        for(int i = 0; i < column.getCellCount(); i++){
            SetStyleToCell(i, column.getColumnIndex());
        }
    }

    public void SetStyleToCell(int rowIndex, int columnIndex){
        var cell = _initTable.getCellByPosition(columnIndex, rowIndex);
        cell.setCellStyleName(CreateOdfStyle().getStyleNameAttribute());
    }

    public void SetBackgroundColor(String value){
        this._styles.add(new Style(OdfTableCellProperties.BackgroundColor, value));
    }

    public void SetBold() {
        this._styles.add(new Style(OdfTextProperties.FontWeight, "bold"));
        this._styles.add(new Style(OdfTextProperties.FontWeightAsian, "bold"));
        this._styles.add(new Style(OdfTextProperties.FontWeightComplex, "bold"));
    }

    public void SetCursive() {
        this._styles.add(new Style(OdfTextProperties.FontStyle, "italic"));
        this._styles.add(new Style(OdfTextProperties.FontStyleAsian, "italic"));
        this._styles.add(new Style(OdfTextProperties.FontStyleComplex, "italic"));
    }

    public void SetUnderline() {
        this._styles.add(new Style(OdfTextProperties.TextUnderlineStyle, "solid"));
        this._styles.add(new Style(OdfTextProperties.TextUnderlineColor, "font-color"));
        this._styles.add(new Style(OdfTextProperties.TextUnderlineWidth, "auto"));
    }

    public void SetFontFamliy(String value){
        this._styles.add(new Style(OdfTextProperties.FontFamily, value));
        this._styles.add(new Style(OdfTextProperties.FontFamilyAsian, value));
        this._styles.add(new Style(OdfTextProperties.FontFamilyComplex, value));
        this._styles.add(new Style(OdfTextProperties.FontFamilyGeneric, value));
        this._styles.add(new Style(OdfTextProperties.FontFamilyGenericAsian, value));
        this._styles.add(new Style(OdfTextProperties.FontFamilyGenericComplex, value));
    }

    public void SetFontColor(String value){
        this._styles.add(new Style(OdfTextProperties.Color, value));
    }

    public void SetFontSize(double value){
        this._styles.add(new Style(OdfTextProperties.FontSize, String.valueOf(value)));
    }

    public void SetColumnWidth(int columnIndex, double value){
        _initTable.getColumnByIndex(columnIndex).setWidth(value * 10);
    }

    public void SetRowHeight(int rowIndex, double value){
        _initTable.getRowByIndex(rowIndex).setHeight(value * 10, false);
    }

    public void SetTextAlign(String value){
        if(value.equals("right")) { value = "end"; }
        if(value.equals("left")) { value = "start"; }
        this._styles.add(new Style(OdfParagraphProperties.TextAlign, value));
    }

    public void SaveFile(String path, String fileName){
        try {
            var file = new File(path, fileName + ".ods");
            _spreadsheetDocument.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* PRIVATE METHODS */
    private OdfStyle CreateOdfStyle(){
        var style = _odfOfficeAutomaticStyles.newStyle(OdfStyleFamily.TableCell);
        _styles.forEach(item -> style.setProperty(item.property, item.value));
        return style;
    }

    private void SetRowStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, (Integer) key, -1);
            SetStyleToRow((Integer) key);
        });
    }

    private void SetColumnStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, -1, (Integer) key);
            SetStyleToColumn((Integer) key);
        });
    }

    private void SetCellStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, ((Point)key).x, ((Point)key).y);
            SetStyleToCell(((Point)key).x, ((Point)key).y);
        });
    }

    private void WrapperSetStyleToTable(de.hskempten.tabulang.datatypes.Style styles, int rowIndex, int columnIndex) {
        styles.forEach(item -> {
            var key = item.getKey();
            var value = item.getValue();
            switch(key){
                case "font-color":
                    SetFontColor(value);
                    break;
                case "font-family":
                    SetFontFamliy(value);
                    break;
                case "font-size":
                    SetFontSize(Double.parseDouble(value));
                    break;
                case "text-align":
                    SetTextAlign(value);
                    break;
                case "background-color":
                    SetBackgroundColor(value);
                    break;
                case "bold":
                    SetBold();
                    break;
                case "italics":
                    SetCursive();
                    break;
                case "underlined":
                    SetUnderline();
                    break;
                case "rowheight":
                    SetRowHeight(rowIndex, Double.parseDouble(value));
                    break;
                case "colwidth":
                    SetColumnWidth(columnIndex, Double.parseDouble(value));
                    break;
            }
        });
    }
}

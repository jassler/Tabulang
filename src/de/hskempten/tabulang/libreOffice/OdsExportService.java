package de.hskempten.tabulang.libreOffice;

import de.hskempten.tabulang.libreOffice.Models.Style;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.dom.style.props.*;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;

public class OdsExportService {
    private SpreadsheetDocument _spreadsheetDocument;
    private OdfOfficeAutomaticStyles _odfOfficeAutomaticStyles;
    private Table _initTable;
    private ArrayList<Style> _styles;

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
        //this.OdfStyle = _odfOfficeAutomaticStyles.newStyle(OdfStyleFamily.TableCell);
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

    private OdfStyle CreateOdfStyle(){
        var style = _odfOfficeAutomaticStyles.newStyle(OdfStyleFamily.TableCell);
        _styles.forEach(item -> style.setProperty(item.property, item.value));
        return style;
    }

    public void SetBackgroundColor(String value){
        this._styles.add(new Style(OdfTableCellProperties.BackgroundColor, value));
        //this.OdfStyle.setProperty(OdfTableCellProperties.BackgroundColor, value);
    }

    public void SetBold() {
        this._styles.add(new Style(OdfTextProperties.FontWeight, "bold"));
        this._styles.add(new Style(OdfTextProperties.FontWeightAsian, "bold"));
        this._styles.add(new Style(OdfTextProperties.FontWeightComplex, "bold"));
        //this.OdfStyle.setProperty(OdfTextProperties.FontWeight, "bold");
        //this.OdfStyle.setProperty(OdfTextProperties.FontWeightAsian, "bold");
        //this.OdfStyle.setProperty(OdfTextProperties.FontWeightComplex, "bold");
    }

    public void SetCursive() {
        this._styles.add(new Style(OdfTextProperties.FontStyle, "italic"));
        this._styles.add(new Style(OdfTextProperties.FontStyleAsian, "italic"));
        this._styles.add(new Style(OdfTextProperties.FontStyleComplex, "italic"));
        //this.OdfStyle.setProperty(OdfTextProperties.FontStyle, "italic");
        //this.OdfStyle.setProperty(OdfTextProperties.FontStyleAsian, "italic");
        //this.OdfStyle.setProperty(OdfTextProperties.FontStyleComplex, "italic");
    }

    public void SetUnderline() {
        this._styles.add(new Style(OdfTextProperties.TextUnderlineStyle, "solid"));
        this._styles.add(new Style(OdfTextProperties.TextUnderlineColor, "font-color"));
        this._styles.add(new Style(OdfTextProperties.TextUnderlineWidth, "auto"));
        //this.OdfStyle.setProperty(OdfTextProperties.TextUnderlineStyle, "solid");
        //this.OdfStyle.setProperty(OdfTextProperties.TextUnderlineColor, "font-color");
        //this.OdfStyle.setProperty(OdfTextProperties.TextUnderlineWidth, "auto");
    }

    public void SetFontColor(String value){
        this._styles.add(new Style(OdfTextProperties.Color, value));
        //this.OdfStyle.setProperty(OdfTextProperties.Color, value);
    }

    public void SetFontSize(double value){
        this._styles.add(new Style(OdfTextProperties.FontSize, String.valueOf(value)));
        //this.OdfStyle.setProperty(OdfTextProperties.FontSize, String.valueOf(value));
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
        //this.OdfStyle.setProperty(OdfParagraphProperties.TextAlign, value);
    }

    public void SaveFile(String path, String fileName){
        try {
            var file = new File(path, fileName + ".ods");
            _spreadsheetDocument.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CopyToClipboard() {
        try {
            var selection = new StringSelection(_spreadsheetDocument.getContentDom().toString());
            var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

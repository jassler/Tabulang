package de.hskempten.tabulang.libreOffice;

import com.github.jferard.fastods.AnonymousOdsFileWriter;
import com.github.jferard.fastods.OdsDocument;
import com.github.jferard.fastods.OdsFactory;
import com.github.jferard.fastods.Table;
import de.hskempten.tabulang.libreOffice.Models.*;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

import org.odftoolkit.odfdom.doc.OdfDocument;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class CalcConnection {
    /* PROPERTIES */
    private String _instanceName;
    private String _tableName;
    private String _path;
    private String _fileName;
    private OdsFactory _odsFactory;
    private AnonymousOdsFileWriter _writer;
    private OdsDocument _odsDocument;
    private OdfDocument _odfDocument;
    private Document _xmlDocument;
    private DocumentBuilderFactory _documentBuilderFactory;
    private DocumentBuilder _documentBuilder;
    private Table _table;
    private Spreadsheet _spreadsheet;
    private ArrayList<Column> _columnList;
    private ArrayList<Row> _rowList;

    /* CONSTRUCTOR */
    public CalcConnection(String instanceName, String tableName, String path, String fileName) {
        _instanceName = instanceName;
        _tableName = tableName;
        _path = path;
        _fileName = fileName;
        CreateCalcConnection();
    }

    /* PUBLIC METHODS */
    public void Export(MSqlTableContent MSqlTableContent){
        try {
            _table = _odsDocument.addTable(this._tableName);
            CreateTableHeadline(MSqlTableContent.get_headlines());
            CreateTableContent(MSqlTableContent.get_content());
            SaveCalcFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Import(String path) {
        try {
            _odfDocument = OdfDocument.loadDocument(path);
            FindElementInXml(_odfDocument.getContentDom().toString());
            CopyToClipboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CopyToClipboard() throws Exception {
        var selection = new StringSelection(_odfDocument.getContentDom().toString());
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    /* PRIVATE METHODS */
    private void FindElementInXml(String xml) {
        try {
            _documentBuilderFactory = DocumentBuilderFactory.newInstance();
            _documentBuilder = _documentBuilderFactory.newDocumentBuilder();
            _xmlDocument = _documentBuilder.parse(new InputSource(new StringReader(xml)));
            _xmlDocument.normalize();
            CreateAllLists();
            FontStyles();
            StylesWrapper();
            CreateTableWrapper();
            /*
            _tableWrapper._tableName = GetTableInformation("table:name");
            _tableWrapper._styleName = GetTableInformation("table:style-name");
            Columns();
            Rows();
             */

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void CreateTableWrapper() {
        var nodeList = GetNodeList("table:table");
        var nodeListLength = nodeList.getLength();

        var temp = new ArrayList<TableWrapper>();
        for(var i = 0; i < nodeListLength; i++){
            var tableWrapper = new TableWrapper();
            var item = nodeList.item(i);
            tableWrapper._attributes = GetAttributes(item);

            var selectCorrectChildren = item.getChildNodes();

            _columnList = new ArrayList<>();
            VisitColumns(selectCorrectChildren, "table:table-column");
            tableWrapper._columns = _columnList;

            _rowList = new ArrayList<>();
            VisitRows(selectCorrectChildren, "table:table-row");
            tableWrapper._rows = _rowList;

            temp.add(tableWrapper);
        }
        _spreadsheet._tables = temp;
    }

    private void CreateAllLists() {
        _spreadsheet = new Spreadsheet();
        _spreadsheet._fontStyles = new ArrayList<>();
        _spreadsheet._tableStyles = new HashMap<>();
        _spreadsheet._rowStyles = new HashMap<>();
        _spreadsheet._columnStyles = new HashMap<>();
        _spreadsheet._cellStyles = new HashMap<>();
    }

    private NodeList GetNodeList(String tagName){
        return _xmlDocument.getElementsByTagName(tagName);
    }

    private void FontStyles() {
        var nodeList = GetNodeList("style:font-face");
        var nodeListLength = nodeList.getLength();

        var temp = new ArrayList<HashMap<String, String>>();
        for(var i = 0; i < nodeListLength; i++){
            var item = nodeList.item(i);
            temp.add(GetAttributes(item));
        }
        _spreadsheet._fontStyles = temp;
    }

    private void StylesWrapper(){
        var nodeList = GetNodeList("style:style");
        var nodeListLength = nodeList.getLength();

        for (int temp = 0; temp < nodeListLength; temp++)
        {
            var node = nodeList.item(temp);
            var nodeAttributes = GetAttributes(node);
            switch (Objects.requireNonNull(nodeAttributes).get("style:family")){
                case "table":
                    var nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet._tableStyles.put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-row":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet._rowStyles.put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-column":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet._columnStyles.put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-cell":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet._cellStyles.put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                default:
                    break;
            }
        }
    }

    private HashMap<String,String> GetAttributes(Node node){
        var returnList = new HashMap<String, String>();
        if (node.hasAttributes()) {
            var nodeMap = node.getAttributes();
            for (int i = 0; i < nodeMap.getLength(); i++)
            {
                Node tempNode = nodeMap.item(i);
                returnList.put(tempNode.getNodeName(), tempNode.getNodeValue());
            }
            return returnList;
        }
        return null;
    }

    private ArrayList<Cell> SearchCell(NodeList nList) {
        var list = new ArrayList<Cell>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            var cell = new Cell();
            cell.Attributes = GetAttributes(node);
            if (node.hasChildNodes()) {
                var children = node.getChildNodes();
                for(int valueIndex = 0; valueIndex < children.getLength(); valueIndex++){
                    var cellValue = children.item(valueIndex);
                    cell._value = cellValue.getFirstChild().getNodeValue();
                }
            }
            list.add(cell);
        }
        return list;
    }

    private void VisitColumns(NodeList nList, String tagName) {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            if(node.getNodeName().equals(tagName)){
                var column = new Column();
                column.Attributes = GetAttributes(node);
                _columnList.add(column);
            }
            if (node.hasChildNodes()) {
                VisitColumns(node.getChildNodes(), tagName);
            }
        }
    }

    private void VisitRows(NodeList nList, String tagName) {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            if(node.getNodeName().equals(tagName)){
                var row = new Row();
                row.Attributes = GetAttributes(node);
                _rowList.add(row);
                row._cells = SearchCell(node.getChildNodes());
            }
            if (node.hasChildNodes()) {
                VisitRows(node.getChildNodes(), tagName);
            }
        }
    }

    private void CreateCalcConnection(){
        _odsFactory = OdsFactory.create(Logger.getLogger(this._instanceName), Locale.GERMANY);
        _writer = _odsFactory.createWriter();
        _odsDocument = _writer.document();
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
            for(var i = 0; i < content.size(); i++){
                var row = _table.getRow(i + 1);
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
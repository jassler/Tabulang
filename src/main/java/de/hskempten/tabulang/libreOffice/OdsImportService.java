package de.hskempten.tabulang.libreOffice;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.libreOffice.Models.*;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class OdsImportService {
    /* PROPERTIES */
    private OdfDocument _odfDocument;
    private Document _xmlDocument;
    private MSpreadsheet _spreadsheet;
    private ArrayList<MColumn> _columnList;
    private ArrayList<MRow> _rowList;

    /* PUBLIC METHODS */

    /**
     * Constructor
     */

    public OdsImportService() {}

    /**
     * Import an *.ods-File from a specific path of the file explorer
     *
     * @param path  Specific path
     */

    public MSpreadsheet Import(String path) {
        try {
            _odfDocument = OdfDocument.loadDocument(path);
            FindElementInXml(_odfDocument.getContentDom().toString());
            return _spreadsheet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Table<InternalString> ImportFile(String path)  {
        MSpreadsheet spreadsheet = Import(path);
        ArrayList<InternalString> headernames = spreadsheet.get_headlines(0);
        ArrayList<Tuple<InternalString>> tableRows = spreadsheet.get_values(0);

        return new Table<>(headernames, tableRows);
    }

    /* PRIVATE METHODS */

    /**
     * Helper function for {@link OdsImportService#FindElementInXml(String)}
     * Wrapper function to filter all styles and contents from the xml-File
     *
     * @param xml   Path to XML-File
     */

    private void FindElementInXml(String xml) {
        try {
            DocumentBuilderFactory _documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder _documentBuilder = _documentBuilderFactory.newDocumentBuilder();
            _xmlDocument = _documentBuilder.parse(new InputSource(new StringReader(xml)));
            _xmlDocument.normalize();
            CreateAllLists();
            FontStyles();
            StylesWrapper();
            CreateTableWrapper();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function for {@link OdsImportService#FindElementInXml(String)}
     * Create a new object of the type {@link MTableWrapper} and insert all rows and columns into this object
     */

    private void CreateTableWrapper() {
        var nodeList = GetNodeList("table:table");
        var nodeListLength = nodeList.getLength();

        var temp = new ArrayList<MTableWrapper>();
        for(var i = 0; i < nodeListLength; i++){
            var tableWrapper = new MTableWrapper();
            var item = nodeList.item(i);
            tableWrapper.set_attributes(GetAttributes(item));

            var selectCorrectChildren = item.getChildNodes();

            _columnList = new ArrayList<>();
            VisitColumns(selectCorrectChildren, "table:table-column");
            tableWrapper.set_columns(_columnList);

            _rowList = new ArrayList<>();
            VisitRows(selectCorrectChildren, "table:table-row");
            tableWrapper.set_rows(_rowList);

            temp.add(tableWrapper);
        }
        _spreadsheet.set_tables(temp);
    }

    /**
     * Helper function for {@link OdsImportService#FindElementInXml(String)}
     * Create all necessary lists for the spreadsheet
     */

    private void CreateAllLists() {
        _spreadsheet = new MSpreadsheet();
        _spreadsheet.set_fontStyles(new ArrayList<>());
        _spreadsheet.set_tableStyles(new HashMap<>());
        _spreadsheet.set_rowStyles(new HashMap<>());
        _spreadsheet.set_columnStyles(new HashMap<>());
        _spreadsheet.set_cellStyles(new HashMap<>());
    }

    /**
     * Helper function for {@link OdsImportService#CreateTableWrapper()}
     * Helper function for {@link OdsImportService#FontStyles()}
     * Helper function for {@link OdsImportService#StylesWrapper()}
     *
     * Returns all nodes with a specific tag name
     *
     * @param tagName Specific tag name
     * @return List of nodes (NodeList)
     */

    private NodeList GetNodeList(String tagName){
        return _xmlDocument.getElementsByTagName(tagName);
    }

    /**
     * Helper function for {@link OdsImportService#FindElementInXml(String)}
     * Filter the font styles from a NodeList-object
     */

    private void FontStyles() {
        var nodeList = GetNodeList("style:font-face");
        var nodeListLength = nodeList.getLength();

        var temp = new ArrayList<HashMap<String, String>>();
        for(var i = 0; i < nodeListLength; i++){
            var item = nodeList.item(i);
            temp.add(GetAttributes(item));
        }
        _spreadsheet.set_fontStyles(temp);
    }

    /**
     * Helper function for {@link OdsImportService#FindElementInXml(String)}
     * Filter the styles for the whole document from a NodeList-object
     */

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
                    _spreadsheet.get_tableStyles().put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-row":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet.get_rowStyles().put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-column":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet.get_columnStyles().put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                case "table-cell":
                    nodeStyleId = nodeAttributes.get("style:name");
                    _spreadsheet.get_cellStyles().put(nodeStyleId, GetAttributes(node.getFirstChild()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Get all attributes from a node tag
     *
     * @param node  Specific node from which the attributes are to be extracted
     * @return Hashmap (key as string and value as string) which represent all attributes
     */

    private HashMap<String,String> GetAttributes(Node node){
        var returnList = new HashMap<String, String>();

        if(node == null)
            return null;

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

    /**
     * Helper function for {@link OdsImportService#CreateTableWrapper()}
     * Helper function for {@link OdsImportService#FontStyles()}
     * Helper function for {@link OdsImportService#StylesWrapper()}
     * Helper function for {@link OdsImportService#SearchCell(NodeList)}
     * Helper function for {@link OdsImportService#VisitColumns(NodeList, String)}
     * Helper function for {@link OdsImportService#VisitRows(NodeList, String)}
     *
     * Filter every cell of a table and get the cell attributes and the value
     *
     * @param nList NodeList of a specific tag
     * @return ArrayList of Cell objects
     */

    private ArrayList<MCell> SearchCell(NodeList nList) {
        var list = new ArrayList<MCell>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            var cell = new MCell();
            cell.setAttributes(GetAttributes(node));
            if (node.hasChildNodes()) {
                var children = node.getChildNodes();
                for(int valueIndex = 0; valueIndex < children.getLength(); valueIndex++){
                    var cellValue = children.item(valueIndex);
                    cell.set_value(cellValue.getFirstChild().getNodeValue());
                }
            }
            list.add(cell);
        }
        return list;
    }

    /**
     * Helper function for {@link OdsImportService#CreateTableWrapper()}
     *
     * Visit all columns recursive
     *
     * @param nList     List of nodes to visit
     * @param tagName   Specific tag name
     */

    private void VisitColumns(NodeList nList, String tagName) {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            if(node.getNodeName().equals(tagName)){
                var column = new MColumn();
                column.setAttributes(GetAttributes(node));
                _columnList.add(column);
            }
            if (node.hasChildNodes()) {
                VisitColumns(node.getChildNodes(), tagName);
            }
        }
    }

    /**
     * Helper function for {@link OdsImportService#CreateTableWrapper()}
     *
     * Visit all rows recursive
     *
     * @param nList     List of nodes to visit
     * @param tagName   Specific tag name
     */

    private void VisitRows(NodeList nList, String tagName) {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            var node = nList.item(temp);
            if(node.getNodeName().equals(tagName)){
                var row = new MRow();
                row.setAttributes(GetAttributes(node));
                _rowList.add(row);
                row.set_cells(SearchCell(node.getChildNodes()));
            }
            if (node.hasChildNodes()) {
                VisitRows(node.getChildNodes(), tagName);
            }
        }
    }
}
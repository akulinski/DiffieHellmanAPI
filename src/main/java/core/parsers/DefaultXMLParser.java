package core.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class DefaultXMLParser implements IXMLParser<String> {

    private String path;
    private File fXmlFile;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;

    public String getPath() {
        return path;
    }

    public File getfXmlFile() {
        return fXmlFile;
    }

    public DocumentBuilderFactory getDocumentBuilderFactory() {
        return documentBuilderFactory;
    }

    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }

    public Document getDocument() {
        return document;
    }

    public NodeList getNodeList() {
        return nodeList;
    }

    public LinkedList<String> getAddressesList() {
        return addressesList;
    }

    private NodeList nodeList;

    private LinkedList<String> addressesList;

    public DefaultXMLParser() {
        this.addressesList = new LinkedList<>();
    }

    public void loadResource(String path) {
        this.path = path;
        fXmlFile = new File(path);

        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public boolean parse() {
        try {
            document = documentBuilder.parse(fXmlFile);
            document.getDocumentElement().normalize();
            nodeList = document.getElementsByTagName("address");
            populateAddressesList();
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public LinkedList<String> getValue() {
        return this.addressesList;
    }

    private void populateAddressesList() {

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node tmpNode = nodeList.item(i);

            if (tmpNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) tmpNode;

                String address = element.getAttribute("ad");
                this.addressesList.add(address);
            }

        }
    }
}

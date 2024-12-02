package hu.domparse.IKXS9J;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class DomWriteIKXS9J {
    public static void main(String[] args) {
        try {
            // 1. Beolvassuk az XML fájlt
            File inputFile = new File("XML_IKXS9J.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            // 2. A fa struktúra kiírása a konzolra
            System.out.println("A dokumentum tartalmának fa struktúrája:");
            printNode(document.getDocumentElement(), 0);

            // 3. Az XML dokumentum mentése egy új fájlba (XML_IKXS9J1.xml)
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XML_IKXS9J1.xml"));
            transformer.transform(source, result);

            System.out.println("\nA dokumentum sikeresen mentésre került az XML_IKXS9J1.xml fájlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Rekurzív metódus a csomópontok fa struktúrájának kiírására
    private static void printNode(Node node, int indent) {
        // Tabulátorok a megfelelő behúzáshoz
        String indentString = new String(new char[indent]).replace('\0', '\t');
        
        // Elem kiírása
        System.out.println(indentString + "Node Name: " + node.getNodeName());

        // Ha van text tartalom
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE || childNode.getNodeType() == Node.TEXT_NODE) {
                    printNode(childNode, indent + 1);  // Rekurzív hívás
                }
            }
        }

        // Ha van attribútum, azt is kiírjuk
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attr = attributes.item(i);
                    System.out.println(indentString + "\tAttribute: " + attr.getNodeName() + " = " + attr.getNodeValue());
                }
            }
        }
    }
}


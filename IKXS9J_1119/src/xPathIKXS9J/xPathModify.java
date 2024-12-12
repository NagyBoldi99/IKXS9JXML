package xpathIKXS9J;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModify {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("IKXS9J_1119/xPathIKXS9J/orarendIKXS9J.xml");
        doc.getDocumentElement().normalize();

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        String pa1 = "//szak";
        String pa2 = "//targy";
        String pa3 = "//ora[@id='e2']/helyszin";

        NodeList CKFEC91 = (NodeList) xpath.compile(pa1).evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);
        editText(CKFEC91, "PTI");
        NodeList CKFEC92 = (NodeList) xpath.compile(pa2).evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);
        appendText(CKFEC92, " (PA)");
        NodeList CKFEC93 = (NodeList)xpath.compile(pa3).evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);
        Element element = (Element) CKFEC93.item(0);
        element.setTextContent("XXXVII");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        
        Transformer transf = transformerFactory.newTransformer(new StreamSource(new File("./IKXS9J/DOMParseIKXS9J/pretty.xsl")));

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.console().writer());
        StreamResult file = new StreamResult("IKXS9J_1119/xPathIKXS9J/orarendIKXS9J.xml");

        transf.transform(source, console);
        transf.transform(source, file);
   
    }

    static void editText(NodeList IKXS9J, String newtext) {
        for (int i = 0; i < IKXS9J.getLength(); i++) {
            Node node = IKXS9J.item(i);
            Element elem = (Element) node;
            elem.setTextContent(newtext);
        }
    }

    static void appendText(NodeList IKXS9J, String newtext) {
        for (int i = 0; i < IKXS9J.getLength(); i++) {
            Node node = IKXS9J.item(i);
            Element elem = (Element) node;
            String text = elem.getTextContent();
            elem.setTextContent(text + newtext);
        }
    }
}

package xpathIKXS9J;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathIKXS9J {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("IKXS9J_1119/xPathIKXS9J/studentIKXS9J.xml");
        doc.getDocumentElement().normalize();

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        // String pa = "class";
        // String pa = "/class/student[@id='02']";
        // String pa = "//student";
        // String pa = "class/student[2]";
        // String pa = "class/student[last()]";
        // String pa = "class/student[last()-1]";
        // String pa = "class/student[position()<3]";
        // String pa = "class/*";
        // String pa = "//student[@*]";
        // String pa = "//node()";
        // String pa = "/class/student[kor>20]";
        String pa = "//student/keresztnev | //student/vezeteknev";

        NodeList CKFEC9 = (NodeList) xpath.compile(pa).evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);

        for (int i = 0; i < CKFEC9.getLength(); i++) {
            Node node = CKFEC9.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){

                System.out.println("\nAktualis elem: " + node.getNodeName());
                if (node.getNodeName() == "student") {
                    Element elem = (Element) node;
                    System.out.println("Id: "+elem.getAttribute("id"));
                    System.out.println("Vezeteknev: "+elem.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println("Keresztnev: "+elem.getElementsByTagName("keresztnev").item(0).getTextContent());
                    System.out.println("Becenev: "+elem.getElementsByTagName("becenev").item(0).getTextContent());
                    System.out.println("Kor: "+elem.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        }
    }
    
}
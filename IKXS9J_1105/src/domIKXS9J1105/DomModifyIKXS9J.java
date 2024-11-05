package domIKXS9J1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DomModifyIKXS9J {
    public static void main(String[] args) {
        try {
          
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("hallgatoIKXS9J.xml"));

            NodeList hallgatok = document.getElementsByTagName("hallgato");

           
            for (int i = 0; i < hallgatok.getLength(); i++) {
                Element hallgato = (Element) hallgatok.item(i);
                if ("01".equals(hallgato.getAttribute("id"))) {
                    
                    NodeList keresztnevList = hallgato.getElementsByTagName("keresztnev");
                    NodeList vezeteknevList = hallgato.getElementsByTagName("vezeteknev");

                    if (keresztnevList.getLength() > 0) {
                        keresztnevList.item(0).setTextContent("János");
                    }
                    if (vezeteknevList.getLength() > 0) {
                        vezeteknevList.item(0).setTextContent("Nagy");
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            

            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            System.out.println("Módosított XML:");
            System.out.println(writer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


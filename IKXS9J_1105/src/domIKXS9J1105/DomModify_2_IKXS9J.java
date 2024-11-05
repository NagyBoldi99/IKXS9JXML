package domIKXS9J1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

public class DomModify_2_IKXS9J {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("IKXS9J_orarend.xml"));

            NodeList oraList = document.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                
                NodeList oraadoList = ora.getElementsByTagName("oraado");
                if (oraadoList.getLength() == 0) {
                    Element oraado = document.createElement("oraado");
                    oraado.setTextContent("Szűcs Miklós");

                    ora.appendChild(oraado);

                    break;
                }
            }
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                
                if ("gyakorlat".equals(ora.getAttribute("tipus"))) {
                    ora.setAttribute("tipus", "eloadas");
                }
            }  
            

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();


            DOMSource source = new DOMSource(document);
            
            StringWriter consoleWriter = new StringWriter();
            transformer.transform(source, new StreamResult(consoleWriter));
            System.out.println("Módosított fájl:");
            System.out.println(consoleWriter.toString());

            FileWriter fileWriter = new FileWriter("orarendModifyIKXS9J.xml");
            transformer.transform(source, new StreamResult(fileWriter));
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package hu.domparse.IKXS9J;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class DomModifyIKXS9J {
    public static void main(String[] args) {
        try {
            // XML fájl betöltéses
            File inputFile = new File("XML_IKXS9J.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            // 1. Felhasználó módosítása
            NodeList felhasznalok = document.getElementsByTagName("Felhasznalo");
            for (int i = 0; i < felhasznalok.getLength(); i++) {
                Node node = felhasznalok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element felhasznalo = (Element) node;
                    // Feltételezzük, hogy a felhasználó ID-ja alapján módosítunk
                    String id = felhasznalo.getElementsByTagName("id").item(0).getTextContent();
                    if (id.equals("1")) { // Itt lehet módosítani a felhasználó ID-ját
                        felhasznalo.getElementsByTagName("nev").item(0).setTextContent("Új Név");
                        felhasznalo.getElementsByTagName("email").item(0).setTextContent("ujemail@example.com");
                        System.out.println("Felhasználó módosítva.");
                    }
                }
            }

         // 2. Raktár módosítása
            NodeList raktarak = document.getElementsByTagName("Raktar");
            for (int i = 0; i < raktarak.getLength(); i++) {
                Node node = raktarak.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element raktar = (Element) node;
                    // Raktár ID alapján módosítunk
                    String raktarId = raktar.getElementsByTagName("raktar_id").item(0).getTextContent();
                    if (raktarId.equals("1")) { // Itt módosítható a raktár ID-ja
                        Element arakElem = (Element) raktar.getElementsByTagName("arak").item(0);
                        arakElem.getElementsByTagName("ar_berles").item(0).setTextContent("5000");
                        arakElem.getElementsByTagName("ar_vetel").item(0).setTextContent("120000");
                        System.out.println("Raktár módosítva.");
                    }
                }
            }

            // 3. Esemény módosítása
            NodeList esemenyek = document.getElementsByTagName("Esemeny");
            for (int i = 0; i < esemenyek.getLength(); i++) {
                Node node = esemenyek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element esemeny = (Element) node;
                    // Esemény ID alapján módosítunk
                    String esemenyId = esemeny.getElementsByTagName("esemeny_id").item(0).getTextContent();
                    if (esemenyId.equals("1")) { // Itt módosítható az esemény ID-ja
                        esemeny.getElementsByTagName("esemeny_nev").item(0).setTextContent("ÚjEseményNév");
                        esemeny.getElementsByTagName("helyszin").item(0).setTextContent("Új Helyszín");
                        System.out.println("Esemény módosítva.");
                    }
                }
            }

            // Dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(inputFile); // Felülírjuk az eredeti fájlt
            transformer.transform(source, result);

            System.out.println("XML dokumentum sikeresen módosítva és mentve.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

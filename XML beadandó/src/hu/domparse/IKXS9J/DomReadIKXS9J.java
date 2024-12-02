package hu.domparse.IKXS9J;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomReadIKXS9J {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("XML_IKXS9J.xml"));
            document.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + document.getDocumentElement().getNodeName());

            // Felhasználók feldolgozása
            System.out.println("\n--- Felhasználók ---");
            NodeList felhasznalok = document.getElementsByTagName("Felhasznalo");
            for (int i = 0; i < felhasznalok.getLength(); i++) {
                Node node = felhasznalok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element felhasznalo = (Element) node;
                    System.out.println("ID: " + felhasznalo.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Név: " + felhasznalo.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Email: " + felhasznalo.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("Neme: " + felhasznalo.getElementsByTagName("neme").item(0).getTextContent());
                    System.out.println("Jelszó: " + felhasznalo.getElementsByTagName("jelszo").item(0).getTextContent());
                    System.out.println();
                }
            }

            // Raktárak feldolgozása
            System.out.println("\n--- Raktárak ---");
            NodeList raktarak = document.getElementsByTagName("Raktar");
            for (int i = 0; i < raktarak.getLength(); i++) {
                Node node = raktarak.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element raktar = (Element) node;
                    System.out.println("Raktár ID: " + raktar.getElementsByTagName("raktar_id").item(0).getTextContent());
                    System.out.println("Bérlés ára: " + raktar.getElementsByTagName("ar_berles").item(0).getTextContent());
                    System.out.println("Vétel ára: " + raktar.getElementsByTagName("ar_vetel").item(0).getTextContent());
                    System.out.println("Cím: " + raktar.getElementsByTagName("cim").item(0).getTextContent());
                    System.out.println("Tulajdonos ID: " + raktar.getElementsByTagName("tulajdonos_id").item(0).getTextContent());
                    System.out.println();
                }
            }

            // Események feldolgozása
            System.out.println("\n--- Események ---");
            NodeList esemenyek = document.getElementsByTagName("Esemeny");
            for (int i = 0; i < esemenyek.getLength(); i++) {
                Node node = esemenyek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element esemeny = (Element) node;
                    System.out.println("Esemény ID: " + esemeny.getElementsByTagName("esemeny_id").item(0).getTextContent());
                    System.out.println("Név: " + esemeny.getElementsByTagName("esemeny_nev").item(0).getTextContent());
                    System.out.println("Dátum: " + esemeny.getElementsByTagName("datum").item(0).getTextContent());
                    System.out.println("Helyszín: " + esemeny.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Leírás: " + esemeny.getElementsByTagName("leiras").item(0).getTextContent());
                    System.out.println();
                }
            }

            // Bérlések feldolgozása
            System.out.println("\n--- Bérlések ---");
            NodeList berlesek = document.getElementsByTagName("Berles");
            for (int i = 0; i < berlesek.getLength(); i++) {
                Node node = berlesek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element berles = (Element) node;
                    System.out.println("Tranzakció ID: " + berles.getElementsByTagName("tranzakcio_id").item(0).getTextContent());
                    System.out.println("Felhasználó ID: " + berles.getElementsByTagName("felhasznalo_id").item(0).getTextContent());
                    System.out.println("Raktár ID: " + berles.getElementsByTagName("raktar_id").item(0).getTextContent());
                    System.out.println("Típus: " + berles.getElementsByTagName("tipus").item(0).getTextContent());
                    System.out.println("Kezdés dátum: " + berles.getElementsByTagName("kezdes_datum").item(0).getTextContent());
                    System.out.println("Ár: " + berles.getElementsByTagName("ar").item(0).getTextContent());
                    System.out.println();
                }
            }

            // Kedvezmények feldolgozása
            System.out.println("\n--- Kedvezmények ---");
            NodeList kedvezmenyek = document.getElementsByTagName("Kedvezmeny");
            for (int i = 0; i < kedvezmenyek.getLength(); i++) {
                Node node = kedvezmenyek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element kedvezmeny = (Element) node;
                    System.out.println("Kedvezmény ID: " + kedvezmeny.getElementsByTagName("kedvezmeny_id").item(0).getTextContent());
                    System.out.println("Felhasználó ID: " + kedvezmeny.getElementsByTagName("felhasznalo_id").item(0).getTextContent());
                    System.out.println("Kedvezmény százalék: " + kedvezmeny.getElementsByTagName("kedvezmeny_szazalek").item(0).getTextContent());
                    System.out.println("Érvényesség vége: " + kedvezmeny.getElementsByTagName("ervenyesseg_vege").item(0).getTextContent());
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

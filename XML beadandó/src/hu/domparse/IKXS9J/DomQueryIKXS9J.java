package hu.domparse.IKXS9J;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomQueryIKXS9J {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File inputFile = new File("XML_IKXS9J.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            // 1. Felhasználó lekérdezése (Első felhasználó)
            NodeList felhasznalok = document.getElementsByTagName("Felhasznalo");
            if (felhasznalok.getLength() > 0) {
                Element felhasznalo = (Element) felhasznalok.item(0);
                String id = felhasznalo.getElementsByTagName("id").item(0).getTextContent();
                String nev = felhasznalo.getElementsByTagName("nev").item(0).getTextContent();
                String email = felhasznalo.getElementsByTagName("email").item(0).getTextContent();
                String neme = felhasznalo.getElementsByTagName("neme").item(0).getTextContent();
                System.out.println("Felhasználó: " + id + ", " + nev + ", " + email + ", " + neme);
            }

            // 2. Raktár lekérdezése (Első raktár)
            NodeList raktarak = document.getElementsByTagName("Raktar");
            if (raktarak.getLength() > 0) {
                Element raktar = (Element) raktarak.item(0);
                String raktarId = raktar.getElementsByTagName("raktar_id").item(0).getTextContent();
                String arBerles = raktar.getElementsByTagName("ar_berles").item(0).getTextContent();
                String arVetel = raktar.getElementsByTagName("ar_vetel").item(0).getTextContent();
                String cim = raktar.getElementsByTagName("cim").item(0).getTextContent();
                System.out.println("Raktár: " + raktarId + ", Bérlés ár: " + arBerles + ", Vétel ár: " + arVetel + ", Cím: " + cim);
            }

            // 3. Esemény lekérdezése (Első esemény)
            NodeList esemenyek = document.getElementsByTagName("Esemeny");
            if (esemenyek.getLength() > 0) {
                Element esemeny = (Element) esemenyek.item(0);
                String esemenyId = esemeny.getElementsByTagName("esemeny_id").item(0).getTextContent();
                String esemenyNev = esemeny.getElementsByTagName("esemeny_nev").item(0).getTextContent();
                String datum = esemeny.getElementsByTagName("datum").item(0).getTextContent();
                String helyszin = esemeny.getElementsByTagName("helyszin").item(0).getTextContent();
                System.out.println("Esemény: " + esemenyId + ", " + esemenyNev + ", Dátum: " + datum + ", Helyszín: " + helyszin);
            }

            // 4. Bérlés lekérdezése (Első bérlés)
            NodeList berlesek = document.getElementsByTagName("Berles");
            if (berlesek.getLength() > 0) {
                Element berles = (Element) berlesek.item(0);
                String tranzakcioId = berles.getElementsByTagName("tranzakcio_id").item(0).getTextContent();
                String felhasznaloId = berles.getElementsByTagName("felhasznalo_id").item(0).getTextContent();
                String raktarId = berles.getElementsByTagName("raktar_id").item(0).getTextContent();
                String tipus = berles.getElementsByTagName("tipus").item(0).getTextContent();
                String kezdesDatum = berles.getElementsByTagName("kezdes_datum").item(0).getTextContent();
                String ar = berles.getElementsByTagName("ar").item(0).getTextContent();
                System.out.println("Bérlés: " + tranzakcioId + ", Felhasználó ID: " + felhasznaloId + ", Raktár ID: " + raktarId + ", Típus: " + tipus + ", Kezdés: " + kezdesDatum + ", Ár: " + ar);
            }

            // 5. Kedvezmény lekérdezése (Első kedvezmény)
            NodeList kedvezmenyek = document.getElementsByTagName("Kedvezmeny");
            if (kedvezmenyek.getLength() > 0) {
                Element kedvezmeny = (Element) kedvezmenyek.item(0);
                String kedvezmenyId = kedvezmeny.getElementsByTagName("kedvezmeny_id").item(0).getTextContent();
                String felhasznaloId = kedvezmeny.getElementsByTagName("felhasznalo_id").item(0).getTextContent();
                String kedvezmenySzazalek = kedvezmeny.getElementsByTagName("kedvezmeny_szazalek").item(0).getTextContent();
                String ervenyessegVege = kedvezmeny.getElementsByTagName("ervenyesseg_vege").item(0).getTextContent();
                System.out.println("Kedvezmény: " + kedvezmenyId + ", Felhasználó ID: " + felhasznaloId + ", Kedvezmény %: " + kedvezmenySzazalek + ", Érvényesség vége: " + ervenyessegVege);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

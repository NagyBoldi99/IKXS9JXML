package hu.domparse.IKXS9J;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DomWriteIKXS9J {
    public static void main(String[] args) {
        try {
            // Dokumentum létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Gyökérelem hozzáadása
            Element rootElement = doc.createElement("RaktarAlkalmazas");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaIKXS9J.xsd");
            doc.appendChild(rootElement);

            // Felhasználók hozzáadása
            Element felhasznalok = doc.createElement("Felhasznalok");
            rootElement.appendChild(felhasznalok);

            addFelhasznalo(doc, felhasznalok, "1", "Kovács István", "istvan.kovacs@example.com", "Férfi", "jelszo123");
            addFelhasznalo(doc, felhasznalok, "2", "Nagy Erika", "erika.nagy@example.com", "Nő", "biztonsagosJelszo");
            addFelhasznalo(doc, felhasznalok, "3", "Szabó Péter", "peter.szabo@example.com", "Férfi", "12345");

            // Raktárak hozzáadása
            Element raktarak = doc.createElement("Raktarak");
            rootElement.appendChild(raktarak);

            addRaktar(doc, raktarak, "1", "10000", "150000", "Budapest, Váci út 10.", "1");
            addRaktar(doc, raktarak, "2", "8000", "120000", "Debrecen, Fő út 12.", "2");
            addRaktar(doc, raktarak, "3", "15000", "200000", "Pécs, Tavasz utca 5.", "3");

            // Események hozzáadása
            Element esemenyek = doc.createElement("Esemenyek");
            rootElement.appendChild(esemenyek);

            addEsemeny(doc, esemenyek, "1", "Licit", "2024-12-05", "Budapest, Váci út 10.", "Raktár árverés.");
            addEsemeny(doc, esemenyek, "2", "Kiárusítás", "2024-12-10", "Debrecen, Fő út 12.", "Kedvezményes áron elérhető termékek.");
            addEsemeny(doc, esemenyek, "3", "Bolhapiac", "2024-12-15", "Pécs, Tavasz utca 5.", "Használt tárgyak árusítása.");

            // Bérlések hozzáadása
            Element berlesek = doc.createElement("Berlesek");
            rootElement.appendChild(berlesek);

            addBerles(doc, berlesek, "1", "2", "1", "Bérlés", "2024-12-01", "10000");

            // Kedvezmények hozzáadása
            Element kedvezmenyek = doc.createElement("Kedvezmenyek");
            rootElement.appendChild(kedvezmenyek);

            addKedvezmeny(doc, kedvezmenyek, "1", "3", "15", "2024-12-31");

            // Kiírás konzolra
            writeDocumentToConsole(doc);

            // Kiírás fájlba
            writeDocumentToFile(doc, "XML_IKXS9J1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeDocumentToFile(Document doc, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    private static void addFelhasznalo(Document doc, Element parent, String id, String nev, String email, String neme, String jelszo) {
        Element user = doc.createElement("Felhasznalo");

        addChildElement(doc, user, "id", id);
        addChildElement(doc, user, "nev", nev);
        addChildElement(doc, user, "email", email);
        addChildElement(doc, user, "neme", neme);
        addChildElement(doc, user, "jelszo", jelszo);

        parent.appendChild(user);
    }

    private static void addRaktar(Document doc, Element parent, String id, String arBerles, String arVetel, String cim, String tulajdonosId) {
        Element raktar = doc.createElement("Raktar");

        addChildElement(doc, raktar, "raktar_id", id);

        Element arak = doc.createElement("arak");
        addChildElement(doc, arak, "ar_berles", arBerles);
        addChildElement(doc, arak, "ar_vetel", arVetel);
        raktar.appendChild(arak);

        addChildElement(doc, raktar, "cim", cim);
        addChildElement(doc, raktar, "tulajdonos_id", tulajdonosId);

        parent.appendChild(raktar);
    }

    private static void addEsemeny(Document doc, Element parent, String id, String nev, String datum, String helyszin, String leiras) {
        Element esemeny = doc.createElement("Esemeny");

        addChildElement(doc, esemeny, "esemeny_id", id);
        addChildElement(doc, esemeny, "esemeny_nev", nev);
        addChildElement(doc, esemeny, "datum", datum);
        addChildElement(doc, esemeny, "helyszin", helyszin);
        addChildElement(doc, esemeny, "leiras", leiras);

        parent.appendChild(esemeny);
    }

    private static void addBerles(Document doc, Element parent, String tranzakcioId, String felhasznaloId, String raktarId, String tipus, String kezdesDatum, String ar) {
        Element berles = doc.createElement("Berles");

        addChildElement(doc, berles, "tranzakcio_id", tranzakcioId);
        addChildElement(doc, berles, "felhasznalo_id", felhasznaloId);
        addChildElement(doc, berles, "raktar_id", raktarId);
        addChildElement(doc, berles, "tipus", tipus);
        addChildElement(doc, berles, "kezdes_datum", kezdesDatum);
        addChildElement(doc, berles, "ar", ar);

        parent.appendChild(berles);
    }

    private static void addKedvezmeny(Document doc, Element parent, String id, String felhasznaloId, String szazalek, String ervVege) {
        Element kedvezmeny = doc.createElement("Kedvezmeny");

        addChildElement(doc, kedvezmeny, "kedvezmeny_id", id);
        addChildElement(doc, kedvezmeny, "felhasznalo_id", felhasznaloId);
        addChildElement(doc, kedvezmeny, "kedvezmeny_szazalek", szazalek);
        addChildElement(doc, kedvezmeny, "ervenyesseg_vege", ervVege);

        parent.appendChild(kedvezmeny);
    }

    private static void addChildElement(Document doc, Element parent, String tagName, String textContent) {
        Element child = doc.createElement(tagName);
        child.appendChild(doc.createTextNode(textContent));
        parent.appendChild(child);
    }

    private static void writeDocumentToConsole(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
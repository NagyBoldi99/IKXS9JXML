package domIKXS9J1022;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;


public class DOMReadIKXS9J1 {


		    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
		        
		        // XML file megnyitása
		        File xmlFile = new File("IKXS9J_orarend.xml");

		        // példányosítás a DocumentBuilderFactory osztályt a statikus newInstance() metódussal.
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		        // A DocumentBuilderFactory-ból megkapjuk a DocumentBuildert.
		        DocumentBuilder dBuilder = factory.newDocumentBuilder();
		        // A DocumentBuilder tartalmazza az API-t a DOM-dokumentum példányok XML-dokumentumból való beszerzésére.

		        // DOM fa előállítása
		        Document ikxs9j = dBuilder.parse(xmlFile);
		        // A parse() metódus elemzi az XML fájlt a Document.

		        ikxs9j.getDocumentElement().normalize();
		        // A dokumentum normalizálása segít a helyes eredmények elérésében.
		        // eltávolítja az üres szövegcsómópontokat, és összekapcsolja a szomszédos szövegcsómópontokat.
		    	
			System.out.println("Gyökér elem: " + ikxs9j.getDocumentElement().getNodeName());
			//Kiíratjuk a dokumentum gyökérelemét
			
			//A fa megadott névvel (ora) rendelkező csomópontjainak összegyűjtése listába.
			//A getElementsByTagName() metódus segítségével megkapjuk a ora elem NodeListjét a dokumentumból.
			NodeList nList = ikxs9j.getElementsByTagName("ora"); // gyerekelemek mentése listába
			
			for (int i = 0; i < nList.getLength(); i++) {
			 // A listán for ciklussal megyünk végig.
			
			 // Lekérjük a lista aktuális elemét.
			 Node nNode = nList.item(i);
			
			 System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			     // Elementté konvertáljuk az aktuális elemet.
			     Element elem = (Element) nNode;
			
			     // Lekérjük az aktuális elem attribútumának tartalmát.
			  // Parsing the "ora" elements from the XML
			     String oid = elem.getAttribute("id");
			     // Az elem attribútumot a getAttribute() segítségével kapjuk meg.

			     // Lekérjük az aktuális elem gyerekelemeit és annak tartalmát.
			     Node node1 = elem.getElementsByTagName("targy").item(0);
			     String tname = ((Element)node1).getAttribute("nev");

			     Node node2 = elem.getElementsByTagName("oktato").item(0);
			     String oktatoNev = node2.getTextContent();

			     Node node3 = elem.getElementsByTagName("helyszin").item(0);
			     String helyszin = node3.getTextContent();

			     Node node4 = elem.getElementsByTagName("nap").item(0);
			     String nap = ((Element)node4).getAttribute("napn");

			     Node node5 = elem.getElementsByTagName("tol").item(0);
			     String tol = ((Element)node5).getAttribute("tolt");

			     Node node6 = elem.getElementsByTagName("ig").item(0);
			     String ig = ((Element)node6).getAttribute("igt");

			     // Megkapjuk a ora elem gyerekeinek tartalmát.

			     // Formázva kiíratjuk a lekért információkat az adott elemről
			     System.out.println("Óra id: " + oid);
			     System.out.println("Tárgy név: " + tname);
			     System.out.println("Oktató név: " + oktatoNev);
			     System.out.println("Helyszín: " + helyszin);
			     System.out.println("Nap: " + nap);
			     System.out.println("Tól: " + tol);
			     System.out.println("Ig: " + ig);

			 	}
			 }
		}
	 }
		    

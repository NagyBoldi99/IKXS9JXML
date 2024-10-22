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

public class DOMreadIKXS9J {

	    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
	        
	        // XML file megnyitása
	        File xmlFile = new File("hallgatoIKXS9J.xml");

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
		
		//A fa megadott névvel (hallgato) rendelkező csomópontjainak összegyűjtése listába.
		//A getElementsByTagName() metódus segítségével megkapjuk a hallgato elem NodeListjét a dokumentumból.
		NodeList nList = ikxs9j.getElementsByTagName("hallgato"); // gyerekelemek mentése listába
		
		for (int i = 0; i < nList.getLength(); i++) {
		 // A listán for ciklussal megyünk végig.
		
		 // Lekérjük a lista aktuális elemét.
		 Node nNode = nList.item(i);
		
		 System.out.println("\nAktuális elem: " + nNode.getNodeName());
		
		 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		     // Elementté konvertáljuk az aktuális elemet.
		     Element elem = (Element) nNode;
		
		     // Lekérjük az aktuális elem attribútumának tartalmát.
		     String hid = elem.getAttribute("id");
		     // Az elem attribútumot a getAttribute() segítségével kapjuk meg.
		
		     // Lekérjük az aktuális elem gyerekelemeit és annak tartalmát.
		     Node node1 = elem.getElementsByTagName("keresztnev").item(0);
		     String kname = node1.getTextContent();
		     Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
		     String vname = node2.getTextContent();

		     Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
		     String fname = node3.getTextContent();
		     // Megkapjuk a hallgato elem három gyerekelemének tartalmát.

		     // Formázva kiíratjuk a lekért információkat az adott elemről
		     System.out.println("Hallgató id: " + hid);
		     System.out.println("Keresztnév: " + kname);
		     System.out.println("Vezetéknév: " + vname);
		     System.out.println("Foglalkozás: " + fname);
		 	}
		 }
	}
 }
	    
	    

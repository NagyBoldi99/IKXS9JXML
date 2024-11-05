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
	        
	    
	        File xmlFile = new File("hallgatoIKXS9J.xml");


	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	      
	        DocumentBuilder dBuilder = factory.newDocumentBuilder();
	

	       
	        Document ikxs9j = dBuilder.parse(xmlFile);
	      

	        ikxs9j.getDocumentElement().normalize();
	      
	     
	    	
		System.out.println("Gyökér elem: " + ikxs9j.getDocumentElement().getNodeName());
	
		
		
		NodeList nList = ikxs9j.getElementsByTagName("hallgato"); 
		
		for (int i = 0; i < nList.getLength(); i++) {
	
		
		
		 Node nNode = nList.item(i);
		
		 System.out.println("\nAktuális elem: " + nNode.getNodeName());
		
		 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    
		     Element elem = (Element) nNode;
		
		    
		     String hid = elem.getAttribute("id");
		
		
		     
		     Node node1 = elem.getElementsByTagName("keresztnev").item(0);
		     String kname = node1.getTextContent();
		     Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
		     String vname = node2.getTextContent();

		     Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
		     String fname = node3.getTextContent();
		    

		   
		     System.out.println("Hallgató id: " + hid);
		     System.out.println("Keresztnév: " + kname);
		     System.out.println("Vezetéknév: " + vname);
		     System.out.println("Foglalkozás: " + fname);
		 	}
		 }
	}
 }
	    
	    

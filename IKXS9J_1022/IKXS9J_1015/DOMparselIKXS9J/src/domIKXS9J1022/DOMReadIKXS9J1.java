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
		        
		        File xmlFile = new File("IKXS9J_orarend.xml");

		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		        DocumentBuilder dBuilder = factory.newDocumentBuilder();

		        Document ikxs9j = dBuilder.parse(xmlFile);

		        ikxs9j.getDocumentElement().normalize();
		    	
			System.out.println("Gyökér elem: " + ikxs9j.getDocumentElement().getNodeName());
			
			NodeList nList = ikxs9j.getElementsByTagName("ora"); 
			
			for (int i = 0; i < nList.getLength(); i++) {
			
			 Node nNode = nList.item(i);
			
			 System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			     Element elem = (Element) nNode;
			
			     String oid = elem.getAttribute("id");

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
		    

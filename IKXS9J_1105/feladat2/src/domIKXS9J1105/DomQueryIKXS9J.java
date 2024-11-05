package domIKXS9J1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DomQueryIKXS9J {

    public static void main(String[] args) {
        try {
            // Load and parse the XML file
            File xmlFile = new File("IKXS9J_orarend.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // a) Query course names into a list and print it
            NodeList oraList = document.getElementsByTagName("ora");
            List<String> courseNames = new ArrayList<>();

            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                Element targy = (Element) ora.getElementsByTagName("targy").item(0);
                String nev = targy.getAttribute("nev");
                courseNames.add(nev);
            }

            System.out.println("Kurzusnevek: " + courseNames);

            // b) Query the first entry in orarendNeptunkod.xml and print it in a structured form
            if (oraList.getLength() > 0) {
                Element firstOra = (Element) oraList.item(0);
                System.out.println("Első órarend példány:");
                printStructuredElement(firstOra);
                saveElementToFile(firstOra, "orarend_elsoora.xml");
            }

            // c) Query instructors' names into a list and print it
            List<String> instructorNames = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                Element oktato = (Element) ora.getElementsByTagName("oktato").item(0);
                String instructorName = oktato.getAttribute("neve");
                instructorNames.add(instructorName);
            }

            System.out.println("Oktatók nevei: " + instructorNames);

            // d) Complex query: Retrieve all courses on "Hétfő" before 12:00
            System.out.println("\nKomplex lekérdezés - 'Hétfői' órák 12:00 előtt:");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                Element napElem = (Element) ora.getElementsByTagName("nap").item(0);
                Element tolElem = (Element) ora.getElementsByTagName("tol").item(0);

                if ("Hétfő".equals(napElem.getAttribute("napn")) && compareTime(tolElem.getAttribute("tolt"), "12:00") < 0) {
                    printStructuredElement(ora);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to print an XML element in a structured format with its actual text content
    private static void printStructuredElement(Element element) {
        System.out.println("<" + element.getTagName() + ">");
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) child;
                System.out.print("  <" + childElement.getTagName() + "> ");
                System.out.print(childElement.getTextContent().trim()); // Print actual content of the element
                System.out.println(" </" + childElement.getTagName() + ">");
            }
        }
        System.out.println("</" + element.getTagName() + ">");
    }

    // Method to save an XML element to a file
    private static void saveElementToFile(Element element, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("<" + element.getTagName() + ">\n");
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) child;
                    writer.write("  <" + childElement.getTagName() + "> " + childElement.getTextContent().trim() + " </" + childElement.getTagName() + ">\n");
                }
            }
            writer.write("</" + element.getTagName() + ">\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to compare two time strings in HH:mm format
    private static int compareTime(String time1, String time2) {
        return time1.compareTo(time2);
    }
}


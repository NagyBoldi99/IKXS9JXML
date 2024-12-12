package saxIKXS9J1203;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxIKXS9J {

	public static void main(String[] args) {
		
		try {
				
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			SaxHandler handler = new SaxHandler();

			saxParser.parse(new File("IKXS9J_kurzusfelvetel.xml"), handler);
 
		
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO: handle exception
		}
		
	}


private static class SaxHandler extends DefaultHandler {
	private int indent = 0;

	//tartalomkezel� keret
	
	// attrib�tumok form�z�sa {id=7, fk=12} form�ban
	private String formatAttributes(Attributes attributes) {
		int attributesLength = attributes.getLength();
		if (attributesLength == 0) {
			return "";
		}
		StringBuilder output = new StringBuilder(" {");
		for (int i = 0; i < attributesLength; i++) {
			output.append(attributes.getLocalName(i) + "=" + attributes.getValue(i));
			if (i < attributesLength - 1) {
				output.append(", ");
			}
		}
		output.append("}");
		return output.toString();
	}
	
	// bekezd�sek kit�tele
	private void indent() {
		System.out.print(" ".repeat(indent * 2));
	}
	
	//esem�nykezel�s
	
	// nyit�tag eset�n
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		indent();
		System.out.println(qName + formatAttributes(attributes) + " start");
		indent++;
	}

	// z�r�tag eset�n
	@Override
	public void endElement(String uri, String localName, String qName) {
		indent--;
		indent();
		System.out.println(qName + " end");
	}

	// sz�vegtartalom eset�nS
	@Override
	public void characters(char ch[], int start, int length) {
		String textContent = new String(ch, start, length).trim();
		if (!textContent.isEmpty()) {
			indent();
			System.out.println(textContent);
		}

	}

}

}

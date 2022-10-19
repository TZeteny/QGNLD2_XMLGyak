package SaxQGNLD21019;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import SaxQGNLD21019.SaxQGNLD2.SaxHandler;

public class test {
	
	public static void main(String[] args){
		try {
			SAXParserfactory saxparserfactory = SAXParserFactory.newInstance();
			
			SAXParserfactory saxparserfactory = SsaxParserFactory.newSAXParser();
			
			SaxHandler = new SaxHandler();
			
			SaxParser.parse( new File ("kurzusfelvetel.xml"), handler );
		}catch ( ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
	}
	
	class SaxHandler extends DefaultHandler {
		
		private int indent = 0;
		
		private String formatAttributes(Attributes attributes) {
			int attrLength = attributes.getLength();
			if (attrLength == 0) {
				return "";
			}
		
	}
	
}

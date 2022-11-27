package hu.domparse.QGNLD2;

import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadQGNLD2 {

	public static void main(String argv[]) throws SAXException, 
	IOException, ParserConfigurationException {
		//Elemek es gyerekelemeik.
		String[] csapatFields = {
				"nev",
				"varos",
				"mezszin"
		};
		
		String[] edzoFields = {
				"nev",
				"hajszin",
				"eletkor"	
		};
		
		String[] jatekosFields = {
				"nev",
				"mezszam",
				"hajszin"
		};
		
		String[] stadionFields = {
				"nev",
				"cim",
				"ferohelyek"
		};
		
		String[] meccsFields = {
				"gollovok",
				"eladott-jegyek",
				"golok-szama"
		};
		
		String[] jatszikFields = {
				"nyertes"
			};
		
		
		String[][] fields = {
				csapatFields, 
				edzoFields, 
				jatekosFields,
				stadionFields,
				meccsFields,
				jatszikFields
		};
		
		String [] subRoots = {
				"csapat",
				"edzo",
				"jatekos",
				"stadion",
				"meccs",
				"jatszik"
		};
		
		String [] idList = {
				"csapatID",
				"edzoID",
				"jatekosID",
				"stadionID",
				"meccsID",
				"jID"
		};
		
		//XML file megnyitasa.
		File xmlFile = new File("XMLQGNLD2.xml");
		//TXT file letrehozas, iras elokeszites.
		File txt = new File("DomRead.txt");
		FileWriter fw = new FileWriter(txt);
		PrintWriter pw = new PrintWriter(fw);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		//Txt-be is iras.
		pw.write( "Root element: " + doc.getDocumentElement().getNodeName() + "\n" );
		
		int index = 0;
		//Az xml-en vegigmenni.
		for(String element : subRoots) {
			NodeList nList = doc.getElementsByTagName(element);	
			for (int i = 0; i < nList.getLength(); i++){
				Node nNode = nList.item(i);
				//Uj elemnel irni konzolra es fajlba.
				System.out.println("\nCurrent Element: " + nNode.getNodeName());
				pw.write( "\nCurrent Element: " + nNode.getNodeName() + "\n");
				//A fieldek segitsegevel gyerekelemek kiirasa.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
					String uid = elem.getAttribute(idList[index]);
					System.out.println(idList[index] + ": " + uid);
					pw.write( idList[index] + ": " + uid  + "\n");
					for(String field : fields[index]){
						Node node = elem.getElementsByTagName(field).item(0);
						String data = node.getTextContent();
						System.out.println(field + ": " + data);
						pw.write( field + ": " + data + "\n");
					}
				}
			}
			index++;
		}
		//Fajl iro bezarasa.
		pw.close();
	}
}
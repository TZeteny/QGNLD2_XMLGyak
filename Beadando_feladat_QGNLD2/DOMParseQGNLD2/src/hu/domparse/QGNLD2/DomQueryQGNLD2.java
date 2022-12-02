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

public class DomQueryQGNLD2 {
	
	public static void main(String argv[]) throws SAXException, 
	IOException, ParserConfigurationException {
		
		File xmlFile = new File("XMLQGNLD2.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		String[] jatekosFields = {
				"nev",
				"mezszam",
				"hajszin"
		};
		
		//A file parse-olasa.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Edzok adatai (minusz az ID).
		System.out.println("Edzok adatai (minusz az ID)\n");
		
		NodeList edzoLista = doc.getElementsByTagName("edzo");
		//Forral vegig az edzokon.
		for(int i = 0; i < edzoLista.getLength(); i++) {
			Node e = edzoLista.item(i);
			if(e.getNodeType()==Node.ELEMENT_NODE) {
				Element edzo = (Element) e;
				NodeList nevList = edzo.getChildNodes();
				System.out.println("Edzo: ");
				//Az edzo gyerekelemein vegigmenni.
				for(int j = 0; j < nevList.getLength(); j++) {
					Node n = nevList.item(j);
					if (n.getNodeType()==Node.ELEMENT_NODE) {
						Element nev = (Element) n;
						System.out.println(nev.getTagName() + "= " + nev.getTextContent());
						
					}
				}
				System.out.println("");
			}
		}
		
		//12-es mezszamu jatekosok nevei.
		System.out.println("12-es mezszamu jatekosok nevei.\n");
		
		NodeList jatekosLista = doc.getElementsByTagName("jatekos");
		
		for(int i = 0; i < jatekosLista.getLength(); i++) {	
			Node jatekos = jatekosLista.item(i);	
			if(jatekos.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) jatekos;
				Node n = element.getElementsByTagName("mezszam").item(0);
				String mezszam = n.getTextContent();
				if("12".equals(mezszam)) {
					Node node = element.getElementsByTagName("nev").item(0);
					String nev = node.getTextContent();				
					System.out.println("Nev: " + nev);
				}
			}
		}
		System.out.println("");
		
		//10nel kisebb mezszamu jatekosok kiirasa.
		System.out.println("10nel kisebb mezszamu jatekosok kiirasa.\n");
		//jatekosokon vegigmenni.
		for(int i = 0; i < jatekosLista.getLength(); i++) {
			Node jatekos = jatekosLista.item(i);
			if(jatekos.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) jatekos;
				//mezszamok ellenorzese.
				Node n = element.getElementsByTagName("mezszam").item(0);
				String mezszam = n.getTextContent();
				//A mezszambol integer kinyerese.
				int szam = Integer.parseInt(mezszam);
				//Ha a mezszam kisebb, mint 10, akkor nev es mezszam kiirasa.
				if(szam < 10) {
					Node node = element.getElementsByTagName("nev").item(0);
					String nev = node.getTextContent();				
					System.out.println("Nev: " + nev);
					System.out.println("Mezszam: " + mezszam);
					System.out.println("");
				}
			}
		}
		
				//28 evnel idosebb edzok kiirasa.
				System.out.println("28 evnel idosebb edzok kiirasa.\n");
				//edzookon vegigmenni.
				for(int i = 0; i < edzoLista.getLength(); i++) {
					Node edzo = edzoLista.item(i);
					if(edzo.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) edzo;
						//kor ellenorzese.
						Node n = element.getElementsByTagName("eletkor").item(0);
						String eletkor = n.getTextContent();
						//A korbol integer kinyerese.
						int kor = Integer.parseInt(eletkor);
						//Ha a kor nagyobb, mint 28, akkor nev kiirasa.
						if(kor > 28) {
							Node node = element.getElementsByTagName("nev").item(0);
							String nev = node.getTextContent();				
							System.out.println("Nev: " + nev);
							System.out.println("eletkor: " + eletkor);
							System.out.println("");
						}
					}
				}
		
		//Egyes csapatban jatszo jatekosok.
		System.out.println("Egyes csapatban jatszo jatekosok.\n");
		
		//Vegigmenni a jetekosokon.
		for (int i = 0; i < jatekosLista.getLength(); i++){
			Node jatekos = jatekosLista.item(i);
			if (jatekos.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) jatekos;
				String jatekosID = element.getAttribute("jatekosID");
				//A csapat kulcs ellenorzese.
				String csapata = element.getAttribute("csapat-idegen-kulcs");
				if (!csapata.contains("c1")) {
					continue;
				}
				System.out.println("jatekosID" + ": " + jatekosID);
				//A jatekos mezovel a jatekos adatainak kiirasa.
				for(String field : jatekosFields){
					Node node = element.getElementsByTagName(field).item(0);
					String data = node.getTextContent();
					System.out.println(field + ": " + data);
				}
				System.out.println("");
			}
		}
		
		
		
		
	}

}



















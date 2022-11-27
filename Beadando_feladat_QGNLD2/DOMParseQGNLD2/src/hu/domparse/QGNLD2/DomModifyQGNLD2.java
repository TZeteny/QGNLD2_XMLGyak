package hu.domparse.QGNLD2;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DomModifyQGNLD2 {
	public static void main(String argv[]) throws ParserConfigurationException, 
	TransformerException, IOException, TransformerConfigurationException {
		try {
			File inputFile = new File("XMLQGNLD2.xml");
			
			 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	         Document doc = docBuilder.parse(inputFile);
	         NodeList edzoLista = doc.getElementsByTagName("edzo");
	         //Edzolistan vegigmenni.
	         for(int i = 0; i < edzoLista.getLength(); i++) {
	        	 Node edzo = doc.getElementsByTagName("edzo").item(i);
	        	 NamedNodeMap attr = edzo.getAttributes();
	        	 Node nodeAttr = attr.getNamedItem("edzoID");
	        	 //ID Változtatás
	        	 nodeAttr.setTextContent("e" + (i));
	        	 //Az edzo gyerekelemein vegigmenni
	        	 NodeList lista = edzo.getChildNodes(); 
	        	 for(int t = 0; t < lista.getLength(); t++) {
	        		 Node node = lista.item(t);
	        		 if (node.getNodeType() == Node.ELEMENT_NODE) {
	        			 Element element = (Element) node;
	        			 //Ha a hajszin elemre er...
	        			 if ("hajszin".equals(element.getNodeName())) {
	                            //es a hajszin erteke barna...
	                            if ("barna".equals(element.getTextContent())){
	                                //akkor voros lesz.
	                                element.setTextContent("voros");
	                            }
	                     }
	        		 }
	        		 
	        		 
	        	 }
	         }
	         
	         //Elso meccs torlese.
	         NodeList meccsLista = doc.getElementsByTagName("meccs");
	         
			 for(int i = 0; i < meccsLista.getLength(); i++) {
				 Node meccs = meccsLista.item(i);
				 Element element = (Element) meccs;
					 if(element.getAttribute("meccsID").equals("m1")) {
						 element.getParentNode().removeChild(element);
						 break;
					 }
			 }
			 
			 //Meccsekhez szabalytalansagok szama szama.			 
             for (int i = 0; i < meccsLista.getLength(); i++)
             {
                 Node meccs = meccsLista.item(i);
                 //szabalytalansagok elem letrehozasa
                 Element szabalytalansagok = doc.createElement("szabalytalansagok");
                 meccs.appendChild(szabalytalansagok);
                 //Minden meccsnek 4et allit be.
                 szabalytalansagok.appendChild(doc.createTextNode("4"));
                
             }
	         
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();

	         DOMSource source = new DOMSource(doc);

	         System.out.println("--Results--");
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
		}catch (Exception e){
            e.printStackTrace();
        }
		
	}
}
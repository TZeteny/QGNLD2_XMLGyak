package domQGNLD2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DomWriteQGNLD2 {
	public static void main(String[] args) throws ParserConfigurationException,
	TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("DOMQGNLD2", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Pal", "Kiss", "WebDeveloper"));
		root.appendChild(createUser(doc, "2", "Pul", "Ki", "WebDeveloper"));
		root.appendChild(createUser(doc, "3", "Pol", "Kiss", "WebDeloper"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutoutKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutoutKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource = new DOMSource(doc);
		
		File myFile = new File("user2.xml");
		
		StramResult console = new StreamResult(System.out);
		Streamresult file = new Streamresult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	private static Node createUser(Document doc, String id, String firstName, 
			String lastName, String profession) {
		
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "laststname", lastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
}





















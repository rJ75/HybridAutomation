package hybrid.datahandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//Class contains functions to parse XML files
public class XMLHandler {
	String strValue;
	HashMap<String, String> objHm = new HashMap<String, String>();

	public Document doc;

	public Element XMLParse(String Filename) {
		try {

			File xmlfile = new File(Filename);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = dBuilder.parse(xmlfile);
			Element xmlRoot = doc.getDocumentElement();
			return xmlRoot;
		} catch (Exception E) {
			E.printStackTrace();
			return null;
		}
	}

	public Document getDocumentObject(String XMLName) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(XMLName);

		} catch (Exception e) {
			System.out.println(e);
		}
		return doc;
	}

	// Function to read all values from XML file and return array list
	public ArrayList<HashMap<String, String>> getAllValues(String strXMLName, String strNodeName) {
		ArrayList<HashMap<String, String>> arrListValue = new ArrayList<HashMap<String, String>>();
		Document doc = getDocumentObject(strXMLName);
		NodeList nodelist = doc.getElementsByTagName(strNodeName);

		for (int intIndex = 0; intIndex < nodelist.getLength(); intIndex++) {
			HashMap<String, String> objHm = new HashMap<String, String>();
			Node node = nodelist.item(intIndex);
			NodeList childnodes = node.getChildNodes();

			for (int intValue = 0; intValue < childnodes.getLength(); intValue++) {
				Node node1 = childnodes.item(intValue);
				if (!(node1.getNodeName().equals("#text")))
					objHm.put(node1.getNodeName(), node1.getTextContent().trim());
			}

			arrListValue.add(objHm);
		}

		return arrListValue;
	}

//Function to read specific value matching the index from XML file and return the value	
	public HashMap<String, String> getValueByIndex(String strXMLName, String strNodeName, int intIndex) {
		ArrayList<HashMap<String, String>> arrListValue = new ArrayList<HashMap<String, String>>();
		Document doc = getDocumentObject(strXMLName);
		NodeList nodelist = doc.getElementsByTagName(strNodeName);

		for (int intValue = 0; intValue < nodelist.getLength(); intValue++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			Node node = nodelist.item(intValue);
			NodeList childnodes = node.getChildNodes();

			for (int intKey = 0; intKey < childnodes.getLength(); intKey++) {
				Node node1 = childnodes.item(intKey);
				if (!(node1.getNodeName().equals("#text")))
					hm.put(node1.getNodeName(), node1.getTextContent().trim());
			}
			arrListValue.add(hm);
		}
		if (intIndex > arrListValue.size() - 1) {
			System.out.println("Your index number for xml is greater than max available value");
		} else {
			objHm = arrListValue.get(intIndex);
			System.out.println("hash map" + objHm);
		}

		return objHm;
	}

//Function to read specific range of values from XML file and return the list		
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap<String, String>> getValuesByRange(String strXMLName, String strNodeName, int intStart,
			int intEnd) {
		ArrayList<HashMap<String, String>> arrListValue = new ArrayList<HashMap<String, String>>();
		Document doc = getDocumentObject(strXMLName);
		NodeList nodelist = doc.getElementsByTagName(strNodeName);
		int intSize = nodelist.getLength();
		HashMap<String, String>[] arrHashMap = new HashMap[intSize];

		for (int intIndex = 0; intIndex < nodelist.getLength(); intIndex++) {
			HashMap<String, String> objHm = new HashMap<String, String>();
			Node node = nodelist.item(intIndex);
			NodeList childnodes = node.getChildNodes();
			for (int intValue = 0; intValue < childnodes.getLength(); intValue++) {
				Node node1 = childnodes.item(intValue);
				if (!(node1.getNodeName().equals("#text")))
					objHm.put(node1.getNodeName(), node1.getTextContent().trim());
			}
			arrHashMap[intIndex] = objHm;
		}
		for (int intKey = intStart; intKey <= intEnd; intKey++) {
			arrListValue.add(arrHashMap[intKey]);
		}

		return arrListValue;
	}

}

package hybrid.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class HybridXMLReportWriter 
{

	DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder icBuilder;
        
    public void test1()
    {
    	try{
    	    icBuilder = icFactory.newDocumentBuilder();
    	    Document doc = icBuilder.newDocument();
    	    
    	    Element mainRootElement = doc.createElement("test_report");
            doc.appendChild(mainRootElement);
            
            // append info
            Element Info=doc.createElement("info");
            mainRootElement.appendChild(Info);
            Info.appendChild(getTestInfo(doc, "Failed", "12: AM", "150", "nison"));
    	    Info.appendChild(getDeviceInfo(doc, "adb-1235", "samsung", "ios 9.0", "9.1", "1246*1257"));
    	    
    	    // add reports
    	    Element rep=doc.createElement("reports");
            mainRootElement.appendChild(rep);
            
            rep.appendChild(getReport(doc, "1.jpg", "This is test", "false"));
            
            
         // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
          //  transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("E:\\file.xml"));
            transformer.transform(source, result);
 
            System.out.println("\nXML DOM Created Successfully..\nTestCase Completed.\n\n\n\n\n\n");
            
            
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
	 public static Node getReport(Document doc, String image, String line, String status) {
	        Element report = doc.createElement("report");
	        report.appendChild(getReportElements(doc, report, "imageFile", image));
	        report.appendChild(getReportElements(doc, report, "line", line));
	        report.appendChild(getReportElements(doc, report, "status", status));
	        return report;
	    }
	 
	 private static Node getReportElements(Document doc, Element element, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
	
	 public static Node getTestInfo(Document doc, String test_status, String run_started, String total_time, String user) {
	        Element report = doc.createElement("test_info");
	        report.appendChild(getReportElements(doc, report, "test_status", test_status));
	        report.appendChild(getReportElements(doc, report, "run_started", run_started));
	        report.appendChild(getReportElements(doc, report, "total_time", total_time));
	        report.appendChild(getReportElements(doc, report, "user", user));
	        return report;
	    }
	 public static Node getDeviceInfo(Document doc, String device_name, String manufacture, String os, String version,String screen_size) {
	        Element report = doc.createElement("device_info");
	        report.appendChild(getReportElements(doc, report, "device_name", device_name));
	        report.appendChild(getReportElements(doc, report, "manufacture", manufacture));
	        report.appendChild(getReportElements(doc, report, "os", os));
	        report.appendChild(getReportElements(doc, report, "version", version));
	        report.appendChild(getReportElements(doc, report, "screen_size", screen_size));
	        return report;
	    }
}

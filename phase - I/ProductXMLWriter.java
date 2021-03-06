import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//import com.datacontainers.Person;
import com.thoughtworks.xstream.XStream;

public class ProductXMLWriter {
	public void xmlConverter(ArrayList<Product> Products) {
		XStream xstream = new XStream();
        File xmlOutput = new File("Products.xml");
		
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("Product", Product.class); 
		for(Product aPerson : Products) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aPerson);
			xmlPrintWriter.write(personOutput);
		}
		xmlPrintWriter.close();	
	}
}
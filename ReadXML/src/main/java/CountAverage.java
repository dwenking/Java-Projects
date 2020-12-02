import java.io.IOException;
import java.text.DecimalFormat;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class CountAverage {
	public static void main(String[] a) throws SAXException, IOException, ParserConfigurationException
	{
		DecimalFormat df = new DecimalFormat("0.00");  
		Double res1=DomReader.recursiveTraverse(); //自上而下进行访问
		System.out.println(df.format(res1));
		Double res2=SAXReader.SAXRead(); //自上而下进行访问
		System.out.println(df.format(res2));
	}
}

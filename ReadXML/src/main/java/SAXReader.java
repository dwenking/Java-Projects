import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXReader {
	
	public static double SAXRead() throws SAXException, IOException, ParserConfigurationException {
		//法一 XMLReader parser = XMLReaderFactory.createXMLReader();
		
		
		//法二：
		//1、获取解析工厂   SAXParserFactory是protect类型，所以用他的静态方法
        SAXParserFactory  factory=SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse=factory.newSAXParser();
        //3、加载文档Document注册处理器
        //4、编写处理器
		Handler handler = new Handler();
		parse.parse("1.xml", handler);
	    List<Student> stu=handler.getContentList();     //得到一个List对象，里面包含Student类型的对象
	    HashSet<String> stu_count=new HashSet<String>();
	    Double total_scores=0.;
	    for(Student s: stu) {
	    	//System.out.println(s.getName()+" "+s.getScores());
	    	stu_count.add(s.getName());
	    	total_scores+=Double.valueOf(s.getScores());
	    }
	    return total_scores/stu_count.size();
	}
	
}

class Handler extends DefaultHandler {
	private List<Student> contentList;
	private String tag;
	private Student student;

	public List<Student> getContentList() {
		return contentList;
	}

	@Override
	// xml文档加载时
	public void startDocument() throws SAXException {
		System.out.println("Start parsing document...");
		contentList = new ArrayList<Student>();
	}

	@Override
	// 文档解析结束
	public void endDocument() throws SAXException {
		System.out.println("End");
	}
	
	@Override
	// 访问某一个元素
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		//System.out.println("开始一个元素"+qName);
		if(qName!=null) {
			tag=qName;
		}
		if (qName!=null&&qName.equals("student")) {
			student= new Student();
		}
	}

	@Override
	// 结束访问元素
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// End of processing current element
		if (qName.equals("student")) {
			this.contentList.add(student);
		}
		tag=null;
	}

	// 访问元素正文
	public void characters(char[] ch, int start, int length) {
		String content = new String(ch, start, length);
		if(tag!=null&&tag.equals("name")) {
			student.setName(content);
		}else if(tag!=null&&tag.equals("course")) {
			student.setSubject(content);
		}else if(tag!=null&&tag.equals("score")) {
			student.setScores(content);
		}
	}

}

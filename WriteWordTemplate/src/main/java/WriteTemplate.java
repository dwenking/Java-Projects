import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WriteTemplate {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		XWPFDocument doc = openDocx("template.docx");//导入模板文件
		Map<String, Object> params = new HashMap<String, Object>();//文字类 key-value
		Map<String,String> picParams = new HashMap<String,String>();//图片类 key-url
		
		params.put("$stuno$","123456789");
		params.put("$name$","Mike");
		
		/* test
		Iterator<String> it=params.keySet().iterator();
		while(it.hasNext()) {
			String key=it.next();
			System.out.println(key+" "+params.get(key));
		}
		*/
		
		String xuehao=params.get("$stuno$").toString();
		CreateBarcode.generateFile(xuehao,"Mike.png");
		
		picParams.put("$image1$", "Mike.png");
		
		List<IBodyElement> ibes = doc.getBodyElements();
		for (IBodyElement ib : ibes) {
			if (ib.getElementType() == BodyElementType.TABLE) {
				replaceTable(ib, params, picParams, doc);
			}
			if (ib.getElementType() == BodyElementType.PARAGRAPH)
				replaceRun(ib,picParams,doc);
		}
		
		writeDocx(doc, new FileOutputStream("res.docx"));//输出
		System.out.println("Successfully written!");
	}
	
	public static XWPFDocument openDocx(String url) {
		InputStream in = null;
		try {
			in = new FileInputStream(url);
			XWPFDocument doc = new XWPFDocument(in);
			return doc;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void writeDocx(XWPFDocument outDoc, OutputStream out) {
		try {
			outDoc.write(out);
			out.flush();
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Matcher matcher(String str) {
		//注意修改模式匹配串
		Pattern pattern = Pattern.compile("\\$\\$(.+?)\\$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}
	
	/**
	 * 写入image
	 * @param run
	 * @param imgFile
	 * @param doc
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void replacePic(XWPFRun run,  String imgFile,  XWPFDocument doc) throws Exception {
		int format;
		if (imgFile.endsWith(".emf"))
			format = Document.PICTURE_TYPE_EMF;
		else if (imgFile.endsWith(".wmf"))
			format = Document.PICTURE_TYPE_WMF;
		else if (imgFile.endsWith(".pict"))
			format = Document.PICTURE_TYPE_PICT;
		else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
			format = Document.PICTURE_TYPE_JPEG;
		else if (imgFile.endsWith(".png"))
			format = Document.PICTURE_TYPE_PNG;
		else if (imgFile.endsWith(".dib"))
			format = Document.PICTURE_TYPE_DIB;
		else if (imgFile.endsWith(".gif"))
			format = Document.PICTURE_TYPE_GIF;
		else if (imgFile.endsWith(".tiff"))
			format = Document.PICTURE_TYPE_TIFF;
		else if (imgFile.endsWith(".eps"))
			format = Document.PICTURE_TYPE_EPS;
		else if (imgFile.endsWith(".bmp"))
			format = Document.PICTURE_TYPE_BMP;
		else if (imgFile.endsWith(".wpg"))
			format = Document.PICTURE_TYPE_WPG;
		else {
			System.err.println(
					"Unsupported picture: " + imgFile + ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
			return;
		}
		if(imgFile.startsWith("http")||imgFile.startsWith("https")){
			run.addPicture(new URL(imgFile).openConnection().getInputStream(), format, "rpic",Units.toEMU(100),Units.toEMU(100));
		}else{
			run.addPicture(new FileInputStream(imgFile), format, "rpic",Units.toEMU(120),Units.toEMU(50));
		}
	}
	
	/**
	 * 替换表格内占位符
	 * @param para 表格对象
	 * @param params 文字替换map
	 * @param picParams 图片替换map
	 * @param indoc
	 * @throws Exception 
	 */
	public static void replaceTable(IBodyElement para ,Map<String, Object> params, 
			Map<String, String> picParams, XWPFDocument indoc)
			throws Exception {
		Matcher matcher;
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		table = (XWPFTable) para;
		rows = table.getRows();
		for (XWPFTableRow row : rows) {
			cells = row.getTableCells();
			int cellsize = cells.size();
			int cellcount = 0;
			for(cellcount = 0; cellcount<cellsize;cellcount++){
				XWPFTableCell cell = cells.get(cellcount);
				String runtext = "";
				List<XWPFParagraph> ps = cell.getParagraphs();
				for (XWPFParagraph p : ps) {
					for(XWPFRun run : p.getRuns()){
						runtext = run.text();
						//matcher = matcher(runtext);
						//if (matcher.find()) {
							if (picParams != null) {
								for (String pickey : picParams.keySet()) {
									if (runtext.equals(pickey)) {
										run.setText("",0);
										replacePic(run, picParams.get(pickey), indoc);
										//System.out.println("replace pic");
									}
								}
							}
							if (params != null) {
								for (String pickey : params.keySet()) {
									if (runtext.equals(pickey)) {
										run.setText(params.get(pickey)+"",0);
										//System.out.println("replace string");
									}
								}
							}
						//}
					}
				}
			}
		}
	}

	public static void replaceRun(IBodyElement para ,Map<String, String> picParams, XWPFDocument indoc)throws Exception {
		XWPFParagraph temp = (XWPFParagraph) para;
		for(XWPFRun run : temp.getRuns()){
			for (String pickey : picParams.keySet()) {
					run.setText("",0);
					replacePic(run, picParams.get(pickey), indoc);
			}
		}
	}
}

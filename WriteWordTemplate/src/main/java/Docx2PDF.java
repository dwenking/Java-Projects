import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;

/**
 * XDocReportTest 将docx文档转为pdf
 *  将得到的结果转化为pdf
 * @author Tom
 *
 */
public class Docx2PDF {

	public static void main(String[] args) throws Exception {
		XWPFDocument doc = new XWPFDocument(new FileInputStream("res.docx"));// docx
		PdfOptions options = PdfOptions.create();
		options.fontProvider(new IFontProvider() {
			// 设置中文字体
			public Font getFont(String familyName, String encoding, float size, int style, Color color) {
				try {
					BaseFont bfChinese = BaseFont.createFont(
							"C:\\Program Files (x86)\\Microsoft Office\\root\\VFS\\Fonts\\private\\STSONG.TTF",
							BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					Font fontChinese = new Font(bfChinese, size, style, color);
					if (familyName != null)
						fontChinese.setFamily(familyName);
					return fontChinese;
				} catch (Throwable e) {
					e.printStackTrace();
					return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
				}
			}
		});
		PdfConverter.getInstance().convert(doc, new FileOutputStream("res.pdf"), options);// pdf
	}
}

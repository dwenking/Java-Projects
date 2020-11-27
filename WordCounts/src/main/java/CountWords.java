import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CountWords {
	
	public static void main(String args[]) {
		String in="test.txt";
		String out="test1.txt";
		Map<String,Integer> count=new HashMap<String,Integer>();
		ArrayList<Word> res=new ArrayList<Word>();
		
		
		TxtFile txt_in=new TxtFile(in);
		TxtFile txt_out=new TxtFile(out);
		txt_out.clearFile();
		
		/*读取并处理*/
		txt_in.readFile(count);
		Iterator<String> it=count.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			Integer value=count.get(key);
			res.add(new Word(key,value));
		}
		Collections.sort(res);
		
		/*写新文件*/
		for(Word w:res) {
			String content=w.content+","+w.value.toString();
			txt_out.writeFile(content);
		}
	}
}

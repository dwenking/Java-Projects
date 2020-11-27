import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

class TxtFile{
	private String loc;
	
	public TxtFile() {
	}
	
	public TxtFile(String loc) {
		this.loc = loc;
	}
	
	/*追加写文件*/
	public void writeFile(String content) {
		//追加写：加上参数true，否则不加
		try(BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(loc,true)))){
			bw.write(content);
			bw.newLine();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*读文件并输出到命令行,并统计单词*/
	public void readFile(Map<String,Integer> tmp) {
		String line;
		try(BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(loc)))){
			while((line=in.readLine())!=null) {
			 	System.out.println(line);
			 	
			 	/*处理部分---------------------*/
			 	String[] words=line.split(";");
			 	if(words[0].charAt(0)=='#')continue;
			 	for(String s:words){
			 		if(tmp.containsKey(s)) {
			 			tmp.put(s, tmp.get(s)+1);
			 		}else {
			 			tmp.put(s,1);
			 		}
			 	}
			 	/*------------------------------*/
			 	
			}
		}catch(Exception ex) {
				ex.printStackTrace();
		}
	}
	
	public void clearFile() {
		File file =new File(loc);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
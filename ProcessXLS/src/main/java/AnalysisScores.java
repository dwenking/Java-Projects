import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalysisScores {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Student> s=new ArrayList<Student>();
		
		ReadWriteExcelFile.readXLSXFile("test.xlsx",s);
		Double total_scores=0.;
		int total_stu=0;
		
		for(Student stu:s) {
			if(stu.subject.equals("数学")) {
				total_stu++;
				total_scores+=stu.scores;
			}
		}
		System.out.println("人数："+total_stu);
		System.out.println("平均分："+total_scores/total_stu);
	}

}

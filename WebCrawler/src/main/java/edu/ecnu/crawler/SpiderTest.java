package edu.ecnu.crawler;

public class SpiderTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0;i<=100;i++) {
			Integer tmp=8604-i;
			String tmp_str=tmp.toString();
			String url="http://news.ecnu.edu.cn/cf/4c/c1833a11"+tmp_str+"/page.psp";
			//System.out.println(url);
			Spider s=new Spider();
			s.run(url);
			s.reSet();
		}
	}
}

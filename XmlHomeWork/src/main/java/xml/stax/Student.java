package xml.stax;

import java.util.List;

public class Student {
	private String name;
	private String subject;
	private String score;
	public Student() {
	}
	public Student(String name,String subject, String score) {
		this.name = name;
		this.subject=subject;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}

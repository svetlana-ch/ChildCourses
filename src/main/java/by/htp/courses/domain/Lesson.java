package by.htp.courses.domain;

import java.util.Date;
import java.util.List;

public class Lesson {
	
	private int id;
	private Date date;
	private Subject subject;
	private User teacher;
	private List<Child> group;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public List<Child> getGroup() {
		return group;
	}
	public void setGroup(List<Child> group) {
		this.group = group;
	}
	
	
}

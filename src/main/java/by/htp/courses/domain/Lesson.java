package by.htp.courses.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Lesson implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate date;
	private int subjectID;
	private int teacherID;
	private int groupID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date_lesson) {
		this.date = date_lesson;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + groupID;
		result = prime * result + id;
		result = prime * result + subjectID;
		result = prime * result + teacherID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (groupID != other.groupID)
			return false;
		if (id != other.id)
			return false;
		if (subjectID != other.subjectID)
			return false;
		if (teacherID != other.teacherID)
			return false;
		return true;
	}
	
	@Override

	public String toString() {
		return "Lesson [id=" + id + ", date=" + date + ", subjectID=" + subjectID + ", teacherID=" + teacherID + ", groupID="
				+ groupID + "]";
	}
	
}

package by.htp.courses.domain;

import java.io.Serializable;

public class Subject implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String subjectName;
	private int ageChildFrom;
	private int ageChildTo;
	private String sexChild;
	private String timeSpending;
	private int numberPerWeek;
	private boolean isNew;
	private double cost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getAgeChildFrom() {
		return ageChildFrom;
	}
	public void setAgeChildFrom(int ageChildFrom) {
		this.ageChildFrom = ageChildFrom;
	}
	public int getAgeChildTo() {
		return ageChildTo;
	}
	public void setAgeChildTo(int ageChildTo) {
		this.ageChildTo = ageChildTo;
	}
	public String getSexChild() {
		return sexChild;
	}
	public void setSexChild(String sexChild) {
		this.sexChild = sexChild;
	}
	public String getTimeSpending() {
		return timeSpending;
	}
	public void setTimeSpending(String timeSpending) {
		this.timeSpending = timeSpending;
	}
	public int getNumberPerWeek() {
		return numberPerWeek;
	}
	public void setNumberPerWeek(int numberPerWeek) {
		this.numberPerWeek = numberPerWeek;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageChildFrom;
		result = prime * result + ageChildTo;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + (isNew ? 1231 : 1237);
		result = prime * result + numberPerWeek;
		result = prime * result + ((sexChild == null) ? 0 : sexChild.hashCode());
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
		result = prime * result + ((timeSpending == null) ? 0 : timeSpending.hashCode());
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
		Subject other = (Subject) obj;
		if (ageChildFrom != other.ageChildFrom)
			return false;
		if (ageChildTo != other.ageChildTo)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (id != other.id)
			return false;
		if (isNew != other.isNew)
			return false;
		if (numberPerWeek != other.numberPerWeek)
			return false;
		if (sexChild == null) {
			if (other.sexChild != null)
				return false;
		} else if (!sexChild.equals(other.sexChild))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (timeSpending == null) {
			if (other.timeSpending != null)
				return false;
		} else if (!timeSpending.equals(other.timeSpending))
			return false;
		return true;
	}
	
	@Override


	public String toString() {

		return "User [id=" + id + ",  subjectName=" +  subjectName + ", ageChildFrom=" + ageChildFrom + ", ageChildTo=" + ageChildTo + ", sexChild="
				+ sexChild + ", timeSpending=" + timeSpending + ", numberPerWeek=" + numberPerWeek + ", cost=" + cost + "]";

	}
	

	

}

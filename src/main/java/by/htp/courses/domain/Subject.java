package by.htp.courses.domain;

public class Subject {	

	private int id;
	private String subjectName;
	private int ageChildFrom;
	private int ageChildTo;
	private String sexChild;
	private String timeSpending; // утро день вечер
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
	
	
	

	

}

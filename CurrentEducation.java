package com.example.education;

public class CurrentEducation {

	long id;
	String school_name, date_started, grad_date, degree_type;
	
	public void setId(long l){
		this.id = l;
	}
	public void setSchoolName(String school_name){
		this.school_name = school_name;
	}
	public void setDateStarted(String date_started){
		this.date_started = date_started;
	}
	public void setGradDate(String grad_date){
		this.grad_date = grad_date;
	}
	public void setDegreeType(String degree_type){
		this.degree_type = degree_type;
	}
	
	public long getId(){
		return this.id;
	}
	public String getSchoolName(){
		return this.school_name;
	}
	public String getDateStarted(){
		return this.date_started;
	}
	public String getGradDate(){
		return this.grad_date;
	}
	public String getDegreeType(){
		return this.degree_type;
	}
	
	
	
}

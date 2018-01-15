package com.example.education;

public class SchoolsApplied {
	private long appliedId;
	private String schoolname;
	private String date;
	private String outcome;
	
	public long getAppliedId(){
		return appliedId;
	}
	
	public void setAppliedId(long id){
		this.appliedId= id;
	}
	
	public String getAppliedName(){
		return schoolname;
	}
	
	public String getAppliedDate(){
		return date;
	}
	
	public void setAppliedName(String schoolname){
		this.schoolname = schoolname;
	}
	
	public void setAppliedDate(String date){
		this.date = date;
	}
	
	public void setAppliedOutcome(String outcome){
		this.outcome = outcome;
	}
	
	public String getAppliedOutcome(){
		
		return outcome;
		
	}
	
}

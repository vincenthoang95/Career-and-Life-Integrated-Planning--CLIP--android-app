package com.example.education;

public class StudyPlan {
	private long planId;
	private String plandate;
	private String plansubject;
	private String planmessage;
	private String plantime;
	
	public long getPlanId(){
		return planId;
	}
	public void setPlanId(long id){
		this.planId= id;
	}
	
	public String getPlanDate(){
		return plandate;
	}
	
	public String getPlanSubject(){
		return plansubject;
	}
	
	public void setPlanDate(String plandate){
		this.plandate = plandate;
	}
	
	public void setPlanSubject(String plansubject){
		this.plansubject = plansubject;
	}
	
	public void setPlanMessage(String planmessage){
		this.planmessage = planmessage;
	}
	
	public String getPlanMessage(){
		
		return planmessage;
		
	}
	
	public void setPlanTime(String plantime){
		this.plantime = plantime;
	}
	
	public String getPlanTime(){
		
		return plantime;
		
	}
	

	
	
}

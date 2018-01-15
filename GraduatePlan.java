package com.example.education;

public class GraduatePlan {
	private long gradId;
	private String gradname;
	private String gradlocation;
	private String graddegree;
	private String gradtime;
	
	public long getGradId(){
		return gradId;
	}
	public void setGradId(long id){
		this.gradId= id;
	}
	
	public String getGradName(){
		return gradname;
	}
	
	public String getGradLocation(){
		return gradlocation;
	}
	
	public void setGradName(String gradname){
		this.gradname = gradname;
	}
	
	public void setGradLocation(String gradlocation){
		this.gradlocation = gradlocation;
	}
	
	public void setGradDegree(String graddegree){
		this.graddegree = graddegree;
	}
	
	public String getGradDegree(){
		
		return graddegree;
		
	}
	
	public void setGradTime(String gradtime){
		this.gradtime = gradtime;
	}
	
	public String getGradTime(){
		
		return gradtime;
		
	}
	
	
}

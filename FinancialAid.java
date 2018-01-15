package com.example.education;

public class FinancialAid {
	private long aidId;
	private String aidtype;
	private String aidname;
	private String aidamount;
	
	public long getAidId(){
		return aidId;
	}
	public void setAidId(long id){
		this.aidId= id;
	}
	
	public String getAidName(){
		return aidname;
	}
	
	public String getAidType(){
		return aidtype;
	}
	
	public void setAidName(String aidname){
		this.aidname = aidname;
	}
	
	public void setAidType(String aidtype){
		this.aidtype = aidtype;
	}
	
	public void setAidAmount(String aidamount){
		this.aidamount = aidamount;
	}
	
	public String getAidAmount(){
		
		return aidamount;
		
	}

	
}

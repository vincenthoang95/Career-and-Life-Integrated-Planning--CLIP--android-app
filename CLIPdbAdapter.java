package com.example.education;

import java.util.ArrayList; 
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CLIPdbAdapter
{
	private SQLiteDatabase database;
	private CLIPdb CLIPdbHelper;
	private String[] currentColumns = 
		{ CLIPdbHelper.KEY_CURRENT_ID,CLIPdbHelper.KEY_SCHOOL_NAME, CLIPdbHelper.KEY_DATE_START, CLIPdbHelper.KEY_GRAD_DATE,
		  CLIPdbHelper.KEY_DEGREE_TYPE,
		};
	private String[] gradColumns = 
		{ CLIPdbHelper.KEY_GRAD_ID,CLIPdbHelper.KEY_GRAD_SCHOOL_NAME, CLIPdbHelper.KEY_GRAD_LOCATION, CLIPdbHelper.KEY_GRAD_DEGREE,
		  CLIPdbHelper.KEY_GRAD_TIME,
		};
	private String[] appliedColumns =
		{CLIPdbHelper.KEY_ID_APPLIED,CLIPdbHelper.KEY_APPLIED_NAME, CLIPdbHelper.KEY_APPLIED_DATE, CLIPdbHelper.KEY_APPLIED_OUTCOME};
	
	private String[] planColumns = 
		{ CLIPdbHelper.KEY_PLAN_ID,CLIPdbHelper.KEY_PLAN_DATE, CLIPdbHelper.KEY_PLAN_SUBJECT, CLIPdbHelper.KEY_PLAN_MESSAGE,
			  CLIPdbHelper.KEY_PLAN_TIME,
		};
	private String[] aidColumns = 
		{ CLIPdbHelper.KEY_AID_ID,CLIPdbHelper.KEY_AID_TYPE, CLIPdbHelper.KEY_AID_NAME, CLIPdbHelper.KEY_AID_AMOUNT,};
	public CLIPdbAdapter(Context context)
	{
		CLIPdbHelper = new CLIPdb(context);
	}
	
	public void open() throws SQLException
	{
		database = CLIPdbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		CLIPdbHelper.close();
	}

	public void createApplied(String name, String date, String outcome)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLIPdbHelper.KEY_APPLIED_NAME, name);
		values.put(CLIPdbHelper.KEY_APPLIED_DATE, date);
		values.put(CLIPdbHelper.KEY_APPLIED_OUTCOME, outcome);
		database.insert(CLIPdbHelper.APPLIED_TABLE_NAME, null, values);
		database.close();
	}
	
	public void deleteApplied(String appliedId)
	{
		database = CLIPdbHelper.getWritableDatabase();
		database.delete(CLIPdbHelper.APPLIED_TABLE_NAME, CLIPdbHelper.KEY_ID_APPLIED+ " = ?" , new String[]{appliedId});
		database.close();
	}
	
	public void updateApplied(String appliedId,String name, String date, String outcome)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("appliedname", name);
		values.put("applieddate", date);
		values.put("appliedoutcome", outcome);
		database.update(CLIPdbHelper.APPLIED_TABLE_NAME, values, CLIPdbHelper.KEY_ID_APPLIED + " = ?", new String[] {appliedId});
		database.close();
		
	}
	
	public SchoolsApplied getApplied(String appliedId)
	{
		database = CLIPdbHelper.getReadableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.APPLIED_TABLE_NAME, appliedColumns,CLIPdbHelper.KEY_ID_APPLIED + " = ?", new String[]{appliedId}, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		SchoolsApplied applied = new SchoolsApplied();
		applied.setAppliedId(cursor.getLong(0));
		applied.setAppliedName(cursor.getString(1));
		applied.setAppliedDate(cursor.getString(2));
		applied.setAppliedOutcome(cursor.getString(3));
		
		Log.d("getApplied("+appliedId+")", applied.toString());
		cursor.close();
		database.close();
		return applied;
	}
	
	public List<SchoolsApplied> getAllApplied()
	{
		List<SchoolsApplied> appliedList = new ArrayList<SchoolsApplied>();
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.APPLIED_TABLE_NAME, appliedColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			SchoolsApplied applied = cursorApplied(cursor);
			appliedList.add(applied);
			cursor.moveToNext();
		}
		
		cursor.close();
		database.close();
		return appliedList;
	}
	
	public Cursor getAllAppliedRows()
	{
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.APPLIED_TABLE_NAME, appliedColumns, null, null, null, null, null);
	    if (cursor != null) 
	    {
            cursor.moveToFirst();
        }
	    
	    database.close();
		return cursor;
	}

	private SchoolsApplied cursorApplied(Cursor cursor) {
		SchoolsApplied applied = new SchoolsApplied();
		applied.setAppliedId(cursor.getLong(0));
		applied.setAppliedName(cursor.getString(1));
		applied.setAppliedDate(cursor.getString(2));
		applied.setAppliedOutcome(cursor.getString(3));
		return applied;
	}
	
	void addCurrentEducation (String name, String date, String grad, String degree ){
		database = CLIPdbHelper.getWritableDatabase();
		//SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CLIPdbHelper.KEY_SCHOOL_NAME, name);
		values.put(CLIPdbHelper.KEY_DATE_START, date);
		values.put(CLIPdbHelper.KEY_GRAD_DATE, grad);
		values.put(CLIPdbHelper.KEY_DEGREE_TYPE, degree);
	
		database.insert(CLIPdbHelper.TABLE_NAME, null, values);
		database.close();
	
		
		
	}
	
	
	public CurrentEducation getCurrentEducation(String currentId){
		database = CLIPdbHelper.getReadableDatabase();
		
		Cursor cursor = database.query(CLIPdbHelper.TABLE_NAME,
				currentColumns, CLIPdbHelper.KEY_CURRENT_ID + " = ?",
				new String[]{currentId}, null, null, null,null);
		
		if(cursor != null){
			cursor.moveToFirst();
		}
		
		CurrentEducation currenteducation = new CurrentEducation();
		currenteducation.setId(cursor.getLong(0));
		currenteducation.setSchoolName(cursor.getString(1));
		currenteducation.setDateStarted(cursor.getString(2));
		currenteducation.setGradDate(cursor.getString(3));
		currenteducation.setDegreeType(cursor.getString(4));
		cursor.close();
		database.close();
		return currenteducation;
	}
	public List<CurrentEducation> getAllCurrentEducation()
	{
		List<CurrentEducation> currentList = new ArrayList<CurrentEducation>();
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.TABLE_NAME, currentColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			CurrentEducation currenteducation = cursorToCurrentEducation(cursor);
			currentList.add(currenteducation);
			cursor.moveToNext();
		}
		
		cursor.close();
		database.close();
		return currentList;
	}
	
	private CurrentEducation cursorToCurrentEducation(Cursor cursor) {
		CurrentEducation currenteducation = new CurrentEducation();
		currenteducation.setId(cursor.getLong(0));
		currenteducation.setSchoolName(cursor.getString(1));
		currenteducation.setDateStarted(cursor.getString(2));
		currenteducation.setGradDate(cursor.getString(3));
		currenteducation.setDegreeType(cursor.getString(4));
		return currenteducation;
	}

	public void updateCurrentEducation(String currentId, String name,String date, String grad, String degree  ){
		
		database = CLIPdbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(CLIPdbHelper.KEY_SCHOOL_NAME, name);
		values.put(CLIPdbHelper.KEY_DATE_START, date);
		values.put(CLIPdbHelper.KEY_GRAD_DATE, grad);
		values.put(CLIPdbHelper.KEY_DEGREE_TYPE, degree);
		
		database.update(CLIPdbHelper.TABLE_NAME, values,
				CLIPdbHelper.KEY_CURRENT_ID + " = ?",
				new String[]{currentId});
		database.close();
		
	}
	
	public Cursor getAllCurrRows()
	{
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.TABLE_NAME, currentColumns, null, null, null, null, null);
	    if (cursor != null) 
	    {
            cursor.moveToFirst();
        }
	    
	    database.close();
		return cursor;
	}
	
	public void createGrad(String name, String location, String type ,String time)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLIPdbHelper.KEY_GRAD_SCHOOL_NAME, name);
		values.put(CLIPdbHelper.KEY_GRAD_LOCATION, location);
		values.put(CLIPdbHelper.KEY_GRAD_DEGREE, type);
		values.put(CLIPdbHelper.KEY_GRAD_TIME, time);
		
		database.insert(CLIPdbHelper.GRAD_TABLE_NAME, null, values);
		database.close();
	}
	
	public void deleteGrad(String gradId)
	{
		database = CLIPdbHelper.getWritableDatabase();
		database.delete(CLIPdbHelper.GRAD_TABLE_NAME, CLIPdbHelper.KEY_GRAD_ID+ " = ?" , new String[]{gradId});
		database.close();
	}
	
	public void updateGrad(String gradId,String name, String location, String type ,String time)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("gradschoolname", name);
		values.put("gradschoollocation", location);
		values.put("graddegreetype", type);
		values.put("gradtime", time);
		database.update(CLIPdbHelper.GRAD_TABLE_NAME, values, CLIPdbHelper.KEY_GRAD_ID + " = ?", new String[] {gradId});
		database.close();
		
	}
	
	public GraduatePlan getGrad(String gradId)
	{
		database = CLIPdbHelper.getReadableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.GRAD_TABLE_NAME, gradColumns,CLIPdbHelper.KEY_GRAD_ID + " = ?", new String[]{gradId}, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		GraduatePlan grad = new GraduatePlan();
		grad.setGradId(cursor.getLong(0));
		grad.setGradName(cursor.getString(1));
		grad.setGradLocation(cursor.getString(2));
		grad.setGradDegree(cursor.getString(3));
		grad.setGradTime(cursor.getString(4));
		
		Log.d("getGrad("+gradId+")", grad.toString());
		cursor.close();
		database.close();
		return grad;
	}
	
	public List<GraduatePlan> getAllGrad()
	{
		List<GraduatePlan> gradList = new ArrayList<GraduatePlan>();
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.GRAD_TABLE_NAME, gradColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			GraduatePlan grad = cursorGrad(cursor);
			gradList.add(grad);
			cursor.moveToNext();
		}
		
		cursor.close();
		database.close();
		return gradList;
	}
	

	public Cursor getAllGradRows()
	{
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.GRAD_TABLE_NAME, gradColumns, null, null, null, null, null);
	    if (cursor != null) 
	    {
            cursor.moveToFirst();
        }
	    
	    database.close();
		return cursor;
	}

	private GraduatePlan cursorGrad(Cursor cursor) {
		GraduatePlan grad = new GraduatePlan();
		grad.setGradId(cursor.getLong(0));
		grad.setGradName(cursor.getString(1));
		grad.setGradLocation(cursor.getString(2));
		grad.setGradDegree(cursor.getString(3));
		grad.setGradTime(cursor.getString(4));
		return grad;
	}
	
	public void createPlan(String date, String subject, String message ,String time)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLIPdbHelper.KEY_PLAN_DATE, date);
		values.put(CLIPdbHelper.KEY_PLAN_SUBJECT, subject);
		values.put(CLIPdbHelper.KEY_PLAN_MESSAGE, message);
		values.put(CLIPdbHelper.KEY_PLAN_TIME, time);
		
		database.insert(CLIPdbHelper.PLAN_TABLE_NAME, null, values);
		database.close();
	}
	
	public void deletePlan(String planId)
	{
		database = CLIPdbHelper.getWritableDatabase();
		database.delete(CLIPdbHelper.PLAN_TABLE_NAME, CLIPdbHelper.KEY_PLAN_ID+ " = ?" , new String[]{planId});
		database.close();
	}
	
	public void updatePlan(String planId,String date, String subject, String message ,String time)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("plandate", date);
		values.put("plansubject", subject);
		values.put("planmessage", message);
		values.put("plantime", time);
		database.update(CLIPdbHelper.PLAN_TABLE_NAME, values, CLIPdbHelper.KEY_PLAN_ID + " = ?", new String[] {planId});
		database.close();
		
	}
	
	public StudyPlan getPlan(String planId)
	{
		database = CLIPdbHelper.getReadableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.PLAN_TABLE_NAME, planColumns,CLIPdbHelper.KEY_PLAN_ID + " = ?", new String[]{planId}, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		StudyPlan plan = new StudyPlan();
		plan.setPlanId(cursor.getLong(0));
		plan.setPlanDate(cursor.getString(1));
		plan.setPlanSubject(cursor.getString(2));
		plan.setPlanMessage(cursor.getString(3));
		plan.setPlanTime(cursor.getString(4));
		
		Log.d("getPlan("+planId+")", plan.toString());
		cursor.close();
		database.close();
		return plan;
	}
	
	public List<StudyPlan> getAllPlan()
	{
		List<StudyPlan> planList = new ArrayList<StudyPlan>();
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.PLAN_TABLE_NAME, planColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			StudyPlan plan = cursorPlan(cursor);
			planList.add(plan);
			cursor.moveToNext();
		}
		
		cursor.close();
		database.close();
		return planList;
	}
	

	public Cursor getAllPlanRows()
	{
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.PLAN_TABLE_NAME, planColumns, null, null, null, null, null);
	    if (cursor != null) 
	    {
            cursor.moveToFirst();
        }
	    
	    database.close();
		return cursor;
	}

	private StudyPlan cursorPlan(Cursor cursor) {
		StudyPlan plan = new StudyPlan();
		plan.setPlanId(cursor.getLong(0));
		plan.setPlanDate(cursor.getString(1));
		plan.setPlanSubject(cursor.getString(2));
		plan.setPlanMessage(cursor.getString(3));
		plan.setPlanTime(cursor.getString(4));
		return plan;
	}
	
	
	public void createAid(String type, String name, String amount)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLIPdbHelper.KEY_AID_TYPE, type);
		values.put(CLIPdbHelper.KEY_AID_NAME, name);
		values.put(CLIPdbHelper.KEY_AID_AMOUNT, amount);
		
		database.insert(CLIPdbHelper.AID_TABLE_NAME, null, values);
		database.close();
	}
	
	public void deleteAid(String aidId)
	{
		database = CLIPdbHelper.getWritableDatabase();
		database.delete(CLIPdbHelper.AID_TABLE_NAME, CLIPdbHelper.KEY_AID_ID+ " = ?" , new String[]{aidId});
		database.close();
	}
	
	public void updateAid(String aidId,String type, String name, String amount)
	{
		database = CLIPdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("aidtype", type);
		values.put("aidname", name);
		values.put("aidamount", amount);
		database.update(CLIPdbHelper.AID_TABLE_NAME, values, CLIPdbHelper.KEY_AID_ID + " = ?", new String[] {aidId});
		database.close();
		
	}
	
	public FinancialAid getAid(String aidId)
	{
		database = CLIPdbHelper.getReadableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.AID_TABLE_NAME, aidColumns,CLIPdbHelper.KEY_AID_ID + " = ?", new String[]{aidId}, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		FinancialAid aid = new FinancialAid();
		aid.setAidId(cursor.getLong(0));
		aid.setAidType(cursor.getString(1));
		aid.setAidName(cursor.getString(2));
		aid.setAidAmount(cursor.getString(3));
		
		cursor.close();
		database.close();
		return aid;
	}
	
	public List<FinancialAid> getAllAid()
	{
		List<FinancialAid> aidList = new ArrayList<FinancialAid>();
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.AID_TABLE_NAME, aidColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			FinancialAid aid = cursorAid(cursor);
			aidList.add(aid);
			cursor.moveToNext();
		}
		
		cursor.close();
		database.close();
		return aidList;
	}
	

	public Cursor getAllAidRows()
	{
		database = CLIPdbHelper.getWritableDatabase();
		Cursor cursor = database.query(CLIPdbHelper.AID_TABLE_NAME, aidColumns, null, null, null, null, null);
	    if (cursor != null) 
	    {
            cursor.moveToFirst();
        }
	    
	    database.close();
		return cursor;
	}

	private FinancialAid cursorAid(Cursor cursor) {
		FinancialAid aid = new FinancialAid();
		aid.setAidId(cursor.getLong(0));
		aid.setAidType(cursor.getString(1));
		aid.setAidName(cursor.getString(2));
		aid.setAidAmount(cursor.getString(3));
		return aid;
	}
	
}
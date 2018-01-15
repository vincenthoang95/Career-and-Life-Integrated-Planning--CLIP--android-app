package com.example.education;

import java.util.ArrayList; 
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CLIPdb extends SQLiteOpenHelper {
	public static final int database_version = 6;
	
	public static final String DATABASE_NAME = "education";
	
	public static final String TABLE_NAME = "currentEducation";
	public static final String APPLIED_TABLE_NAME = "appliedSchools";
	public static final String GRAD_TABLE_NAME = "gradPlans";
	public static final String PLAN_TABLE_NAME = "studyPlans";
	public static final String AID_TABLE_NAME = "financialAid";
	
	public static final String KEY_AID_ID = "_id";
	public static final String KEY_AID_TYPE = "aidtype";
	public static final String KEY_AID_NAME = "aidname";
	public static final String KEY_AID_AMOUNT = "aidamount";
	
	
	public static final String KEY_PLAN_ID = "_id";
	public static final String KEY_PLAN_DATE = "plandate";
	public static final String KEY_PLAN_SUBJECT = "plansubject";
	public static final String KEY_PLAN_MESSAGE = "planmessage";
	public static final String KEY_PLAN_TIME = "plantime";
	
	public static final String KEY_ID_APPLIED = "_id";
	public static final String KEY_APPLIED_NAME = "appliedname";
	public static final String KEY_APPLIED_DATE = "applieddate";
	public static final String KEY_APPLIED_OUTCOME ="appliedoutcome";
	
	
	public static final String KEY_CURRENT_ID = "_id";
	public static final String KEY_SCHOOL_NAME = "school_name";
	public static final String KEY_DATE_START = "date_start";
	public static final String KEY_GRAD_DATE = "grad_date";
	public static final String KEY_DEGREE_TYPE = "degree_type";
	
	public static final String KEY_GRAD_ID = "_id";
	public static final String KEY_GRAD_SCHOOL_NAME = "gradschoolname";
	public static final String KEY_GRAD_LOCATION = "gradschoollocation";
	public static final String KEY_GRAD_DEGREE = "graddegreetype";
	public static final String KEY_GRAD_TIME = "gradtime";
	
	public static final String CREATE_CURRENTEDU_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_CURRENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_SCHOOL_NAME + " TEXT NOT NULL, "
			+ KEY_DATE_START + " TEXT NOT NULL, "
			+ KEY_GRAD_DATE + " TEXT NOT NULL, "
            + KEY_DEGREE_TYPE + " TEXT NOT NULL " + ");";
	public static final String CREATE_APPLIED_TABLE = "CREATE TABLE " + APPLIED_TABLE_NAME + "("
            + KEY_ID_APPLIED + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_APPLIED_NAME + " TEXT NOT NULL, "
			+ KEY_APPLIED_DATE + " TEXT NOT NULL, " 
			+ KEY_APPLIED_OUTCOME + " TEXT NOT NULL "+ ");";
	
	public static final String CREATE_GRAD_TABLE = "CREATE TABLE " + GRAD_TABLE_NAME + "("
            + KEY_GRAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_GRAD_SCHOOL_NAME + " TEXT NOT NULL, "
			+ KEY_GRAD_LOCATION + " TEXT NOT NULL, " 
			+ KEY_GRAD_DEGREE + " TEXT NOT NULL, "
			+ KEY_GRAD_TIME + " TEXT NOT NULL "+ ");";
	public static final String CREATE_PLAN_TABLE = "CREATE TABLE " + PLAN_TABLE_NAME + "("
            + KEY_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_PLAN_DATE + " TEXT NOT NULL, "
			+ KEY_PLAN_SUBJECT + " TEXT NOT NULL, " 
			+ KEY_PLAN_MESSAGE + " TEXT NOT NULL, "
			+ KEY_PLAN_TIME + " TEXT NOT NULL "+ ");";
	public static final String CREATE_AID_TABLE = "CREATE TABLE " + AID_TABLE_NAME + "("
            + KEY_AID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_AID_TYPE + " TEXT NOT NULL, "
			+ KEY_AID_NAME + " TEXT NOT NULL, " 
			+ KEY_AID_AMOUNT + " TEXT NOT NULL "+ ");";
	
	public CLIPdb(Context context) {
		super(context, DATABASE_NAME, null, database_version);
		Log.d("Database operations", "Database created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_CURRENTEDU_TABLE);
		database.execSQL(CREATE_APPLIED_TABLE);
		database.execSQL(CREATE_GRAD_TABLE);
		database.execSQL(CREATE_PLAN_TABLE);
		database.execSQL(CREATE_AID_TABLE);
		Log.d("Database operations", "Table created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.w(CLIPdb.class.getName(), 
				"Upgrading the database from version " + oldVersion + " to " 
					+ newVersion + ", which will destroy all old data");
		
		// clear all data
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + APPLIED_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + GRAD_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + PLAN_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + AID_TABLE_NAME);
		
		// recreate the tables
		onCreate(db);
	}
}

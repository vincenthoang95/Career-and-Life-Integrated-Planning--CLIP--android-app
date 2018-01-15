package com.example.education;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Current_Education extends ActionBarActivity {
	Button button;
	TextView SCHOOL_NAME, DATE_STARTED, GRAD_DATE, DEGREE_TYPE;
	String school_name, date_started, grad_date, degree_type;
	Context CTX = this;
	Current_Education current;
	CLIPdbAdapter database;
	String currentId;
	List<CurrentEducation> allCurrent = new ArrayList<CurrentEducation>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current__education);
		
		database = new CLIPdbAdapter(this);
		current =new Current_Education();
		database.open();
		SCHOOL_NAME = (TextView) findViewById(R.id.viewCurrentName);
		DATE_STARTED = (TextView) findViewById(R.id.viewCurrentStart);
		GRAD_DATE = (TextView) findViewById(R.id.viewCurrentGrad);
		DEGREE_TYPE = (TextView) findViewById(R.id.viewCurrentDegree);
		
		Intent i = getIntent();
		currentId = i.getStringExtra("currentId");
		CurrentEducation currenteducation = database.getCurrentEducation("1");
		if(currenteducation != null){
			SCHOOL_NAME.setText(currenteducation.getSchoolName());
			DATE_STARTED.setText(currenteducation.getDateStarted());
			GRAD_DATE.setText(currenteducation.getGradDate());
			DEGREE_TYPE.setText(currenteducation.getDegreeType());

			
		}
		button = (Button) findViewById(R.id.buttonEditCurrentEducation);
        button.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				
				
				Intent i = new Intent(Current_Education.this,Edit_Current_Education.class);
				startActivity(i);
			//	finish();
	         }
		});
	}
	/*
	 public void onBackPressed() {
		   Intent intent = new Intent(Intent.ACTION_MAIN);
		   intent.addCategory(Intent.CATEGORY_HOME);
		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   startActivity(intent);
		 }
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.current__education, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed() {
	    //your code when back button pressed
		Intent i = new Intent(Current_Education.this, Education.class);
		startActivity(i);
	}
}

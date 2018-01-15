package com.example.education;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edit_Current_Education extends ActionBarActivity {
	Button button;
	EditText SCHOOL_NAME, DATE_STARTED, GRAD_DATE, DEGREE_TYPE;
	String school_name, date_started, grad_date, degree_type;
	private CLIPdbAdapter database;
	private Current_Education currenteducation;
	String currentId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__current__education);
		
		database = new CLIPdbAdapter(this);
		currenteducation = new Current_Education();
		database.open();
		
		button = (Button) findViewById(R.id.buttonSaveCurrentEducation);
		SCHOOL_NAME = (EditText) findViewById(R.id.schoolname);
		DATE_STARTED = (EditText) findViewById(R.id.addPlanSubject);
		GRAD_DATE = (EditText) findViewById(R.id.addPlanMessage);
		DEGREE_TYPE = (EditText) findViewById(R.id.studytime);
		
		Intent i = getIntent();
		currentId = i.getStringExtra("currentId");
		CurrentEducation currenteducation = database.getCurrentEducation("1");
		if(currenteducation != null){
			SCHOOL_NAME.setText(currenteducation.getSchoolName());
			DATE_STARTED.setText(currenteducation.getDateStarted());
			GRAD_DATE.setText(currenteducation.getGradDate());
			DEGREE_TYPE.setText(currenteducation.getDegreeType());

			
		}
        button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				school_name = SCHOOL_NAME.getText().toString();
				date_started = DATE_STARTED.getText().toString();
				grad_date = GRAD_DATE.getText().toString();
				degree_type = DEGREE_TYPE.getText().toString();
				
				database.updateCurrentEducation("1", school_name, date_started, grad_date, degree_type);
				Intent i = new Intent(Edit_Current_Education.this, Current_Education.class);
				startActivity(i);
			//	finish();
	         }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit__current__education, menu);
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
}

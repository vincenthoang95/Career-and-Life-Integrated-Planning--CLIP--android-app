package com.example.education;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Current_Education extends ActionBarActivity {
	EditText school_name, start_date, grad_date, degree_type;
	Button save;
	CLIPdbAdapter database;
	Current_Education currenteducation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__current__education);
		
		database =  new CLIPdbAdapter(this);
		currenteducation = new Current_Education();
		database.open();
		
		school_name = (EditText) findViewById(R.id.addgradName);
		start_date = (EditText) findViewById(R.id.currentDate);
		grad_date = (EditText) findViewById(R.id.addgradlocation);
		degree_type = (EditText) findViewById(R.id.currentDegree);
		
		save = (Button) findViewById(R.id.addGradSave);
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String name = school_name.getText().toString();
				String sdate = start_date.getText().toString();
				String gdate = grad_date.getText().toString();
				String dtype = degree_type.getText().toString();
				
				database.addCurrentEducation(name,sdate,gdate,dtype);
	
				Intent i = new Intent(Add_Current_Education.this, Current_Education.class);
				startActivity(i);
			
			}
			
		});
		

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__current__education, menu);
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

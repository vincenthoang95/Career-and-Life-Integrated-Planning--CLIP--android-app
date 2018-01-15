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

public class Edit_Schools_Applied extends ActionBarActivity {
	private CLIPdbAdapter database;
	private Schools_Applied viewApplied;
	private EditText schoolname = null;
	private EditText date = null;
	private EditText outcome = null;
	private Button edit;
	private Button delete;
	private String appliedId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__schools__applied);
		database = new CLIPdbAdapter(this);
		viewApplied = new Schools_Applied();
		database.open();
		
		schoolname = (EditText) findViewById(R.id.editAppliedName);
		date = (EditText) findViewById(R.id.editApplieddate);
		outcome = (EditText) findViewById(R.id.addgradlocation);
		edit = (Button) findViewById(R.id.appliedEdit);
		delete = (Button) findViewById(R.id.appliedDelete);
		
		Intent i = getIntent();
		appliedId = i.getStringExtra("appliedId");
		SchoolsApplied schoolsapplied = database.getApplied(appliedId);
		if(schoolsapplied != null){
			schoolname.setText(schoolsapplied.getAppliedName());
			date.setText(schoolsapplied.getAppliedDate());
			outcome.setText(schoolsapplied.getAppliedOutcome());
		}
		edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String userAppliedName = schoolname.getText().toString();
				String userAppliedDate = date.getText().toString();
				String userAppliedOutcome = outcome.getText().toString();
				
				database.updateApplied(appliedId, userAppliedName, userAppliedDate, userAppliedOutcome);
				Intent intent = new Intent (Edit_Schools_Applied.this, Schools_Applied.class);
				startActivity(intent);
			}
			
		});
		delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				database.deleteApplied(appliedId);
				Intent intent = new Intent (Edit_Schools_Applied.this, Schools_Applied.class);
				startActivity(intent);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit__schools__applied, menu);
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

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

public class Add_Future_Study_Plans extends ActionBarActivity {
	private EditText plandate = null,
			plansubject = null,
			planmessage = null,
			plantime = null;
			
	private CLIPdbAdapter database;
	private Button SAVE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__future__study__plans);
		
		database = new CLIPdbAdapter(this);
		database.open();
		
		plandate= (EditText) findViewById(R.id.addPlanDate);
		plansubject = (EditText) findViewById(R.id.addPlanSubject);
		planmessage =(EditText) findViewById(R.id.addPlanMessage);
		plantime =(EditText) findViewById(R.id.addPlanTime);
		
		SAVE = (Button) findViewById(R.id.addGradSave);
		SAVE.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v) 
		{
			String text1=plandate.getText().toString();
			String text2=plansubject.getText().toString();
			String text3 = planmessage.getText().toString();
			String text4 = plantime.getText().toString();
			database.createPlan(text1,text2,text3,text4);
			Toast.makeText(Add_Future_Study_Plans.this, "New Study Plan Added!", Toast.LENGTH_LONG).show();
			Intent i = new Intent(Add_Future_Study_Plans.this, Future_Study_Plans.class);
			startActivity(i);
			//finish();
		}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__future__study__plans, menu);
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

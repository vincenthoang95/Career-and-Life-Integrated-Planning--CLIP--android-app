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

public class Edit_Study_Plan extends ActionBarActivity {
	private CLIPdbAdapter database;
	private Future_Study_Plans viewPlan;
	private EditText plandate = null,
			plansubject = null,
			planmessage = null,
			plantime = null;
	private Button edit;
	private Button delete;
	private String planId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__study__plan);
		
		database = new CLIPdbAdapter(this);
		viewPlan = new Future_Study_Plans();
		database.open();
		
		plandate = (EditText) findViewById(R.id.editPlanDate);
		plansubject = (EditText) findViewById(R.id.editPlanSubject);
		planmessage = (EditText) findViewById(R.id.editPlanMessage);
		plantime = (EditText) findViewById(R.id.editPlanTime);
		edit = (Button) findViewById(R.id.editPlanButton);
		delete = (Button) findViewById(R.id.deletePlanButton);
		
		Intent i = getIntent();
		planId = i.getStringExtra("planId");
		StudyPlan plan = database.getPlan(planId);
		if(plan != null){
			plandate.setText(plan.getPlanDate());
			plansubject.setText(plan.getPlanSubject());
			planmessage.setText(plan.getPlanMessage());
			plantime.setText(plan.getPlanTime());
		}
		edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String text1 = plandate.getText().toString();
				String text2 = plansubject.getText().toString();
				String text3 = planmessage.getText().toString();
				String text4 = plantime.getText().toString();
				
				database.updatePlan(planId, text1,text2,text3,text4);
				Intent intent = new Intent (Edit_Study_Plan.this, Future_Study_Plans.class);
				startActivity(intent);
				//finish();
			}
			
		});
		delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				database.deletePlan(planId);
				Intent intent = new Intent (Edit_Study_Plan.this, Future_Study_Plans.class);
				startActivity(intent);
				//finish();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit__study__plan, menu);
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

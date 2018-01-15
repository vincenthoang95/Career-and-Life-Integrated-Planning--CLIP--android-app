package com.example.education;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Future_Study_Plans extends ActionBarActivity {
	private Button button;
	
	private TextView plan;
	CLIPdbAdapter database;
	CLIPdb CLIPdbHelper;
	List<StudyPlan> planList =new ArrayList<StudyPlan>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_future__study__plans);
		
		database =  new CLIPdbAdapter(this);
		database.open();
		planList = database.getAllPlan();
		
		if (planList.size() != 0)
		{
			ListView listView = (ListView) findViewById(R.id.viewPlan);
			
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					plan = (TextView) view.findViewById(R.id.planId);
					String planId = plan.getText().toString();
					Intent i = new Intent(getApplication(), Edit_Study_Plan.class);
					i.putExtra("planId", planId);
					startActivity(i);
				}
			}); 
			
			Cursor cursor = database.getAllPlanRows();
			String[] fromFields = new String[]{CLIPdb.KEY_PLAN_ID, CLIPdb.KEY_PLAN_DATE, CLIPdb.KEY_PLAN_SUBJECT};
			int[] toViewFields = new int[] {R.id.planId, R.id.showplanDate ,R.id.showplanSubject};
			SimpleCursorAdapter Adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.study_plans_entry, cursor, fromFields, toViewFields, 0);
			listView.setAdapter(Adapter); 
			
		} 
		
		button = (Button) findViewById(R.id.buttonAddStudyPlans);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Future_Study_Plans.this, Add_Future_Study_Plans.class);
				startActivity(i);
			//	finish();
	         }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.future__study__plans, menu);
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
		Intent i = new Intent(Future_Study_Plans.this, Education.class);
		startActivity(i);
	}
}

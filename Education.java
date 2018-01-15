package com.example.education;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Education extends ActionBarActivity {
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private CLIPdbAdapter database;
	private CLIPdb CLIPdbHelper;
	List<CurrentEducation> currentList = new ArrayList<CurrentEducation>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_education);
		
		button1 = (Button) findViewById(R.id.buttonCurrentEducation);
		button2 = (Button) findViewById(R.id.buttonGraduatePlans);
		button3 = (Button) findViewById(R.id.buttonSchoolsApplied);
		button4 = (Button) findViewById(R.id.buttonFinancialAid);
		button5 = (Button) findViewById(R.id.buttonStudyPlans);
		database = new CLIPdbAdapter(this);
		database.open();
		currentList = database.getAllCurrentEducation();
        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(currentList.size() == 0){
					Intent i = new Intent(Education.this, Add_Current_Education.class);
					startActivity(i);
				}
				else{
					Intent i = new Intent(Education.this, Current_Education.class);
					startActivity(i);
					//	finish();
				}
	         }
		});
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Education.this, Graduate_Plans.class);
				startActivity(i);
			//	finish();
	         }
		});
        button3.setOnClickListener(new OnClickListener() {
	
        	public void onClick(View v) {
        		Intent i = new Intent(Education.this, Schools_Applied.class);
        		startActivity(i);
        		//	finish();
        	}
        });
        button4.setOnClickListener(new OnClickListener() {
	
        	public void onClick(View v) {
        		Intent i = new Intent(Education.this, Financial_Aid.class);
        		startActivity(i);
        		//	finish();
        	}
        });

        button5.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Education.this, Future_Study_Plans.class);
				startActivity(i);
			//	finish();
	         }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.education, menu);
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
		   Intent intent = new Intent(Intent.ACTION_MAIN);
		   intent.addCategory(Intent.CATEGORY_HOME);
		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   startActivity(intent);
	 }
}

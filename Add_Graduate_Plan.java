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

public class Add_Graduate_Plan extends ActionBarActivity {
	private EditText gradname = null,
			gradlocation = null,
			graddegree = null,
			gradtime = null;
			
	private CLIPdbAdapter database;
	private Button SAVE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__graduate__plan);
		
		database = new CLIPdbAdapter(this);
		database.open();
		
		gradname= (EditText) findViewById(R.id.addgradName);
		gradlocation = (EditText) findViewById(R.id.addgradlocation);
		graddegree =(EditText) findViewById(R.id.addgradDegree);
		gradtime =(EditText) findViewById(R.id.editgradTime);
		SAVE = (Button) findViewById(R.id.addGradSave);
		SAVE.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v) 
		{
			String text1=gradname.getText().toString();
			String text2=gradlocation.getText().toString();
			String text3 = graddegree.getText().toString();
			String text4 = gradtime.getText().toString();
			database.createGrad(text1,text2,text3,text4);
			Toast.makeText(Add_Graduate_Plan.this, "New Graduate Plan Added!", Toast.LENGTH_LONG).show();
			Intent i = new Intent(Add_Graduate_Plan.this, Graduate_Plans.class);
			startActivity(i);
			//finish();
		}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__graduate__plan, menu);
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

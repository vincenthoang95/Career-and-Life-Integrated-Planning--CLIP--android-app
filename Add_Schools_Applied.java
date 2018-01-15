package com.example.education;

import java.util.HashMap;

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

public class Add_Schools_Applied extends ActionBarActivity {
	
	private EditText schoolname = null, dateapplied = null, outcome = null;
	private CLIPdbAdapter database;
	Schools_Applied Schools_Applied;
	private Button SAVE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__schools__applied);

		database = new CLIPdbAdapter(this);
		database.open();
		
		schoolname= (EditText) findViewById(R.id.appliedschoolname);
		dateapplied = (EditText) findViewById(R.id.aplieddate);
		outcome =(EditText) findViewById(R.id.addgradName);
		SAVE = (Button) findViewById(R.id.addGradSave);
		
		SAVE.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v) 
		{
			String userschoolname=schoolname.getText().toString();
			String userdateapplied=dateapplied.getText().toString();
			String useroutcome = outcome.getText().toString();
			database.createApplied(userschoolname, userdateapplied,useroutcome);
			Toast.makeText(Add_Schools_Applied.this, "New School Applied Added!", Toast.LENGTH_LONG).show();
			Intent i = new Intent(Add_Schools_Applied.this, Schools_Applied.class);
			startActivity(i);
		}
		});
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__schools__applied, menu);
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

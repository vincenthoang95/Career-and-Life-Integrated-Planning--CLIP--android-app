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

public class Add_Financial_Aid extends ActionBarActivity {
	private EditText aidtype = null,
			aidname = null,
			aidamount = null;
			
	private CLIPdbAdapter database;
	private Button SAVE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__financial__aid);
		
		database = new CLIPdbAdapter(this);
		database.open();
		
		aidtype= (EditText) findViewById(R.id.addAidType);
		aidname = (EditText) findViewById(R.id.addAidName);
		aidamount =(EditText) findViewById(R.id.addAidAmount);
		
		SAVE = (Button) findViewById(R.id.AidSaveButton);
		SAVE.setOnClickListener(new OnClickListener()
		{
		public void onClick(View v) 
		{
			String text1=aidtype.getText().toString();
			String text2=aidname.getText().toString();
			String text3 = aidamount.getText().toString();
			
			database.createAid(text1,text2,text3);
			Toast.makeText(Add_Financial_Aid.this, "New Financial Aid Added!", Toast.LENGTH_LONG).show();
			Intent i = new Intent(Add_Financial_Aid.this, Financial_Aid.class);
			startActivity(i);
			//finish();
		}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__financial__aid, menu);
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

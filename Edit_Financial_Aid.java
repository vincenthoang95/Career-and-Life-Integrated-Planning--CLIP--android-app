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

public class Edit_Financial_Aid extends ActionBarActivity {
	private EditText aidtype = null,
			aidname = null,
			aidamount = null;
	private Financial_Aid viewAid;
	private CLIPdbAdapter database;
	
	private Button edit;
	private Button delete;
	private String aidId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__financial__aid);
		
		database = new CLIPdbAdapter(this);
		viewAid = new Financial_Aid();
		database.open();
		
		aidtype = (EditText) findViewById(R.id.editAidType);
		aidname = (EditText) findViewById(R.id.editAidName);
		aidamount = (EditText) findViewById(R.id.editAidAmount);
	
		edit = (Button) findViewById(R.id.editAidButton);
		delete = (Button) findViewById(R.id.deleteAidButton);
		
		Intent i = getIntent();
		aidId = i.getStringExtra("aidId");
		FinancialAid aid = database.getAid(aidId);
		if(aid != null){
			aidtype.setText(aid.getAidType());
			aidname.setText(aid.getAidName());
			aidamount.setText(aid.getAidAmount());
			
		}
		edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String text1 = aidtype.getText().toString();
				String text2 = aidname.getText().toString();
				String text3 = aidamount.getText().toString();
				
				database.updateAid(aidId, text1,text2,text3);
				Intent intent = new Intent (Edit_Financial_Aid.this, Financial_Aid.class);
				startActivity(intent);
				//finish();
			}
			
		});
		delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				database.deleteAid(aidId);
				Intent intent = new Intent (Edit_Financial_Aid.this, Financial_Aid.class);
				startActivity(intent);
				//finish();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit__financial__aid, menu);
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

package com.example.education;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity; 
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Schools_Applied extends ActionBarActivity {
	private Button addSchoolApplied;
	
	private TextView applied;
	
	CLIPdbAdapter database;
	CLIPdb CLIPdbHelper;
	List<SchoolsApplied> appliedList =new ArrayList<SchoolsApplied>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schools__applied);
		
		database =  new CLIPdbAdapter(this);
		database.open();
		appliedList = database.getAllApplied();
		
		if (appliedList.size() != 0)
		{
			ListView listView = (ListView) findViewById(R.id.mylistapplied);
			
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					applied = (TextView) view.findViewById(R.id.appliedId);
					String appliedId = applied.getText().toString();
					Intent i = new Intent(getApplication(), Edit_Schools_Applied.class);
					i.putExtra("appliedId", appliedId);
					startActivity(i);
				}
			}); 
			
			Cursor cursor = database.getAllAppliedRows();
			String[] fromAppliedFields = new String[]{CLIPdb.KEY_ID_APPLIED, CLIPdb.KEY_APPLIED_NAME};
			int[] toViewAppliedFields = new int[] {R.id.appliedId, R.id.schoolName};
			SimpleCursorAdapter appliedAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_applied_entry, cursor, fromAppliedFields, toViewAppliedFields, 0);
			listView.setAdapter(appliedAdapter); 
			
		} 
		
		addSchoolApplied = (Button) findViewById(R.id.buttonAddSchoolsApplied);
        addSchoolApplied.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Schools_Applied.this, Add_Schools_Applied.class);
				startActivity(i);
			//	finish();
	         }
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schools__applied, menu);
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
		Intent i = new Intent(Schools_Applied.this, Education.class);
		startActivity(i);
	}
}

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

public class Financial_Aid extends ActionBarActivity {
	private Button button;
	
	private TextView aid;
	CLIPdbAdapter database;
	CLIPdb CLIPdbHelper;
	List<FinancialAid> aidList =new ArrayList<FinancialAid>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financial__aid);
		
		database =  new CLIPdbAdapter(this);
		database.open();
		aidList = database.getAllAid();
		
		if (aidList.size() != 0)
		{
			ListView listView = (ListView) findViewById(R.id.viewAid);
			
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					aid = (TextView) view.findViewById(R.id.aidId);
					String aidId = aid.getText().toString();
					Intent i = new Intent(getApplication(), Edit_Financial_Aid.class);
					i.putExtra("aidId", aidId);
					startActivity(i);
				}
			}); 
			
			Cursor cursor = database.getAllAidRows();
			String[] fromFields = new String[]{CLIPdb.KEY_AID_ID, CLIPdb.KEY_AID_NAME, CLIPdb.KEY_AID_AMOUNT};
			int[] toViewFields = new int[] {R.id.aidId, R.id.showaidName ,R.id.showaidAmount};
			SimpleCursorAdapter Adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.financial_aid_entry, cursor, fromFields, toViewFields, 0);
			listView.setAdapter(Adapter); 
			
		}
		
		button = (Button) findViewById(R.id.buttonAddFinancialAid);
		
        button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Financial_Aid.this, Add_Financial_Aid.class);
				startActivity(i);
			//	finish();
	         }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.financial__aid, menu);
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
	
	@Override
	public void onBackPressed() {
	    //your code when back button pressed
		Intent i = new Intent(Financial_Aid.this, Education.class);
		startActivity(i);
	}
}

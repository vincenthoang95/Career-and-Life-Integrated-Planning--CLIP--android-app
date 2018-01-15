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

public class Edit_Graduate_Plan extends ActionBarActivity {

	private CLIPdbAdapter database;
	private Graduate_Plans viewGrad;
	private EditText gradname = null,
			gradlocation = null,
			graddegree = null,
			gradtime = null;
	private Button edit;
	private Button delete;
	private String gradId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit__graduate__plan);
		
		database = new CLIPdbAdapter(this);
		viewGrad = new Graduate_Plans();
		database.open();
		
		gradname = (EditText) findViewById(R.id.showgradName);
		gradlocation = (EditText) findViewById(R.id.editgradLocation);
		gradtime = (EditText) findViewById(R.id.editgradDegree);
		graddegree = (EditText) findViewById(R.id.editgradTime);
		edit = (Button) findViewById(R.id.buttonGradEdit);
		delete = (Button) findViewById(R.id.buttonGradDelete);
		
		Intent i = getIntent();
		gradId = i.getStringExtra("gradId");
		GraduatePlan grad = database.getGrad(gradId);
		if(grad != null){
			gradname.setText(grad.getGradName());
			gradlocation.setText(grad.getGradLocation());
			graddegree.setText(grad.getGradDegree());
			gradtime.setText(grad.getGradTime());
		}
		edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String text1 = gradname.getText().toString();
				String text2 = gradlocation.getText().toString();
				String text3 = graddegree.getText().toString();
				String text4 = gradtime.getText().toString();
				
				database.updateGrad(gradId, text1,text2,text3,text4);
				Intent intent = new Intent (Edit_Graduate_Plan.this, Graduate_Plans.class);
				startActivity(intent);
				//finish();
			}
			
		});
		delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				database.deleteGrad(gradId);
				Intent intent = new Intent (Edit_Graduate_Plan.this, Graduate_Plans.class);
				startActivity(intent);
				//finish();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit__graduate__plan, menu);
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

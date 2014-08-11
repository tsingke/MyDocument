package com.example.smarthomeclient;

import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.Button;
import android.widget.EditText;

public class light extends Activity{

	private EditText light_et1;
	private EditText light_et2;
	private EditText light_et3;
	private EditText light_et4;
	private Button light_back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		light_et1=(EditText)findViewById(R.id.light_et1);
		light_et2=(EditText)findViewById(R.id.light_et2);
		light_et3=(EditText)findViewById(R.id.light_et3);
		light_et4=(EditText)findViewById(R.id.light_et4);
		light_back=(Button)findViewById(R.id.light_back);
		light_back.setOnClickListener(new lightbackclick());
	}
	public class lightbackclick implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent back=new Intent();
			back.setClass(light.this, MainActivity.class);
			back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			light.this.startActivity(back);
			finishActivity(0);
		}
		
	}
}


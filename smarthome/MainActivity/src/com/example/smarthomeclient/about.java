package com.example.smarthomeclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class about extends Activity{
	private Button email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		email=(Button)findViewById(R.id.email);
		email.setOnClickListener(new bugreport());
	}
	public class bugreport implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//ÏµÍ³ï¿½Ê¼ï¿½ÏµÍ³ï¿½Ä¶ï¿½ï¿½ï¿½Îªandroid.content.Intent.ACTION_SEND
			Intent email = new Intent(android.content.Intent.ACTION_SEND);
			email.setType("plain/text");
			String[] emailReciver = new String[]{"ZWN476867589@gmail.com"};
			String emailSubject = "bug·´À¡";

			//ï¿½ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½Ä¬ï¿½Ïµï¿½Ö·
			email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
			//ï¿½ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½Ä¬ï¿½Ï±ï¿½ï¿½ï¿½
			email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
			//ï¿½ï¿½ï¿½ï¿½ÏµÍ³ï¿½ï¿½ï¿½Ê¼ï¿½ÏµÍ³
			startActivity(Intent.createChooser(email, "ÇëÑ¡Ôñ´ò¿ªµÄÓ¦ÓÃ³ÌÐò"));
		}
	}    
}
	
	
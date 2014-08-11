package com.example.smarthomeclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class temp extends Activity {
	
	    //�¶ȼ�ؽ���ؼ�
		public EditText temp_et1;
		public EditText temp_et2;
		public EditText temp_et3;
		public EditText temp_et4;
		private Button temp_back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp);
		temp_et1=(EditText)findViewById(R.id.temp_et1);
		temp_et2=(EditText)findViewById(R.id.temp_et2);
		temp_et3=(EditText)findViewById(R.id.temp_et3);
		temp_et4=(EditText)findViewById(R.id.temp_et4);
		temp_back=(Button)findViewById(R.id.temp_back);
		temp_back.setOnClickListener(new tempbackclick());
}
	public class tempbackclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent back=new Intent();
			back.setClass(temp.this, MainActivity.class);
			back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			temp.this.startActivity(back);
			finishActivity(0);
		}
		
	}
	
}

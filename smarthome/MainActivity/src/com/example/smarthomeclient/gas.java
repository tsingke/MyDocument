package com.example.smarthomeclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class gas extends Activity{
	    //瓦斯监控界面控件
		private EditText gas_et1;
		private EditText gas_et2;
		private EditText gas_et3;
		private EditText gas_et4;
		private Button gas_back;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.gas);
			gas_back=(Button)findViewById(R.id.gas_back);
			gas_back.setOnClickListener(new gasbackclick());
		}
		public class gasbackclick implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent();
				back.setClass(gas.this, MainActivity.class);
				back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				gas.this.startActivity(back);
				finishActivity(0);
			}
			
		}
}

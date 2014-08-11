package com.example.smarthomeclient;

import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class connect extends Activity{
		private EditText edtext_connect;
		private Button ok;
		private Button cancel;
		public String SOCKET_IP;
		public int SOCKET_PORT=51706;
		Socket socket=null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.connect);
			cancel=(Button)findViewById(R.id.cancel);
			cancel.setOnClickListener(new cancelclick());
			ok=(Button)findViewById(R.id.ok);
		}
		public class cancelclick implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cancel=new Intent();
				cancel.setClass(connect.this, MainActivity.class);
				cancel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				connect.this.startActivity(cancel);
				finishActivity(0);
			}
			
		}
		public class okclick implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edtext_connect=(EditText)findViewById(R.id.edtext_connect);
				SOCKET_IP=edtext_connect.getText().toString();
				try{
					InetAddress serveraddr=InetAddress.getByName(SOCKET_IP);
					socket=new Socket(SOCKET_IP, SOCKET_PORT);
			}catch(Exception e) { 
        	    Log.e("TCP", "S: Error", e); 
        	} finally { 
        	    
        	} 
			
		}
}
}

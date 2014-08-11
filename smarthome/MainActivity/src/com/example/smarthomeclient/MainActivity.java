package com.example.smarthomeclient;

import java.io.InputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ToggleButton;
import com.example.smarthomeclient.*;
public class MainActivity extends Activity {
	//
	private Button btn_connect;
	private Button btn_gas;
	private Button btn_light;
	private Button btn_temp;
	private Button btn_exit;
	private ToggleButton led1;
	private ToggleButton led2;
	private ToggleButton led3;
	private ToggleButton led4;
	Socket socket=null;
	
	private String TempStr[];//用于存放温度数值
	private String LightLevelStr[];//用于存放光强数值
	private String GasStr[];//用于存放瓦斯数值
	private String AlarmFlag[];//用于报警标识
	private String LedFlag[];//用于灯状态标识
	
	private Timer timer;
	private TimerTask task;
	private final int TIMER_INVALIDATE = 1001;
	
	private int dlgIndex = 0;
	private int cnt = 0;
	private Double testValue = new Double(75.123);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_connect=(Button)findViewById(R.id.btn_connect);
		btn_gas=(Button)findViewById(R.id.btn_gas);
		btn_light=(Button)findViewById(R.id.btn_light);
		btn_temp=(Button)findViewById(R.id.btn_temp);
		btn_exit=(Button)findViewById(R.id.btn_exit);
		
		btn_exit.setOnClickListener(new exitclick());
		btn_connect.setOnClickListener(new connectclick());
		btn_gas.setOnClickListener(new gasclick());
		btn_temp.setOnClickListener(new tempclick());
		btn_light.setOnClickListener(new lightclick());
	}

	public class exitclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//finish();
			System.exit(0);
		}
	}
	public class connectclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent connect=new Intent();
			connect.setClass(MainActivity.this, connect.class);
			MainActivity.this.startActivity(connect);
		}
	}
	public class gasclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent gas=new Intent();
			gas.setClass(MainActivity.this, gas.class);
			MainActivity.this.startActivity(gas);
		}
	}
	public class tempclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent temp=new Intent();
			temp.setClass(MainActivity.this, temp.class);
			MainActivity.this.startActivity(temp);
		}
	}
	public class lightclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent light=new Intent();
			light.setClass(MainActivity.this, light.class);
			MainActivity.this.startActivity(light);
		}
	}
	 public void receive(){
	    	new Thread(){
				public void run(){
					try{
						
						while(!socket.isClosed()){//加入socket是打开的
							
							InputStream is = socket.getInputStream(); 

							byte data[] = new byte[512];
							int n = is.read(data);
							if(n > 0){						
								String val = new String(data);							
								//解析数据	
								switch(val.charAt(1))
								{
								case 'T':
									TempStr = val.split(",");
									break;
								case 'L':
									LightLevelStr = val.split(",");
									break;
								case 'G':
									GasStr = val.split(",");
									break;
								case 'A'://报警
									AlarmFlag = val.split(",");
									break;
								case 'D'://灯状态
									LedFlag = val.split(",");
									break;
								
								}
								
								Message message = new Message();// 生成消息，并赋予ID值
								message.what = TIMER_INVALIDATE;
								myHandler.sendMessage(message);// 投递消息
							}
							
							sleep(300);
						}
					}
					catch(Exception e){

					}
				}
			}.start();
	    }
	 Handler myHandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case TIMER_INVALIDATE://查询灯状态
					if(dlgIndex == 0)
					{
						if(LedFlag[1].charAt(0) == 0x30)
						{
							led1.setChecked(false);
						}
						else if(LedFlag[1].charAt(0) == 0x31)
						{
							led1.setChecked(true);
						}
						
						if(LedFlag[2].charAt(0) == 0x30)
						{
							led2.setChecked(false);
						}
						else if(LedFlag[2].charAt(0) == 0x31)
						{
							led2.setChecked(true);
						}
						
						if(LedFlag[3].charAt(0) == 0x30)
						{
							led3.setChecked(false);
						}
						else if(LedFlag[3].charAt(0) == 0x31)
						{
							led3.setChecked(true);
						}
						
						if(LedFlag[4].charAt(0) == 0x30)
						{
							led4.setChecked(false);
						}
						else if(LedFlag[4].charAt(0) == 0x31)
						{
							led4.setChecked(true);
						}
					}
					else if(dlgIndex == 1) //温度监控界面
					{
						temp_et1.setText(TempStr[1]);
						mTemp2.setText(TempStr[2]);
						mTemp3.setText(TempStr[3]);
						mTemp4.setText(TempStr[4]);					
					}
					else if(dlgIndex == 2) //光强监控界面
					{
						mLightLevel1.setText(LightLevelStr[1]);
						mLightLevel2.setText(LightLevelStr[2]);
						mLightLevel3.setText(LightLevelStr[3]);
						mLightLevel4.setText(LightLevelStr[4]);					
					}
					else if(dlgIndex == 3) //瓦斯监控界面
					{
						if(cnt == 1)
						{
							mGas1.setText(GasStr[1]);
							mGas2.setText(GasStr[2]);
							mGas3.setText(GasStr[3]);
							mGas4.setText(GasStr[4]);	
						}
						else if(cnt == 0)
						{
							if(AlarmFlag[1].charAt(0) == 0x30)
							{
								mAlarm1.setChecked(false);
							}
							else if(AlarmFlag[1].charAt(0) == 0x31)
							{
								mAlarm1.setChecked(true);
							}
							
							if(AlarmFlag[2].charAt(0) == 0x30)
							{
								mAlarm2.setChecked(false);
							}
							else if(AlarmFlag[2].charAt(0) == 0x31)
							{
								mAlarm2.setChecked(true);
							}
							
							if(AlarmFlag[3].charAt(0) == 0x30)
							{
								mAlarm3.setChecked(false);
							}
							else if(AlarmFlag[3].charAt(0) == 0x31)
							{
								mAlarm3.setChecked(true);
							}
							
							if(AlarmFlag[4].charAt(0) == 0x30)
							{
								mAlarm4.setChecked(false);
							}
							else if(AlarmFlag[4].charAt(0) == 0x31)
							{
								mAlarm4.setChecked(true);
							}
							
						}			
						
						
					}
					
					break;
				case 1://定时向server发送请求事件
					if(dlgIndex == 0)
					{
						MySend("DDDDD");  //主界面
					}
					else if(dlgIndex == 1)//温度监控界面
					{
						MySend("TTTTT");
					}
					else if(dlgIndex == 2)//光强监控界面
					{
						MySend("LLLLL");
					}
					else if(dlgIndex == 3)//瓦斯监控界面
					{
						if(cnt == 0)
						{
							cnt = 1;
							MySend("GGGGG");
						}
						else if(cnt == 1)
						{
							cnt = 0;
							MySend("AAAAA");
						}		
						
						
					}
					break;
				}
				super.handleMessage(msg);
			}
		};
}
	
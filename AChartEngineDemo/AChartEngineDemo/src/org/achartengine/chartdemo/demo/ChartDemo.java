package org.achartengine.chartdemo.demo;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import method.Dvalue;
import method.linearSmooth;
import method.selectList;

import org.achartengine.GraphicalView;
import org.achartengine.chartdemo.demo.chart.BIS;
import org.achartengine.chartdemo.demo.chart.FirstActivity;
import org.achartengine.chartdemo.fft.Complex;
import org.achartengine.chartdemo.fft.FFT;

import com.neurosky.thinkgear.TGDevice;
import com.neurosky.thinkgear.TGEegPower;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChartDemo extends Activity {

	private LinearLayout mlayout;
	private LinearLayout mlayout1;
	private LinearLayout mlayout2;
	private GraphicalView mViewChart;
	private GraphicalView mViewChart1;
	private GraphicalView mViewChart2;
	private Button startbtn;
	private Button stopbtn;
	private Spinner spinner;
	private Handler handler;
	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	private String ti = null;  //波形的名称
	private TextView textview1;
	private Timer timer = new Timer();
	private TimerTask task;// ����
	BluetoothAdapter bluetoothAdapter;
    TGDevice tgDevice;
    final boolean rawEnabled = true;  
    

    double beta;
    double syn;
    boolean flag;
	ArrayList<Integer> rawValue=new ArrayList<Integer>();
	ArrayList<Integer> raw=new ArrayList<Integer>();
    //EEG的频率带
	ArrayList<Integer> lowAlpha=new ArrayList<Integer>();
	ArrayList<Integer> highAlpha=new ArrayList<Integer>();
	ArrayList<Integer> lowBeta=new ArrayList<Integer>();
	ArrayList<Integer> highBeta=new ArrayList<Integer>();
	ArrayList<Integer> lowGamma=new ArrayList<Integer>();
	ArrayList<Integer> midGamma=new ArrayList<Integer>();
	ArrayList<Integer> fft=new ArrayList<Integer>();
	ArrayList<Integer> list=new ArrayList<Integer>();
	ArrayList<Double> betaArrayList=new ArrayList<Double>();
	ArrayList<Double> synArrayList=new ArrayList<Double>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xy_chart);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	        if( bluetoothAdapter == null ) {            
	        	Toast.makeText( this, "蓝牙不可用！", Toast.LENGTH_SHORT ).show();
	        	return;
	        } 
	        else 
	        {
	        	tgDevice = new TGDevice(bluetoothAdapter, handler1);
	        }
        //放置图表的三个LAyOUT
		mlayout = (LinearLayout) findViewById(R.id.chart);
		mlayout1 = (LinearLayout) findViewById(R.id.chart1);
		mlayout2 = (LinearLayout) findViewById(R.id.chart2);
		textview1 = (TextView) findViewById(R.id.textView1);

		final FirstActivity firstActivity1 = new FirstActivity();
		mViewChart = firstActivity1.execute("原始信号", this, 512, -500,500);
		final FirstActivity firstActivity2 = new FirstActivity();
		mViewChart1 = firstActivity2.execute("频谱", this, 512, 0,20);
		final FirstActivity firstActivity3 = new FirstActivity();
		mViewChart2 = firstActivity3.execute("HighAlpha频带", this, 60,0, 50);

		startbtn = (Button) findViewById(R.id.startBtn);
		stopbtn = (Button) findViewById(R.id.stopBtn);
		spinner = (Spinner) findViewById(R.id.spinner);
		startbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 tgDevice.connect( true );
				 list=highAlpha;//默认highAlpha波

				task = new TimerTask() {
                    @Override
					public void run() {
						Message message = new Message();
						message.what = 1;
						handler.sendMessage(message);
					}
				};
				timer.schedule(task, 1000, 1000);//每1s更一次新
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 1) {
                            if(flag==true){
                                firstActivity1.updateChart1(rawValue, "原始信号");
								firstActivity2.updateChart1(fft, "频谱");
								int s=Dvalue.dvalue(rawValue);
                                if(s<350){
                                    synArrayList.add(BIS.Syn(fft));
                                    betaArrayList.add(BIS.beta(fft));
                                }
                                rawValue.clear();
                                flag=false;
                            }
                            firstActivity3.updateChart(list, ti);


                            if(synArrayList.size()==8){

                                syn=selectList.selectlist(synArrayList);
                                beta=selectList.selectlist(betaArrayList);

                                synArrayList.clear();
                                betaArrayList.clear();

                            }
                            double bis=24*syn+20*beta; //计算BIS指数
                            DecimalFormat df=new DecimalFormat("#.###");
                            bis=Double.valueOf(df.format(bis));
                            textview1.setText("BIS指数: "+bis);
                            super.handleMessage(msg);
                        }
                    }
                };
			}
		});


		stopbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ChartDemo.this.finish();
			}
		});


		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String[] item = getResources().getStringArray(R.array.items);
				Toast.makeText(ChartDemo.this, "你点击的是:" + item[pos], 500)
						.show();
				firstActivity3.series.clear();
				
				ti = item[pos];
				if(pos==0){
					list=highAlpha;
				}
				else if (pos==1) {
					list=lowAlpha;
				}
				else if (pos==2) {
					list=highBeta;
				}
				else if (pos==3) {
					list=lowBeta;
				}
				else if (pos==4) {
					list=midGamma;
				}
				else {
					list=lowGamma;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO������
			}
		});
        //添加视图
		mlayout.addView(mViewChart, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		;
		mlayout1.addView(mViewChart1, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		;
		mlayout2.addView(mViewChart2, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		;
	}

	@Override
	public void onDestroy() {
		if (mTimer != null) {// ����������ʱ�ص�Timer
			mTimer.cancel();
			mTimerTask.cancel();
			super.onDestroy();
		}
		super.onDestroy();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		// tgDevice.close();
		super.onStop();
	}
	
	/**
     * Handles messages from TGDevice
     */
    final Handler handler1 = new Handler() {
        @Override
        public void handleMessage( Message msg ) {

            switch( msg.what ) {
            	case TGDevice.MSG_MODEL_IDENTIFIED:
            		
                    tgDevice.setBlinkDetectionEnabled(true);
                    tgDevice.setTaskDifficultyRunContinuous(true);
                    tgDevice.setTaskDifficultyEnable(true);
                    tgDevice.setTaskFamiliarityRunContinuous(true);
                    tgDevice.setTaskFamiliarityEnable(true);
                    tgDevice.setRespirationRateEnable(true); /// not allowed on EEG hardware, here to show the override message
            		break;
            	
                case TGDevice.MSG_STATE_CHANGE:
    
                    switch( msg.arg1 ) {
    	                case TGDevice.STATE_IDLE:
    	                    break;
    	                case TGDevice.STATE_CONNECTING:       	
    	                	Toast.makeText( ChartDemo.this, "Connecting...\n", Toast.LENGTH_SHORT ).show();
    	                	break;	
                        case TGDevice.STATE_CONNECTED:

                            Toast.makeText( ChartDemo.this, "Connected.\n", Toast.LENGTH_SHORT ).show();
                            tgDevice.start();
                            break;
    	                case TGDevice.STATE_NOT_FOUND:

    	                	Toast.makeText( ChartDemo.this, "Could not connect to any of the paired BT devices.  Turn them on and try again.\nBluetooth devices must be paired 1st" , Toast.LENGTH_SHORT ).show();
    	                	
    	                	break;
                        case TGDevice.STATE_ERR_NO_DEVICE:

                            Toast.makeText( ChartDemo.this, "No Bluetooth devices paired.  Pair your device and try again.\n", Toast.LENGTH_SHORT ).show();
                            break;
    	                case TGDevice.STATE_ERR_BT_OFF:

    	                	Toast.makeText( ChartDemo.this, "Bluetooth is off.  Turn on Bluetooth and try again.", Toast.LENGTH_SHORT ).show();
    	                    break;

    	                case TGDevice.STATE_DISCONNECTED:

    	                	Toast.makeText( ChartDemo.this, "Disconnected.\n", Toast.LENGTH_SHORT ).show();
                    } /* end switch on msg.arg1 */

                    break;

			case TGDevice.MSG_RAW_DATA:
				/* Handle raw EEG/EKG data here */
				int rawV = msg.arg1;
				raw.add(rawV);
				if (raw.size() == 512) {
					Complex[] x = new Complex[512];
					for (int i = 0; i < 512; i++) {
						rawValue.add(raw.get(i));
//						x[i] = new Complex(raw.get(i), 0);
					}
					rawValue=linearSmooth.smooth(rawValue);
					for(int j=0;j<512;j++){
						x[j] = new Complex(rawValue.get(j), 0);
					}
					fft.clear();
					Complex[] y = FFT.fft(x);
					double[] show = FFT.show(y);
					
					for (int i = 0; i < show.length; i++) {
						fft.add((int) show[i]);
					}
					flag = true;
					
					raw.clear();
				}
				break;

                	
	            case TGDevice.MSG_EEG_POWER:
	            	TGEegPower e = (TGEegPower)msg.obj;
	            	lowAlpha.add(e.lowAlpha/1000);
	            	highAlpha.add(e.highAlpha/1000);	
	            	lowBeta.add(e.lowBeta/1000);
	            	highBeta.add(e.highBeta/1000);	
	            	lowGamma.add(e.lowGamma/1000);
	            	midGamma.add(e.midGamma/1000);	
	            	break;
	            
                default:
                	break;               	
        	} /* end switch on msg.what */
        } /* end handleMessage() */       
    }; /* end Handler */
    
    
	  public void doStuff(View view) {
	    	if( tgDevice.getState() != TGDevice.STATE_CONNECTING && tgDevice.getState() != TGDevice.STATE_CONNECTED ) {
	    	    
	    		tgDevice.connect( rawEnabled );
	    	}
	}

}
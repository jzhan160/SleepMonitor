package org.achartengine.chartdemo.demo.chart;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import android.R.integer;
import android.graphics.SumPathEffect;
import android.util.Log;

public class BIS {

	
	static double  syn;
	static double  beta;

	public static void b(ArrayList<Integer> list) {
		int W1=16;
		int W2=20;

			syn=Syn(list);
			beta=beta(list);
			Log.i("tag","syn:"+syn+"   beta"+beta);
//	      double BIS=W1*syn+W2*beta;
//	      Log.i("tag", "BIS��"+BIS);
//
//      return BIS;
	}

	public static double beta(ArrayList<Integer> list){
		DecimalFormat df=new DecimalFormat("#.###"); 		//������λС����		
//		double P1=10*Math.log10(Sum(list,30,47))/(N+1);
//		double P2=10*Math.log10(Sum(list,11,20))/(N+1);
//		Log.i("tag","s1"+Sum(list,30,47)+"s2 "+Sum(list,11,20) );
		
		double P1=SumPy(list,30,47);
		double P2=SumPy(list,11,20);
//		Log.i("tag","p1:"+P1+"  p2:"+P2 );
		double P=Math.log(P1/P2);
//    	Log.i("tag","beta"+beta);

//		P=Double.valueOf(df.format(P));
		Log.i("tag","����ϵ��"+P);
//		if (P<1&&P>0.5) {
//			beta=P;
//		}
//		Log.i("tag","����ϵ��"+P);
		return 	P;				//����Py
	}
	
	private static double Py(ArrayList<Integer> list,int i) {
		int N=512;
		double Py=10*Math.log10(Math.pow(list.get(i), 2)/(N+1));
		return Py;
	}


	private static double SumPy(ArrayList<Integer> list,int i, int j) {
		// TODO �Զ����ɵķ������
		double sum=0;
		//���ò���Ƶ��Ϊ512HZ
		for(int m=i;m<=j;m++){
		sum+=Py(list,m);
		}
		return sum;
	}

	
public static double Syn(ArrayList<Integer> list){
		

		DecimalFormat df1=new DecimalFormat("#.###"); 		//������λС����		
		double B1=Sumsyn(list, 1, 47);
		double B2=Sumsyn(list, 40, 47);
		double Synchfastslow=Math.log(B1/B2);

//		Synchfastslow=Double.valueOf(df1.format(Synchfastslow));
//		Log.i("tag","syn"+Synchfastslow);
		Log.i("tag","Synchfastslow�� "+Synchfastslow);
		return Synchfastslow;				//����Py
	}




	private static double Sumsyn(ArrayList<Integer> list,int i,int j) {
		double sumsyn=0;
		for(int m=i;m<=j;m++){
			if((list.get(m)>list.get(m-1))&&(list.get(m)>list.get(m+1))){
			sumsyn+=B(list,m);
			}
		}
		return sumsyn;
	}
	
	private static double B(ArrayList<Integer> list,int i) {
		// TODO �Զ����ɵķ������
		double b =Math.abs(list.get(0))*Math.pow(list.get(i), 2);
		return b;
	}
}

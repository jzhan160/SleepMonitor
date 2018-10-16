package org.achartengine.chartdemo.demo.chart;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.chartdemo.demo.ChartDemo;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.provider.ContactsContract.Contacts.Data;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;


public class SecondActivity extends Activity {

	GraphicalView ViewChart;// ������ʾ����ͳ��ͼ
	public XYSeries series;// XY���ݵ�
	private XYMultipleSeriesDataset mDataset;// XY�����ݼ�
	private XYMultipleSeriesRenderer renderer;// ����ͳ��ͼ�������
	public int k=0;
	
	public GraphicalView execute(String title,Context context,int xLength,int yLength) {

		
		series = new XYSeries(title);// ������������������ϵ����е㣬��һ����ļ��ϣ�������Щ�㻭������


		mDataset = new XYMultipleSeriesDataset(); // ����һ�����ݼ���ʵ����������ݼ�������������ͼ��	
		mDataset.addSeries(series);// ���㼯��ӵ�������ݼ���
		
	    
	    int color = Color.RED;// ������ɫ
		PointStyle style = PointStyle.CIRCLE;// ���������������ʾ
	    renderer = buildRenderer(color, style, true);
	    renderer.setXLabels(10);
	    renderer.setYLabels(5);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setShowLegend(false);// ����ʾͼ��
	    renderer.setZoomButtonsVisible(false);
	    renderer.setPanEnabled(true, false);
		renderer.setClickEnabled(false);
		renderer.setLabelsTextSize(20);
		renderer.setAxisTitleTextSize(25);//������������ֵĴ�С  
		renderer.setChartTitleTextSize(25);//��������ͼ��������ֵĴ�С    
		renderer.setMargins(new int[] { 30, 30, 0,20 });//����ͼ�����߿�(��/��/��/��)  

		setChartSettings(renderer, title, "  ", " ", 0, xLength, 0, yLength,
	        Color.LTGRAY, Color.LTGRAY);
		
		ViewChart = ChartFactory.getLineChartView(context, mDataset, renderer);
		return ViewChart;
	}
	
	
	
	public XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle style, boolean fill) {// ����ͼ�������߱������ʽ��������ɫ����Ĵ�С�Լ��ߵĴ�ϸ��
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer(); 
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(color);
//		r.setPointStyle(style);
		r.setFillPoints(fill);
		r.setLineWidth(3);
//		r.setFillBelowLine(true);
//		r.setFillBelowLineColor(color);
		renderer.addSeriesRenderer(r);
		return renderer;
	}

	public void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {// ������������ĸ������ԣ�������Ķ��ٷ�API�ĵ�
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}
	
	

	public void updateChart(File file,String title,int i) {// ��Ҫ������ÿ��1000msˢ������ͳ��ͼ
		mDataset.removeSeries(series);// �Ƴ����ݼ��оɵĵ㼯
		renderer.setChartTitle(title);
		series.clear();
		String name=file.getName();
			try {
				int row = H20121012.getTotalLines(file);
//			    int raw=(int) (Math.random()*100);
//			    int halpha=(int) (Math.random()*100);
//			    int lalpha=(int) (Math.random()*100);
//			    int hbelta=(int) (Math.random()*100);
//			    int lbelta=(int) (Math.random()*100);
//			    int hgama=(int) (Math.random()*100);
//			    int lgama=(int) (Math.random()*100);
//			    int[] a= new int[]{raw,halpha,lalpha,hbelta,lbelta,hgama,lgama};
//				if(name.equals("info.txt")){
//					Log.i("tag","���鵰�㻹��");
//					for(int t=0;t<row;t++){
//			    series.add(t,Integer.valueOf( H20121012.readAppointedLineNumber(file, t+1).split("\\s{1,}")[i]) );
//					series.add(k,Integer.valueOf( H20121012.readAppointedLineNumber(file, k+1).split("\\s{1,}")[i]) );
//					series.add(k,a[i] );
//				}
//				else if (name.equals("info.txt")&&row>64) {
//						for(int y=0;y<10;y++){
//							series.add(row-128+y,Integer.valueOf( H20121012.readAppointedLineNumber(file, row-128+y+1).split("\\s{1,}")[i]) );
//						}
//					}
//				else 
//					if(name.equals("fft.txt")){
					Log.i("tag", "fft�ļ���������Ϊ"+row);	
//					
//					for(int j=0;j<10;j++){
//					series.add(j, Float.valueOf(H20121012.readAppointedLineNumber(file, lineNumber).split("\\s{1,}")[j]) );
//					}
						String[] a=H20121012.readAppointedLineNumber(file, row).split("\\s{1,}");
						
						for(int j=0;j<128;j++){
					        series.add(j, Double.valueOf(a[j]));	
						}
						
//				}
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		mDataset.addSeries(series);// �����ݼ�������µĵ㼯
		ViewChart.invalidate();// ��ͼ���£�û����һ�������߲�����ֶ�̬

//		k++;
//		if(k>128&&name.equals("info.txt")){
//			renderer.setXAxisMax(128);// ����X���ֵ
//			renderer.setXAxisMin(0);// ����X���ֵ
//		}
	}
}
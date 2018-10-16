package org.achartengine.chartdemo.demo.chart;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


public class FirstActivity extends Activity {

	private GraphicalView ViewChart;// ������ʾ����ͳ��ͼ
	public XYSeries series;// ���ݵ㼯
	private XYMultipleSeriesDataset mDataset;// XY�����ݼ�
	private XYMultipleSeriesRenderer renderer;// ����ͳ��ͼ�������
	private int k=0;
	//����ͼ��
	public GraphicalView execute(String title,Context context,int xLength,int yLengthstart,int yLength) {

		
		series = new XYSeries(title);// ��ļ��ϣ�������Щ�㻭������
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
		renderer.setLabelsTextSize(12);
		renderer.setAxisTitleTextSize(15);//������������ֵĴ�С  
		renderer.setChartTitleTextSize(15);//��������ͼ��������ֵĴ�С    
		renderer.setMargins(new int[] { 30, 30, 0,20 });//����ͼ�����߿�(��/��/��/��)  

		setChartSettings(renderer, title, "  ", " ", 0, xLength, yLengthstart, yLength,
	        Color.GRAY, Color.GRAY);
		
		ViewChart = ChartFactory.getLineChartView(context, mDataset, renderer);
		return ViewChart;
	}


	// ����ͼ�������߱������ʽ��������ɫ����Ĵ�С�Լ��ߵĴ�ϸ��
	public XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle style, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer(); 
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(color);
		r.setFillPoints(fill);
		r.setLineWidth(3);
		renderer.addSeriesRenderer(r);
		return renderer;
	}
	// ������������ĸ�������
	public void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
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


	// ��Ҫ������ÿ��1000msˢ������ͳ��ͼ
	public void updateChart(ArrayList<Integer> list,String title) {
		mDataset.removeSeries(series);// �Ƴ����ݼ��оɵĵ㼯
		renderer.setChartTitle(title);

		if (!list.isEmpty()) {
			int a = list.get(list.size() - 1);
			if (a < 1500) {
				series.add(k, a);
			} else {
				series.add(k, 0);
			}
			k++;
		}

		mDataset.addSeries(series);// �����ݼ�������µĵ㼯
		ViewChart.invalidate();// ��ͼ���£�û����һ�������߲�����ֶ�̬

		if(k>60){
			renderer.setXAxisMax(k);// ����X���ֵ
			renderer.setXAxisMin(k-60);// ����X��Сֵ
		}
	}
	
	public void updateChart1(ArrayList<Integer> list,String title) {// ��Ҫ������ÿ��1000msˢ������ͳ��ͼ
		mDataset.removeSeries(series);// �Ƴ����ݼ��оɵĵ㼯
		renderer.setChartTitle(title);
		series.clear();

		for(int i=0;i<list.size();i++){
			series.add(i, list.get(i));
		}

		mDataset.addSeries(series);// �����ݼ�������µĵ㼯
		ViewChart.invalidate();// ��ͼ���£�û����һ�������߲�����ֶ�̬

	}
	
	
}
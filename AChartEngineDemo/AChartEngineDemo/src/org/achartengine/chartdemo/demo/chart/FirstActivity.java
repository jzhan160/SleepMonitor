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

	private GraphicalView ViewChart;// 用于显示现行统计图
	public XYSeries series;// 数据点集
	private XYMultipleSeriesDataset mDataset;// XY轴数据集
	private XYMultipleSeriesRenderer renderer;// 线性统计图主描绘器
	private int k=0;
	//创建图表
	public GraphicalView execute(String title,Context context,int xLength,int yLengthstart,int yLength) {

		
		series = new XYSeries(title);// 点的集合，根据这些点画出曲线
		mDataset = new XYMultipleSeriesDataset(); // 创建一个数据集的实例，这个数据集将被用来创建图表
		mDataset.addSeries(series);// 将点集添加到这个数据集中
		
	    
	    int color = Color.RED;// 设置颜色
		PointStyle style = PointStyle.CIRCLE;// 设置外观周期性显示
	    renderer = buildRenderer(color, style, true);
	    renderer.setXLabels(10);
	    renderer.setYLabels(5);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setShowLegend(false);// 不显示图例
	    renderer.setZoomButtonsVisible(false);
	    renderer.setPanEnabled(true, false);
		renderer.setClickEnabled(false);
		renderer.setLabelsTextSize(12);
		renderer.setAxisTitleTextSize(15);//设置轴标题文字的大小  
		renderer.setChartTitleTextSize(15);//设置整个图表标题文字的大小    
		renderer.setMargins(new int[] { 30, 30, 0,20 });//设置图表的外边框(上/左/下/右)  

		setChartSettings(renderer, title, "  ", " ", 0, xLength, yLengthstart, yLength,
	        Color.GRAY, Color.GRAY);
		
		ViewChart = ChartFactory.getLineChartView(context, mDataset, renderer);
		return ViewChart;
	}


	// 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
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
	// 设置主描绘器的各项属性
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


	// 主要工作是每隔1000ms刷新整个统计图
	public void updateChart(ArrayList<Integer> list,String title) {
		mDataset.removeSeries(series);// 移除数据集中旧的点集
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

		mDataset.addSeries(series);// 在数据集中添加新的点集
		ViewChart.invalidate();// 视图更新，没有这一步，曲线不会呈现动态

		if(k>60){
			renderer.setXAxisMax(k);// 设置X最大值
			renderer.setXAxisMin(k-60);// 设置X最小值
		}
	}
	
	public void updateChart1(ArrayList<Integer> list,String title) {// 主要工作是每隔1000ms刷新整个统计图
		mDataset.removeSeries(series);// 移除数据集中旧的点集
		renderer.setChartTitle(title);
		series.clear();

		for(int i=0;i<list.size();i++){
			series.add(i, list.get(i));
		}

		mDataset.addSeries(series);// 在数据集中添加新的点集
		ViewChart.invalidate();// 视图更新，没有这一步，曲线不会呈现动态

	}
	
	
}
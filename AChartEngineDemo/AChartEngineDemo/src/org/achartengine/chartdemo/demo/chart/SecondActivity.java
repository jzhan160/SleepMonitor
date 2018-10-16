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

	GraphicalView ViewChart;// 用于显示现行统计图
	public XYSeries series;// XY数据点
	private XYMultipleSeriesDataset mDataset;// XY轴数据集
	private XYMultipleSeriesRenderer renderer;// 线性统计图主描绘器
	public int k=0;
	
	public GraphicalView execute(String title,Context context,int xLength,int yLength) {

		
		series = new XYSeries(title);// 这个类用来放置曲线上的所有点，是一个点的集合，根据这些点画出曲线


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
		renderer.setLabelsTextSize(20);
		renderer.setAxisTitleTextSize(25);//设置轴标题文字的大小  
		renderer.setChartTitleTextSize(25);//设置整个图表标题文字的大小    
		renderer.setMargins(new int[] { 30, 30, 0,20 });//设置图表的外边框(上/左/下/右)  

		setChartSettings(renderer, title, "  ", " ", 0, xLength, 0, yLength,
	        Color.LTGRAY, Color.LTGRAY);
		
		ViewChart = ChartFactory.getLineChartView(context, mDataset, renderer);
		return ViewChart;
	}
	
	
	
	public XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle style, boolean fill) {// 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
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
			int labelsColor) {// 设置主描绘器的各项属性，详情可阅读官方API文档
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
	
	

	public void updateChart(File file,String title,int i) {// 主要工作是每隔1000ms刷新整个统计图
		mDataset.removeSeries(series);// 移除数据集中旧的点集
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
//					Log.i("tag","你麻蛋你还卡");
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
					Log.i("tag", "fft文件的总行数为"+row);	
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		mDataset.addSeries(series);// 在数据集中添加新的点集
		ViewChart.invalidate();// 视图更新，没有这一步，曲线不会呈现动态

//		k++;
//		if(k>128&&name.equals("info.txt")){
//			renderer.setXAxisMax(128);// 设置X最大值
//			renderer.setXAxisMin(0);// 设置X最大值
//		}
	}
}
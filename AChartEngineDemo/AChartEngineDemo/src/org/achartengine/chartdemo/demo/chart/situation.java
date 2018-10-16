package org.achartengine.chartdemo.demo.chart;

public class situation {
	public static String sitString(double bis) {
		// if(bis>=80){return "清醒";}
		// if(bis<80&&bis>=60){return "轻度麻醉";}
		// if(bis<60&&bis>=40){return "深度麻醉";}
		// return "爆发抑制";

		if (bis >= 0) {
			return "清醒";
		}

		return "爆发抑制";
	}
}

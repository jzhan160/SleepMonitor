package org.achartengine.chartdemo.demo.chart;

public class situation {
	public static String sitString(double bis) {
		// if(bis>=80){return "����";}
		// if(bis<80&&bis>=60){return "�������";}
		// if(bis<60&&bis>=40){return "�������";}
		// return "��������";

		if (bis >= 0) {
			return "����";
		}

		return "��������";
	}
}

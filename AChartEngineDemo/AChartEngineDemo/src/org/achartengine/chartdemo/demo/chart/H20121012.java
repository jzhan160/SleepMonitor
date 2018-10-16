package org.achartengine.chartdemo.demo.chart;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public final class H20121012 {
	/**
	 * ��ȡ�ļ�ָ���С�
	 */

	public  static String readAppointedLineNumber(File sourceFile, int lineNumber)  //�ӵ�һ�п�ʼ��û�е�����
            throws IOException {  
        FileReader in = new FileReader(sourceFile);  
        LineNumberReader reader = new LineNumberReader(in);  
        String string = null;
        String s = "";  
        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {  
            System.out.println("�����ļ���������Χ(1��������)֮�ڡ�");  
//            System.exit(0);  
        }  
        int lines = 0;  
        while (s != null) {  
            lines++;  
            s = reader.readLine();  
            if((lines - lineNumber) == 0) {  
//                System.out.println(s);  
//                System.exit(0); 
                  string=s;
            }  
        }  
        reader.close();  
        in.close();  
        return string;
    }  

	// �ļ����ݵ���������
	public static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}
	
}
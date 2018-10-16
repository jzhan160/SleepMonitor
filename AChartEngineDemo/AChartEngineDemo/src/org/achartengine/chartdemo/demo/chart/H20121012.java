package org.achartengine.chartdemo.demo.chart;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public final class H20121012 {
	/**
	 * 读取文件指定行。
	 */

	public  static String readAppointedLineNumber(File sourceFile, int lineNumber)  //从第一行开始，没有第零行
            throws IOException {  
        FileReader in = new FileReader(sourceFile);  
        LineNumberReader reader = new LineNumberReader(in);  
        String string = null;
        String s = "";  
        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {  
            System.out.println("不在文件的行数范围(1至总行数)之内。");  
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

	// 文件内容的总行数。
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
package method;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.content.Context;


/*
 * 创建txt文件，分别用来存储原始信号和fft后的信号，并初始化
 */
public class CreatFile {
	
	  public static File file;
	  public static int rowNumber=0;//控制频谱图读取数据源的行数
	  public static int N = 128;             //fft变换取的点数
	  int totalLine;
	  double bis=0;
	  
	  
		public static void createfile(Context context,String filename){
			try {
				file = new File(context.getFilesDir(),filename);
				if(!file.exists()){
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				FileWriter fw = new FileWriter(file);
				BufferedWriter bufferedwrited = new BufferedWriter(fw);
			    bufferedwrited.write("");
			    bufferedwrited.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

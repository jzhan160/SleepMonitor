package method;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.content.Context;


/*
 * ����txt�ļ����ֱ������洢ԭʼ�źź�fft����źţ�����ʼ��
 */
public class CreatFile {
	
	  public static File file;
	  public static int rowNumber=0;//����Ƶ��ͼ��ȡ����Դ������
	  public static int N = 128;             //fft�任ȡ�ĵ���
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

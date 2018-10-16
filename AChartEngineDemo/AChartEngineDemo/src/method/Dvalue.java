package method;

import java.util.ArrayList;
//获取峰峰值
public class Dvalue {
	public static int dvalue(ArrayList<Integer> list){
		 int max = list.get(0);     
         int min = list.get(0); 
       for (int i = 0; i < list.size(); i++) {          
                 if (min > list.get(i)) min = list.get(i);   
                  if (max < list.get(i)) max = list.get(i);        
          }        
		return   (max-min);
	}
}

package method;

import java.util.ArrayList;
//线性平滑滤波
public class linearSmooth {
	public static ArrayList<Integer> smooth(ArrayList<Integer> list){
		double s;
		int N=list.size();
		
		ArrayList<Integer> sm=new ArrayList<Integer>();
		
		s=  ( 13.0 * list.get(0) + 10.0 * list.get(1) + 7.0 *list.get(2) + 4.0 * list.get(3) +
				list.get(4)- 2.0 *list.get(5) - 5.0 * list.get(6) ) / 28.0;
	    sm.add(0,(int)s);
		
		
        s = ( 5.0 * list.get(0) + 4.0 * list.get(1) + 3 * list.get(2) + 2 * list.get(3) +
        		list.get(4) - list.get(6) ) / 14.0;
        sm.add(1,(int)s);
        
        
        s=( 7.0 * list.get(0) + 6.0 * list.get(1) + 5.0 * list.get(2) + 4.0 * list.get(3) +
                3.0 *list.get(4) + 2.0 * list.get(5) + list.get(6) ) / 28.0;
        sm.add(2,(int)s);

	        for (int i = 3; i <= N - 4; i++ )
	        {
	        	double g=( list.get(i-3) + list.get(i-2) + list.get(i-1) + list.get(i) + list.get(i+1) + list.get(i+2) + list.get(i+3) ) / 7.0;
	            sm.add(i,(int)g);
	        }

	        s=( 7.0 * list.get(N-1)+ 6.0 * list.get(N-2) + 5.0 * list.get(N-3) +
	                4.0 * list.get(N-4) + 3.0 * list.get(N-5) + 2.0 * list.get(N-6) + list.get(N-7) ) / 28.0;
	        sm.add(N-3,(int)s);
	        
	        s=( 5.0 *list.get(N-1) + 4.0 * list.get(N-2) + 3.0 * list.get(N-3) +
	                2.0 * list.get(N-4) + list.get(N-5) - list.get(N-7) ) / 14.0;
	        sm.add(N-2,(int)s);
	        
	        s= ( 13.0 * list.get(N-1) + 10.0 * list.get(N-2) + 7.0 * list.get(N-3) +
	                4 * list.get(N-4) + list.get(N-5) - 2 * list.get(N-6) - 5 * list.get(N-7) ) / 28.0;
	        sm.add(N-1,(int)s);
		return sm;
	}
}

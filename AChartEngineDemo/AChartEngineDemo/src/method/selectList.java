package method;
import java.util.ArrayList;

/*
 * 对BIS指数保存的list进行筛选值
 */
public class selectList {
		public  static Double selectlist(ArrayList<Double> list){
			//计算均值ave
			double sum=0;
			int N=list.size();
			for(int i=0;i<N;i++){
				sum+=list.get(i);
			}
			double ave=sum/N;
			
			//计算方差sdev
			double sdev=0;
			for(int j=0;j<N;j++){
				sdev+=Math.pow((list.get(j)-ave), 2);
			}
			sdev=Math.sqrt(sdev/N);
			
			//筛选出在阈值范围内的点
			ArrayList<Double> list2=new ArrayList<Double>();
			for(int m=0;m<N;m++){
				double temp=list.get(m);
				if(temp<(ave+sdev)&&temp>(ave-sdev)){
					list2.add(temp);
				}
			}
			
			//对筛选出来的list求平均数
			int N2=list2.size();
			double sum2=0;
			for(int n=0;n<N2;n++){
				sum2+=list2.get(n);
			}
			
			return sum2/N2;
		}
}

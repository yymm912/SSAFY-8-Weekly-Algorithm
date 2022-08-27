package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18310 {
	
	static int N;
	static int[] location;
	
	static long sum,avg;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		location=new int[N];
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<N; i++) {
			location[i]=Integer.parseInt(st.nextToken());
			//sum+=location[i];
		}
		
		Arrays.sort(location);
		System.out.println(location[(N-1)/2]);
		
//		avg=sum/N;
//		Arrays.sort(location);
//
//		long minDiff=Integer.MAX_VALUE;
//		int home=0;
//		
//		for (int i=0; i<N; i++) {
//			long diff=Math.abs(location[i]-avg);
//			if (diff<minDiff) {
//				minDiff=diff;
//				home=location[i];
//			}
//		}
//		
//		System.out.println(home);
	}
}

package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_18310 {

	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int [] house=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++)house[i]=Integer.parseInt(st.nextToken());
		Arrays.sort(house);
		System.out.print(house[(n-1)/2]);
	}
}

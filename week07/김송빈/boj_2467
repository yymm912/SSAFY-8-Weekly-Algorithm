package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2467 {
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int []liquid=new int[n];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)liquid[i]=Integer.parseInt(st.nextToken());
		
		int min=Integer.MAX_VALUE;
		int []arr=new int[2];
		for(int i=0;i<n;i++) {
			int left=i+1;
			int right=n-1;
			
			while(left<=right) {
				int mid=(left+right)/2;
				int sum=liquid[i]+liquid[mid];
				
				if(Math.abs(sum)<Math.abs(min)) {
					min=sum;
					arr[0]=liquid[i];
					arr[1]=liquid[mid];				
				}
				
				if(sum<0)left=mid+1;
				else right=mid-1;
			}
		}
		System.out.println(arr[0]+" "+arr[1]);
	}
}

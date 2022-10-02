package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053 {
	static int N, ans=1;
	static int[] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		st=new StringTokenizer (br.readLine());
		
		arr=new int[N];
		dp=new int[N];
		
		for (int i=0; i<N; i++) 
			arr[i]=Integer.parseInt(st.nextToken());
		

		for (int i=0; i<N; i++) {
			dp[i]=1;
			for (int j=0; j<i; j++) {
				if (arr[i]>arr[j] && dp[i]<dp[j]+1) {
					dp[i]=dp[j]+1;
					ans=Math.max(ans, dp[i]);
				}
			}
		}
		System.out.println(ans);
	}
}

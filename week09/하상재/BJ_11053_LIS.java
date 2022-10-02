package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11053_LIS {

	static int N, answer;
	static int[] arr;
	static int[] DP;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 1;
		if(N==1) {
			System.out.println(1);
			return;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		DP = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			DP[i] = 1;
			for(int j=i-1; j>=0; j--) {
				
				if(arr[j]<arr[i]) {
					if(DP[j]>=DP[i]) {
						DP[i] = DP[j]+1;
						answer = Math.max(answer, DP[i]);
					}
				}
				
			}
		}
		System.out.println(answer);
		
	}

}
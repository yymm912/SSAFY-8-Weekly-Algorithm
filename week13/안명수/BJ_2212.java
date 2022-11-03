package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2212 {
	static int N, K;
	static int sum;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		Arrays.sort(arr);
		for(int i = 0; i < N - 1; i++) 
			arr[i] = arr[i + 1] - arr[i];
		
		
		arr[N - 1] = Integer.MAX_VALUE;
		
		Arrays.sort(arr);
		for(int i = 0; i < N - K; i++) 
			sum += arr[i];
		
		
		System.out.println(sum);
	}
	

}

// 1 3 6 6 7 9
// 2 3 0 1 2
// 

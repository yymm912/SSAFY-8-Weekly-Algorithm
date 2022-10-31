package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2467용액 {

	static int N;
	static int inp[];
	static int ans1, ans2;
	static int sum;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		inp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inp[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 제일 왼쪽거랑 제일 오른쪽 비교 ?
		// 0보다 크거나 같으면 right-- 
		// 0보다 작으면 left++
		int left = 0;
		int right = N-1;
		
		// 99랑 98
		while(left < right) {
			int sum = inp[left] + inp[right];
			if( ans > Math.abs(sum) ) {
				ans = Math.abs(sum);
				ans1 = inp[left];
				ans2 = inp[right];
			}
			
			if(inp[left] + inp[right] >= 0) {
				right--;
			}
			else {
				left++;
			}
			
		}
		
		System.out.println(ans1 + " " + ans2);
		
	}

}

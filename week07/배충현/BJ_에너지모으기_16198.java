package ssafy;

import java.io.*;
import java.util.*;

public class BJ_에너지모으기_16198 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(stk.nextToken());
		}
		
		solve(0, 0, 0);
		System.out.println(ans);
	}
	
	static void solve(int cnt, int sum, int flag) {
		if (cnt == N-2) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for (int i = 1; i < N-1; i++) {
			if ((flag&1<<i) != 0) continue;
			int l = 0, r = 0;
			for (int j = 1; i-j >= 0; j++) {
				if ((flag&1<<i-j)!=0) continue;
				l = input[i-j];
				break;
			}
			for (int j = 1; i+j < N; j++) {
				if ((flag&1<<i+j)!=0) continue;
				r = input[i+j];
				break;
			}
			
			solve(cnt+1, sum+l*r, flag|1<<i);
		}
	}
}

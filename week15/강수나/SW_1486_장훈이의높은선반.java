package a22_11_12;

import java.io.*;
import java.util.*;

public class SW_1486_장훈이의높은선반 {

	static int N, B, ans;
	static int[] height;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			height = new int[N];
			isSelected = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			} //입력 끝
			
			ans = Integer.MAX_VALUE;
			subset(0, 0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void subset(int cnt, int sum) {
		if (sum>=B) ans = Math.min(ans, sum-B);
			
		if (cnt == N) {
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt+1, sum + height[cnt]);
		isSelected[cnt] = false;
		subset(cnt+1, sum);
	}
}

package baekjoon.공통부분문자열_5582;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int n = str1.length();
		int m = str2.length();
		int[][] map = new int[n+1][m+1]; // 0 dummy
		int ans = 0;
		
		for (int i = 1; i <= n; i++) {
			char c1 = str1.charAt(i-1);
			for (int j = 1; j <= m; j++) {
				char c2 = str2.charAt(j-1);
				if(c1 == c2) {
					map[i][j] = map[i-1][j-1]+1;
					ans = Math.max(ans, map[i][j]);
				}
			}
		}
		
		System.out.println(ans);
		
	}
}

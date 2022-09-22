package baekjoon.LCS_9251;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static String str1, str2;
	static int size1, size2;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		size1 = str1.length();
		size2 = str2.length();
		
		dp = new int[size1+1][size2+1]; // 0은 더미
		
		LCS();
		
		System.out.println(dp[size1][size2]);

	}
	
	static void LCS() {
		for (int i = 1; i <= size1; i++) {
			for (int j = 1; j <= size2; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
	}

}

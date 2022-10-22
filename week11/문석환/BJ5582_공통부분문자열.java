package BOJ.문자열.공통부분문자열_5582;

import java.util.Scanner;

// 제목 : 공통 부분 문자열
// 번호 : 5582
// 난이도 :
// https://www.acmicpc.net/problem/5582
/*
 * INFO
 * 두 문자열이 주어졌을 때 , 두 문자열에 모두 포함된 가장 긴 공통 부분 문자열을 찾는 프로그램을 작성하시오
 * s의 부분 문자열 t는 , s에 t가 연속으로 나타나는 것을 의미
 */
public class Main {
	static int[][] dp;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.nextLine();
		String B = sc.nextLine();
		int aLen = A.length();
		int bLen = B.length();

		dp = new int[aLen+1][bLen+1]; // a개의 문자로 이루어진 A 문자열 , b개의 문자로 이루어진 B 문자열
		// 0을 dummy로 준 이유 -> i-1과 j-1에 접근 했을 때 따로 조건을 설정해주지 않기 위해
		for(int i=1;i<=aLen;i++) {
			for(int j=1;j<=bLen;j++) {
				// A.charAt(i)의 문자와 B의 모든 문자를 비교
				if(A.charAt(i-1) == B.charAt(j-1)) { // 같으면 dp[i][j] = dp[i-1][j-1] + 1
					dp[i][j] = dp[i-1][j-1] + 1;
				}else {
					dp[i][j] = 0;
				}
				if(dp[i][j] > ans) {
					ans = dp[i][j];
				}
			}
		}

		System.out.println(ans);
	}
}

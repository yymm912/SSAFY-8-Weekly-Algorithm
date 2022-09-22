package week8.김정윤;

import java.io.*;
import java.util.*;

public class BJ9251_LCS {
	static char[] str1;
	static char[] str2;
	static Integer[][] cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 첫 번째 문자열
		str1 = sc.nextLine().toCharArray();
		// 두 번째 문자열
		str2 = sc.nextLine().toCharArray();
		cnt = new Integer[str1.length][str2.length];
		
		int ans = LCS(str1.length-1, str2.length-1);
		
		System.out.println(ans);
		
	}
	// Longest Common Subsequence
	static int LCS(int s1, int s2) {
		// 문자열 길이를 넘어가는 경우
		if(s1 == -1 || s2 == -1) {
			return 0;
		}
		// 탐색하지 않은 문자일 경우
		if (cnt[s1][s2] == null) {
			// 탐색 완료
			cnt[s1][s2] = 0;
			
			// 두 문자가 동일할 경우
			if (str1[s1] == str2[s2]) {
				// LCS(Xi, Yi) = LCS(Xi-1, Yi-1) + 1
				cnt[s1][s2] = LCS(s1-1, s2-1) + 1;
			} else { // 두 문자가 동일하지 않을 경우
				// LCS(Xi, Yi) = max(LCS(Xi-1, Yi), LCS(Xi, Yi-1))
				// 이전 원소 중 큰 값으로 채우기
				cnt[s1][s2] = Math.max(LCS(s1-1, s2), LCS(s1, s2-1));
			}
		}
		return cnt[s1][s2];
	}
}

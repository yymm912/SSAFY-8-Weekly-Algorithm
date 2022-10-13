package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_5582_공통부분문자열 {
	static int[][] lcs;
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		lcs = new int[str1.length+1][str2.length+1];

		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				if(str1[i]==str2[j]) {
					lcs[i+1][j+1] = lcs[i][j]+1;
					max=Math.max(max, lcs[i+1][j+1]);
				}
			}
		}

		System.out.println(max);
	}
}

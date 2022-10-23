import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String originalStr = br.readLine();
		char[] str = originalStr.toCharArray();
		/**
		 * KMP, 자기자신 KMP 적용
		 * 단, 탐색 문자열을 앞에서부터 하나씩 뻄
		 * */
		
		int max = 0;
		for(int searchbegin=0; searchbegin<str.length; searchbegin++) { // 앞자리 한글자씩자르면서 최장 "접두==접미"인 문자열 찾음
			int n = str.length, m = n-searchbegin;
			
			// KMP
			char[] searchstr = originalStr.substring(searchbegin).toCharArray();
			int[] match = new int[m];
			int len = getMatch(match, searchstr);
			max = Math.max(len, max);
						
		}
		System.out.println(max);
	}
	static int getMatch(int[] match, char[] str) {
		int begin = 1, matched = 0;
		int maxlen = 0;
		while(begin + matched < str.length) {
			if(str[matched] == str[begin + matched]) {
				matched++;
				match[begin + matched - 1] = matched;
				maxlen = Math.max(matched, maxlen);
			} else {
				if(matched == 0) {
					begin++;
				} else {
					begin += matched - match[matched-1]; // 현재 틀렸으니까, 바로 이전 맞았던 부분을 접두사와 일치시킴
					matched = match[matched-1];
				}
			}
		}
		return maxlen;
	}
}
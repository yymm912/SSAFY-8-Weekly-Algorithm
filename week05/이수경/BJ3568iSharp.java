package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ3568iSharp {

	static String inp;
	static String[] list;
	static String[] result;
	static String[] type;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

//		System.out.println(st.countTokens());
		int len = st.countTokens();
		list = new String[len];
		result = new String[len];
		type = new String[len];

		for (int i = 0; i < len; i++) {
			list[i] = st.nextToken();
		}
		// 맨 앞은 자료형.
		for (int i = 0; i < len; i++) {
			result[i] = list[0];
		}

		// 뒤에 있는 문자들을 자료형 바로 앞으로 옮기기

		// 뒤에 있는 자료형들만 뽑기
		int idx = 0; // 변수의 끝 위치 확인 
		for (int i = 1; i < len; i++) {

			// 뒤에 있는 문자들 반대로 저장
			for (int k = list[i].length() - 2; k >= 0; k--) {

				if (list[i].charAt(k) >= 'a' && list[i].charAt(k) <= 'z') {
					idx = k;
					result[i] += " ";
					break;
				}
				else if ( list[i].charAt(k) == ']' ) {
					
					result[i] += "[]";
					k--;
				}
				else {
					result[i] += list[i].charAt(k);
				}
				
				// 0 , 1,2,3...
			}
			
			// 변수 할당
			for(int k=0;k<=idx;k++) {
				result[i] += list[i].charAt(k);
			}
			
			
		}
		
		for (int i = 1; i < len; i++) {
			System.out.println(result[i] + ";");
		}

	}
}

/*
 * int& aaa*[]&, bbcd, cccccczzz*;
 * 
 * int&&[]* a; int& b; int&* c;
 */
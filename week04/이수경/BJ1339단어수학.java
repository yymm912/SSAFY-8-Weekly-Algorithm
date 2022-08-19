package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ1339단어수학 {

	static int N;
	static char alphabet[][];
	static String input[];
	static String Max;
	static int sum[] = new int[26]; // A~Z 까지 A:0, B:1 ...
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		alphabet = new char[N][8];
		input = new String[N];
		// 알파벳 대문자
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i] = st.nextToken();	
		}

		for(int i=0;i<N;i++) {
			
			for(int j=0;j<input[i].length();j++) { //개별 입력 1개씩 보기 
				char alpha = input[i].charAt(j);
				
				// 뒤에 Length 길이만큼 0 붙여주기 
				String zero_len = "1";
				for(int k=0; k < (input[i].length() - j) - 1 ;k++) {
					zero_len += "0";
				}
				
				sum[alpha-'A'] += Integer.parseInt(zero_len);
			
			}
		}
		
		// 내림차 정렬 후 순서대로 *9 , *8 ... 
//		Arrays.sort(sum, (o1, o2) -> o2 - o1); //왜빨간줄? -> String과 달리 int타입은 이렇 못함!
		
		Integer[] result = Arrays.stream(sum).boxed().toArray(Integer[]::new);
		Arrays.sort(result, Collections.reverseOrder());
		
		int n = 9;
		for(int i=0;i<result.length;i++) {
			ans += result[i] * n;
			n--;
		}
		
		System.out.println(ans);
		
	}
	

}

/*
2
GCF
ACDEB
*/
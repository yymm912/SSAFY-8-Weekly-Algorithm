package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_명령프롬프트_1032 {
	
	static int N;
	static String[] array;
	static String answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		array = new String[N];
		answer = "";
		
		for (int i = 0; i < N; i++) {
			array[i] = br.readLine();
		}
		
		for (int i = 0; i < array[0].length(); i++) {
			char c = array[0].charAt(i);
			boolean flag = true;
			for (int j = 1; j < N; j++) {
				if (array[j].charAt(i) != c) {
					flag = false;
					break;
				}
			}
			
			if (flag) answer += Character.toString(array[0].charAt(i));
			else answer += "?";
		}
		
		System.out.println(answer);
	}
}

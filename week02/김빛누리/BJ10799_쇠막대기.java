package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10799_쇠막대기 {
	static int index = 0;

	static void logic(String input) {
		int n = input.length();
		int answer = 0;
		int size = 0;
		
		for(int i = 0; i < n; i++) {
			char c = input.charAt(i);
			
			
			if(c=='(') {
				size++;
			}else {
				size--;
				if(input.charAt(i-1) == '(') {
					answer += size;
				}else answer++;
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();	
		logic(input);

	}

}

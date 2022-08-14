package ssafy.algorithm.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_쇠막대기_10799_3 {

	static char[] input;
	static int pipe, laser, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		pipe = 1;
		laser = 0;
		result = 0;
		
		for (int i = 1; i < input.length; i++) {
			if (input[i] == '(') {
				pipe++;
			} else {// )
				// 레이저 맞음
				if(input[i-1] == '(') {
					laser++; pipe--;
					result += pipe;
				} else {
					// 파이프 끝
					pipe--;
					result++;
				}
			}
		}
		
		System.out.println(result);

	}

}

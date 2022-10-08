package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW5658_보물상자비밀번호 {
	static int N, K;
	static TreeSet<String> set = new TreeSet<>(); 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// String으로 수 입력 받아서 맨 뒤에거 떼고 맨 앞에 붙임
			// 4개씩 자르기
			// 수 길이 / 4 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String input = br.readLine();
			
			int repeat = N/4;
			
			for (int i = 0; i < repeat; i++) {
				set.add(input.substring(0, N/4));
				set.add(input.substring(N/4, N/4*2));
				set.add(input.substring(N/4*2, N/4*3));
				set.add(input.substring(N/4*3, N));
				
				char c = input.charAt(N-1);
				input = c + input.substring(0, N-1);
			}
			
			for (int i = 0; i < K-1; i++) {
				set.pollLast();
			}
			String numK = set.pollLast();
			
			sb.append("#").append(t).append(" ").append(hexToTen(numK)).append("\n");
			set.clear();
		}
		System.out.println(sb.toString());

	}
	
	static int hexToTen(String hex) {
		int result = 0;
		for (int i = 0; i < hex.length(); i++) {
			result *= 16;
			
			char c = hex.charAt(i);
			int num = c >= 'A'? c-'A'+10 : c-'0';
			
			result += num;
		}
		
		return result;
		
	}

}

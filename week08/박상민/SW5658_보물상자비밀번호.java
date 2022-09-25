import java.io.*;
import java.util.*;

public class SW5658_보물상자비밀번호 {
	
	static int T, N, K;
	static char[] arr;
	static List<Integer> result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = br.readLine().toCharArray();
			
			// 결과리스트 (마지막에 정렬해서 값 출력)
			result = new ArrayList<>();
			for (int i = 0; i < N / 4; i++) {   // 3바퀴만 돌려 (12개 -> 3번)
				rotate();       // 돌려
				calc();         // 계산
			}
			
			Collections.sort(result, (n1, n2) -> n2 - n1);
			
			sb.append("#").append(t).append(" ").append(result.get(K - 1)).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
    // 돌려돌려
	static void rotate() {
		char temp = arr[N - 1];
		for (int i = N - 1; i > 0; i--) arr[i] = arr[i - 1];
		arr[0] = temp;
	}
	
    // 16진수 계산
	static void calc() {
		for (int i = 0; i < N; i += N / 4) {
			int p = N / 4 - 1;
			int sum = 0;
			for (int j = i; j < i + N / 4; j++) {
				sum += charToInt(arr[j]) * Math.pow(16, p--);
			}
			if (!result.contains(sum)) result.add(sum);
		}
	}
	
    // char -> int 변환함수 (16진수)
	static int charToInt(char c) {
		switch (c) {
			case 'A': return 10;
			case 'B': return 11;
			case 'C': return 12;
			case 'D': return 13;
			case 'E': return 14;
			case 'F': return 15;
		}
		return c-'0';
	}
}

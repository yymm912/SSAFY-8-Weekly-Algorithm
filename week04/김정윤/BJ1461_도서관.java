package SW;

import java.io.*;
import java.util.*;

public class BJ1461_도서관 {
	static int N, M, max = Integer.MIN_VALUE, ans = 0;;
	static int[] book;
	static Stack<Integer> s = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		book = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			book[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(book);

		// 분할정복
		if (Math.abs(book[0]) < Math.abs(book[N-1])) {
			// 음수
			for (int i = 0; i < N; i+=M) { // 큰수부터
				if (book[i] < 0) {
					s.add(Math.abs(book[i]));

				}
			}
			// 양수
			for (int i = N-1; i >= 0; i-=M) { // 작은수부터
				if (book[i] > 0) {
					s.add(Math.abs(book[i]));
				} 
			}
		}
		
		else {
			// 양수
			for (int i = N-1; i >= 0; i-=M) {
				if (book[i] > 0) {
					s.add(Math.abs(book[i]));
				}
			}
			// 음수
			for (int i = 0; i < N; i+=M) {
				if (book[i] < 0) {
					s.add(Math.abs(book[i]));
				} 
			}
		}
		for (Integer stack : s) {
			max = Math.max(stack, max);
			ans += stack * 2;
		}
		
		ans -= max; // 되돌아오는길 빼기
		System.out.println(ans);
	}

}

package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10816_숫자카드2 {

	static int N, M;
	static int[] card;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		card = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card);  // 이진탐색을 위한 정렬
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			sb.append(upper_bound(num) - lower_bound(num)).append(' ');
		}
		System.out.println(sb.toString());
	}

	// 찾는 값이 있는 범위 바로 직전에 위치한 작은 수 찾기
	static int lower_bound(int num) {
		int s = 0;
		int e = card.length;
		
		while(s < e) {
			int m = (s + e) / 2;
			
			if(num <= card[m]) e = m;
			else if(num > card[m]) s = m + 1;
		}
		
		return s;
	}
	
	// 찾는 값이 있는 범위 바로 직후에 있는 큰 수 찾기
	static int upper_bound(int num) {
		int s = 0;
		int e = card.length;
		
		while(s < e) {
			int m = (s + e) / 2;
			
			if(num < card[m]) e = m;
			else s = m + 1;
		}
		
		return s;
	}
	
}

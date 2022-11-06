package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1477휴게소세우기2 {

	static int N, M, L;
	static List<Integer> inp = new ArrayList<>();
	static int left, right, mid;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 이분 탐색 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		inp.add(0);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			inp.add(Integer.parseInt(st.nextToken()));
		}
		inp.add(L);
		// 비교 해야 할 것 : 0 ~ 휴게소, 휴게소 ~ 휴게소, 휴게소 ~ L
		
		Collections.sort(inp);
		
		left = 1;  
		right = L;
		
		while( left <= right ) {
			mid = (left + right) / 2;
			if(check()) left = mid + 1; // count가 M을 넘어가면 큰 범위 탐색 
			else right = mid - 1; // count 가 M을 넘어가지 않으면 작은 범위 탐색 
			
		}
		
		System.out.println(left);
			
	}
	static boolean check() {
		int count = 0;
		for (int i = 0; i < inp.size() - 1; i++) {
			int dist = (inp.get(i+1)-1) - (inp.get(i)); // 범위 계산 시 휴게소는 미포함 시키므로 -1 
			count += dist/mid; // dist: 휴개소의 간격, mid: 고속도로 길이
		}
		if(count > M) return true; // 배치할 수 있는 휴게소가 M개보다 많을 경우 
		else return false; // 배치할 수 있는 휴게소가 M개보다 적을 경우 
	}


}
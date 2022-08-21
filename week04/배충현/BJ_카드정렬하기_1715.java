package ssafy.algorithm.study.w04;

import java.io.*;
import java.util.*;

public class BJ_카드정렬하기_1715 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int ans = 0;
		
		while(pq.size()>1) {
			int a = pq.poll();
			int b = pq.poll();
			ans += a+b;
			pq.add(a+b);
		}
		
		System.out.println(ans);
		
	}
}

package ssafy;

import java.io.*;
import java.util.*;

public class BJ_행성터널_2887 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, cnt;
	static long ans;
	static int[] p;
	static List<int[]> input, el;
	static PriorityQueue<int[]> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		input = new ArrayList<>();
		el = new ArrayList<>();
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			input.add(new int[] {i, Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())});
		}
		
		sort();
		
		p = new int[N+1];
		for (int i = 0; i <= N; i++) p[i] = i;
		
		while(true) {
			int[] e = pq.poll();
			if (findset(e[0]) != findset(e[1])) {
				union(e[0], e[1]);
				ans += e[2];
				cnt++;
			}
			if (cnt == N-1) break;
		}
		
		System.out.println(ans);
		
	}
	
	static void union(int x, int y) {
		int a = findset(x);
		int b = findset(y);
		// 큰 애가 대표
		if (a > b) {
			p[b] = a;
		} else {
			p[a] = b;
		}
	}
	
	static int findset(int x) {
		if (x == p[x]) return x;
		return p[x] = findset(p[x]);
	}
	
	static void sort() {
		pq = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]);
		
		Collections.sort(input, (p1, p2) -> p1[1] - p2[1]);
		for (int i = 0; i < N-1; i++) {
			pq.add(new int[] {input.get(i)[0], input.get(i+1)[0], input.get(i+1)[1] - input.get(i)[1]});
		}
		
		Collections.sort(input, (p1, p2) -> p1[2] - p2[2]);
		for (int i = 0; i < N-1; i++) {
			pq.add(new int[] {input.get(i)[0], input.get(i+1)[0], input.get(i+1)[2] - input.get(i)[2]});
		}
		
		Collections.sort(input, (p1, p2) -> p1[3] - p2[3]);
		for (int i = 0; i < N-1; i++) {
			pq.add(new int[] {input.get(i)[0], input.get(i+1)[0], input.get(i+1)[3] - input.get(i)[3]});
		}
		
	}
}

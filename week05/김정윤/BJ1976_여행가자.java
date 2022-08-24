package week5.김정윤;

import java.io.*;
import java.util.*;

public class BJ1976_여행가자 {
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		
		makeSet();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// 1:연결 O, 0: 연결 X
				int tmp = Integer.parseInt(st.nextToken());
				
				if (tmp == 1) { // 연결되어있는 경우 합치기
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int start = findSet(Integer.parseInt(st.nextToken())); // 시작지점
		for (int i = 1; i < M; i++) {
			int now = Integer.parseInt(st.nextToken()); // 다음 지점
			if (start != findSet(now)) { // 연결되어있지 않음
				System.out.println("NO");
				return;
			}
		}
		// 연결되어 있음
		System.out.println("YES");
		
	}
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	static int findSet(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px < py) parent[py] = px;
		else parent[px] = py;
	}

}

package baekjoon.팀결성_1717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m; // n개의 집합, m개의 연산
	static int[] parent;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 초기화
		parent = new int[n+1]; // 0 dummy
		makeSet();
		
		sb = new StringBuilder(); // #1 _		
		// 풀이
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(op==0) {
				union(x,y);
			}else if(op == 1) {
				if(findSet(x) == findSet(y)) sb.append("YES").append('\n');
				else sb.append("NO").append('\n');
			}
		}
		System.out.println(sb.toString());

	}
	
	// 서로소 집합 관련 메소드들
	static void makeSet() {
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}

	// 부모를 찾는(부모 번호 == 집합)
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
}
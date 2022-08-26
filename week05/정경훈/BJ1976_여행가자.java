package baekjoon.여행가자_1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드-와샬로도 풀립니다.

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1]; // 0 dummy
		MakeSet();
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) union(i,j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int size = st.countTokens();
		int check = parent[Integer.parseInt(st.nextToken())];
		for (int i = 1; i < size; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(parent[num] != check) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}

	static void MakeSet() {
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static void union(int x, int y) {
		int py = findSet(y);
		int px = findSet(x);
		if(py > px) parent[py] = px;
		else parent[px] = py;
	}
}

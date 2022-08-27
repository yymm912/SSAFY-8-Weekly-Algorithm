package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1976_union_find {
	static int N, M;
	static int[][] conn;
	static int[] tgt;
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		conn = new int[N + 1][N + 1];
		parent = new int[N + 1];
		
		StringTokenizer stk;
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			stk = new StringTokenizer(br.readLine());
			for(int j = 1;j <= N; j++) {
				conn[i][j] = Integer.parseInt(stk.nextToken());
				if(conn[i][j] == 0) conn[i][j] = 100_000_000;
			}
		}
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(conn[i][j] == 1) union(i, j);
			}
		}
		
		tgt = new int[M];
		stk = new StringTokenizer(br.readLine());	
		for(int i = 0; i < M; i++)
			tgt[i] = Integer.parseInt(stk.nextToken());
		
		for(int i = 0; i < M - 1; i++) {
			if(find(tgt[i]) != find(tgt[i + 1])) {
				System.out.println("NO");
				return;
			}
		}
		
		
		System.out.println("YES");
	}
	
	static int find(int num) {
		if(parent[num] == num) return num;
		return parent[num] = find(parent[num]);
	}
	
	static void union(int A, int B) {
		A = find(A);
		B = find(B);
		
		if(A == B) return;
		if(A > B) parent[A] = B;
		else parent[B] = A;
	}
}

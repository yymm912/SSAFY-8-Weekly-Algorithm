package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1976_여행가자 {
	static int[] parent;
	static boolean[][] adj;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
			
		parent = new int[N+1];
		for(int i = 1; i < N; i++) {
			parent[i] = i;
		}
		
		adj = new boolean[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		
		boolean trip = true;
		st = new StringTokenizer(br.readLine());
		int before = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M-1; i++) {
			int curr = Integer.parseInt(st.nextToken());
			
			if(!check(before, curr)) trip = false;
		}
		
		if(!trip) System.out.println("NO");
		else System.out.println("YES");
		
	}
	static boolean check(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return true;
		return false;
	}
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return;
		
		parent[bRoot] = aRoot;
	}
	static int find(int a) {
		if(a == parent[a]) return a;
		
		return parent[a] = find(parent[a]);
	}

}

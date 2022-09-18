

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산 {

	static List<List<Integer>> parent = new ArrayList<> ();
	static int V, E, ans, r1, r2;
	static int p1, p2, min;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(br.readLine());
		visited = new boolean[V+1];

		for (int i = 0; i <= V; i++) {
			parent.add(new ArrayList<> ());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			parent.get(a).add(b);
			parent.get(b).add(a);
		} //입력 끝
		
		min = Integer.MAX_VALUE;
		dfs(p1, 0);
		if (min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	static void dfs(int node, int cnt) {
		if (node == p2) { //도착
			min = Math.min(min, cnt);
			return;
		}
		
		visited[node] = true;
		int len = parent.get(node).size();
		for (int i = 0; i < len; i++) {
			int n = parent.get(node).get(i);
			if(visited[n]) continue;
			dfs(n, cnt+1);
			visited[node] = false;
		}
	}
}

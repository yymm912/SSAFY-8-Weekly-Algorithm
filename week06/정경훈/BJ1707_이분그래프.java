package baekjoon.이분그래프_1707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, V, E;
	static boolean[] color;
	static boolean[] visit;
 	static List<Integer>[] list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		for (int t = 1; t <= K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			list = new List[V+1];
			color = new boolean[V+1];
			visit = new boolean[V+1];
			boolean ok = true;
			for (int i = 0; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				list[v1].add(v2);
				list[v2].add(v1);
			}
			
			for (int i = 1; i <= V; i++) {
				if(!visit[i]) {
					if(!search(i)) {
						ok = false;
						break;
					}
				}
			}
			if(ok) System.out.println("YES");
			else System.out.println("NO");
			
		}
	}
	
	static boolean search(int s) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(s);
		color[s] = true;
		visit[s] = true;
		while(!q.isEmpty()) {
			int n = q.poll();
			
			for (int x : list[n]) {
				if(!visit[x]) { // 첫방문일 때
					visit[x] = true;
					color[x] = !color[n];
					q.add(x);
				}else {
					if(color[x] == color[n]) return false;
				}
			}
		}
		
		return true;
	}
}

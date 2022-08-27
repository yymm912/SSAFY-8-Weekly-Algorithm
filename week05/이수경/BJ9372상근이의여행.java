package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Kruskal
public class BJ9372상근이의여행 {

	static int T, N, M, a,b;
	static int parent[];
	static Edge edge[];
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			edge = new Edge[M];
			flag = false;
			makeSet();
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				edge[i] = new Edge(a,b);
			}
			
			int cnt = 0;
			for(int i=0;i<M;i++) {
				
				union(edge[i].a, edge[i].b);
				cnt++;
				
				if(cnt == N-1) break;
				
			}
			
			
			System.out.println(cnt);
		}
		
		
		
	}

	static class Edge {
		int a, b;

		public Edge(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
	}
	static void makeSet() {
		for(int i=1;i<=N;i++)parent[i] = i;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find( parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if( px == py )return false;
		
		if( px < py ) parent[py] = px;
		else parent[px] = py;
		return true;
	}
}

package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Kruskal
public class BJ1197최소스패닝트리 {

	static int V, E;
	static int parent[];
	static Edge edge[];
	static long sum;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V+1];
		edge = new Edge[E];
		makeSet();
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
				
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edge[i] = new Edge(a,b,c);
		}
		
		Arrays.sort(edge, (a,b)->a.value-b.value); // 가중치 작은거부터 오름차순 정렬 
		
		sum = 0;
		for(int i=0;i<E;i++) {
			
			if(union(edge[i].a, edge[i].b)) {
				sum += edge[i].value;
				cnt++;
			}
			
			if( cnt == V-1) break;
		}
		
		System.out.println(sum);
		
	}
	
	static class Edge {
		int a, b, value;

		public Edge(int a, int b, int value) {
			super();
			this.a = a;
			this.b = b;
			this.value = value;
			
		}
		
	}
	static void makeSet() {
		for(int i=1;i<=V;i++)parent[i] = i;
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

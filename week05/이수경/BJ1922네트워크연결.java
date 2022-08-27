package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Prim
public class BJ1922네트워크연결 {

	static int N, M;
	static PriorityQueue<Edge> pq = new PriorityQueue<>( (a,b)-> a.value-b.value); // 가중치 작은 순 
	static List<Edge> edge[];
	static boolean visit[];
	static long sum;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visit = new boolean[N+1];
		edge = new ArrayList[N+1];
		for(int i=0;i<=N;i++) edge[i] = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edge[a].add(new Edge(b,c));
			edge[b].add(new Edge(a,c));
		}
		
		
		// 첫 시작 1부터
		pq.addAll( edge[1] );
		visit[1] = true;
		
		while( ! pq.isEmpty()) {
			
			Edge e = pq.poll();
			
			if(visit[e.v]) continue;
			visit[e.v] = true;
			sum += e.value;
			cnt++;
			
			if(cnt == N-1) break;
			pq.addAll(edge[e.v]);
			
			
		}

		System.out.println(sum);
		
		
	}
	
	static class Edge {
		int v, value;

		public Edge(int v, int value) {
			super();
			this.v = v;
			this.value = value;
		}

		
		
	}

}

package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// prim 방법 
public class BJ17472다리만들기2_prim {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int matrix[][];
	static int islandCnt;
	static int cost[][];
	static int min = Integer.MAX_VALUE;
	static int parent[];
	static int sum;
	static PriorityQueue<Node> pq = new PriorityQueue<>( (e1, e2) -> e1.dist - e2.dist); 
	static List<Node> edge[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료 
		
	
		// 섬의 개수 세기
		islandCnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visit[i][j] ) {
					dfs(i, j, islandCnt);
					islandCnt++;
				}
			}
		}
		
		// 섬과 섬 사이의 최단거리 구하기 
		matrix = new int[islandCnt][islandCnt];
		cost = new int[islandCnt][islandCnt];
		edge = new ArrayList[islandCnt];
		for(int i=1;i<islandCnt;i++) edge[i] = new ArrayList<>();
		
		for (int i = 1; i < islandCnt; i++) {
			for (int j = 1; j < islandCnt; j++) {
				if(i==j) continue;
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// BFS로 탐색 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0) {
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		// prim
		prim();
	
		if(pq.size() == 0) System.out.println(-1);
		else System.out.println(sum);
	}
	
	static void prim() {
		
		boolean visited[] = new boolean[islandCnt];
		int n = 0;
		// 해당 정점 주변에 있는 모든 정점들 추가하면서 제일 작은 것만 연결! 
		// 1번 섬부터 시작 
		
		pq.addAll(edge[1]);
		visited[1] = true;
		while(!pq.isEmpty()) {
			
			if( islandCnt - 2 == n ) break;
			
			Node e = pq.poll();
			
			if(!visited[e.v]) { // 사이클 없을 때만 
				pq.addAll(edge[e.v]);
				visited[e.v] = true;
				sum += e.dist;
				n++;
			}
			
		}
		
		
	}
	
	static void addEdge(int v1, int v2, int cost) {
	    boolean same = false;
	    
	    for (Node edge : edge[v1] ) {
	        
	        if( edge.v == v2 ) {
	            same = true;
	            edge.dist = Math.min(edge.dist, cost);
	            break;
	        }
	    }
	    
	    if( !same) edge[v1].add(new Node(v2, cost));
	}
	
	static void bfs(int y, int x, int idx) { // idx는 섬 번호 
		
		// 섬과 섬 사이 최단거리 구하기 
		Deque<Dist> q = new ArrayDeque<>();
		visit = new boolean[N][M];
		for (int d = 0; d < 4; d++) {
			q.offer(new Dist(y, x, 0));
			visit[y][x] = true;
			
			while(!q.isEmpty()) {
				
				Dist e = q.poll();
				int py = e.y + dy[d];
				int px = e.x + dx[d];
				
				if(py < 0 || px < 0 || py >= N || px >= M || visit[py][px] || map[py][px] == idx) continue;
				
				if(  map[py][px] != 0) {
					if( e.dist > 1 ) {
//						addEdge(idx, map[py][px], e.dist);
						edge[idx].add(new Node(map[py][px], e.dist));
						break;
					}
					
				}
				else {
					visit[py][px] = true;
					q.offer(new Dist(py, px, e.dist + 1));
				}
				
			}
			
			
			q.clear(); // 방향마다 최소값이 다르므로 
		}
		
	}
	
	
	
	static void dfs(int y, int x, int num) {
		
		if(visit[y][x]) return;
		visit[y][x] = true;
		map[y][x] = num;

		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || px < 0 || py >= N || px >= M || map[py][px] == 0 || visit[py][px] ) continue;
			
			dfs(py, px, num);
			
		}
		
	}
	
	static class Node {
		int v, dist;

		public Node(int v, int dist) {
			super();
			this.v = v;
			this.dist = dist;
		}
		
	}
	
	static class Dist {
		int y, x, dist;

		public Dist(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		
	}
	
	
	

}

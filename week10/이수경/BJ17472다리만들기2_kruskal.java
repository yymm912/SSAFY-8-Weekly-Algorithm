package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// kruskal 방법 
public class BJ17472다리만들기2_kruskal {

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
	static PriorityQueue<Dist> pq = new PriorityQueue<>( (e1, e2) -> e1.dist - e2.dist); 
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
		
		
		// 색칠되어있는 칸은 땅 
		
		// 다리는 바다에만 건설 가능, 다리의 길이는 다리가 격자에서 차지하는 칸 수 
		// A에서 다리를 통해 B로 갈 수 있을 때, A B 는 연결. 다리 길이는 2 이상, 다리의 방향은 중간에 바뀌면 안됌 
		// 다리 방향은 가로, 세로 
		
		// 모든 섬을 연결하는 다리 길이의 최솟값 
		
		
		
		// 다리의 개수가 섬의 개수 - 1 를 만족하면 종료 
		
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
		
		// kruskal
		kruskal();
	
		if(pq.size() == 0) System.out.println(-1);
		else System.out.println(sum);
	}
	
	static void kruskal() {
		
		makeSet();
		
		int n = 0; // 연결 된 간선의 개수 
		while(!pq.isEmpty()) {
			if( n == islandCnt - 2 ) break;
			Dist e = pq.poll();
			int u = e.y;
			int v = e.x;
			
			if(  union(u,v) ) { // 사이클이 없을 때만 
				sum += e.dist;
				n++; 
			}
				
		}
		
		
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
						pq.add(new Dist(idx, map[py][px], e.dist));
						cost[idx][map[py][px]] = Math.min(cost[idx][map[py][px]], e.dist );
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
	
	static int find(int x) {
		if( x == parent[x] ) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if( a == b ) return false; // 이미 연결되어 있다면 false (사이클)
		if( a != b )parent[b] = a;
		
		return true;
	}
	static void makeSet() {
		parent = new int[islandCnt];
		for (int i = 1; i < islandCnt; i++) {
			parent[i] = i;
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

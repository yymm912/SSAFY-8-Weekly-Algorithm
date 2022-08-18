package algo_study._8월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs, dfs
 * 0:구멍, 1:칸막이
 * -------------
 * 시간차이?
 * bfs: 1.6621ms/ 2.5231ms 		(bfs: 인접노드 먼저 -> 최단 경로 찾을 때)
 * dfs: 0.03ms/ 0.1691ms => 유리 	(dfs: 한방향으로 갈 수 있을 때까지 -> 해당 분기를 완벽하게 탐색할 때)
 * */
public class TC_음료수얼려먹기 {
	
	static int COUNT, N, M;
	static int[][] map;
	static Queue<Node> queue = new ArrayDeque<>();
	static boolean[][] visited;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		long start = System.nanoTime(); //시간 add (시작)
		//한점 한점 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 0) { //방문한적 없고 값이 0일 때만
					bfs(i, j);
					//dfs(i, j);
					COUNT++; //bfs 수행 횟수 == 아이스크림 개수
				}
			}
		}
		long end = System.nanoTime(); //시간 add (끝)
		System.out.println("시간:"+(end-start)*0.000_001 + "ms"); //시간 add (끝-시작)
		
		System.out.println(COUNT);
	}
	
	//1
	static void bfs(int y, int x) {
		queue.offer(new Node(y, x)); //첫 노드 큐에 넣고 시작
		visited[y][x] = true; //방문체크
		
		while (!queue.isEmpty()) {
			Node node = queue.poll(); //맨 앞 노드 빼줌
			for (int d = 0; d < 4; d++) { //사방탐색
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 1) continue;
				//범위 밖,방문한적 O, 값이 1이면 안본다
				queue.offer(new Node(ny, nx)); //조건에 맞으면 큐에 인접 노드 추가
				visited[ny][nx] = true; //방문체크
			}
		}
	}
	
	//2
	static void dfs(int y, int x) {
		visited[y][x] = true; //방문체크
		for (int d = 0; d < 4; d++) { //사방탐색
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 1) continue;
			dfs(ny, nx);
		}
	}
	
	static class Node {
		int x, y;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

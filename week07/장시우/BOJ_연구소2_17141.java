package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_연구소2_17141 {
	
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] checked;
	static Queue<Node> q = new ArrayDeque<>();
	
	// 바이러스가 들어갈 수 있는 위치(2)를 저장
	static List<Node> virus = new ArrayList<>();
	
	// 조합으로 뽑힌 바이러스들을 저장한 tgt배열
	static Node[] tgt;
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		tgt = new Node[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 바이러스의 위치를저장
				if (map[i][j] == 2) virus.add(new Node(i, j));
			}
		}
		
		// 조합을 사용해서 전체 바이러스 중 M개만 뽑아 퍼지는 시간을 갱신한다.
		comb(0, 0);
		
		// 바이러스가 다 찬적이 없으면 ans == -1
		if (ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			// 바이러스를 놓을 장소 M개를 정한 후
			// checked와 bfs로 바이러스가 퍼지는 시간을 구한다.
			checked = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				checked[tgt[i].x][tgt[i].y] = true;
				q.add(tgt[i]);
			}
			
			bfs();
			
			return;
		}
		
		if (srcIdx == virus.size()) {
			return;
		}
		
		tgt[tgtIdx] = virus.get(srcIdx);
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static void bfs() {
		int count = 0;
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dx[d];
					int ny = n.y + dy[d];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && !checked[nx][ny] && map[nx][ny] != 1) {
						checked[nx][ny] = true;
						q.add(new Node(nx, ny));
					}
				}
			}
			if (!q.isEmpty()) count++;
		}
		
		// 바이러스가 다 퍼졌는지 체크한다.
		boolean isSpreadAll = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 1 && !checked[i][j]) {
					isSpreadAll = false;
					break;
				}
			}
			
			if (!isSpreadAll) {
				break;
			}
		}
		
		// 바이러스가 다 퍼지지 않았다면 가장 큰 값을 넣는다.
		if (!isSpreadAll) count = Integer.MAX_VALUE;
		
		ans = Math.min(ans, count);
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

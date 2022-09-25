package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
=======
>>>>>>> 29cb3cd5c1915c469078fbca7107e904ee976418
import java.util.StringTokenizer;

public class BOJ_인구이동_16234 {
	
	static int N, L, R, ans, unionSum, cnt;
	static int[][] map;
	static boolean[][] checked;
	static Queue<Node> q = new ArrayDeque<>();
	static Set<Node> union = new HashSet<>();
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N, L, R;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// bfs를 사용해 연합을 구해 hashSet에 좌표를 저장한다.
		while (true) {
			checked = new boolean[N][N];
			cnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!checked[i][j]) {
						checked[i][j] = true;
						union.add(new Node(i, j));
						q.add(new Node(i, j));
						unionSum = map[i][j];
						bfs();
						// 인구를 재분배한다.
						redistribution();
						
						cnt++;
					}
				}
			}
			
			// 인구이동이 불가능하면 종료한다.
			if (cnt >= N * N) break;
			
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static void redistribution() {
		int newPopulation = unionSum / union.size();
		Iterator<Node> itr = union.iterator();
		while(itr.hasNext()) {
			Node n = itr.next();
			int x = n.x;
			int y = n.y;
			
			map[x][y] = newPopulation;
			
		}
		
		union.clear();
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			int x = n.x;
			int y = n.y;
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && canMakeUnion(x, y, nx, ny) && !checked[nx][ny]) {
					checked[nx][ny] = true;
					Node next = new Node(nx, ny);
					q.add(next);
					union.add(next);
					unionSum += map[nx][ny];
				}
			}
		}
	}
	
	static boolean canMakeUnion(int x, int y, int nx, int ny) {
		int d = Math.abs(map[nx][ny] - map[x][y]);
		
		if (d >= L && d <= R) return true;
		else return false;
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Node) {
				Node n = (Node) o;
				if (this.x == n.x && this.y == n.y) return true;
				return false;
			}
			
			return true;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}

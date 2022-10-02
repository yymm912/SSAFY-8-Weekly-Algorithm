package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀의 이동경로 => queue
// 사과의 위치 => N*N 배열에 표시
// 방향 전환 정보 => Map<int, String>
// 이동할 때마다 1초씩 증가
// 방향전환 => 상 우 하 좌
public class BJ3190_뱀 {

	static int N, K, L, D=1, cnt;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Map<Integer, String> turn = new HashMap<>();
	static Queue<Node> qu = new ArrayDeque<Node>();
	
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// qu.contains() 를 사용하기 위해 ..
		@Override
		public boolean equals(Object o) {
			if(o != null && o instanceof Node) {
				Node node = (Node)o;
				if(this.x == node.x && this.y == node.y) return true;
				return false;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		K = Integer.parseInt(br.readLine()); // 사과 = 2
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			
			turn.put(sec, dir);
		}
		
		play();
		
		System.out.println(cnt);
	}

	static void play() {
		qu.offer(new Node(0, 0));
		cnt = 1;
		int px = 0;
		int py = 0;
		
		while(true) {
								
			int tx = px + dx[D];
			int ty = py + dy[D];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N) break; // 벽에 부딪히면 게임 종료
			if(qu.contains(new Node(tx, ty))) break; // 몸에 부딪히면 게임 종료
			
			// 뱀 이동 시작
			if(map[tx][ty] == 2) { // 사과를 만날 때는 꼬리를 빼지 않는다
				map[tx][ty] = 0;
				qu.add(new Node(tx, ty));
			} else {
				qu.add(new Node(tx, ty));
				qu.poll();
			}
			
			if(turn.get(cnt) != null) { // 현재 초 뒤에 방향을 바꿔야 하는지 확인 
				String dir = turn.get(cnt);
				if(dir.equals("L")) { // 왼쪽
					D -= 1;
					if(D < 0) D = 3;
				} else if(dir.equals("D")) { // 오른쪽
					D = (D+1) % 4;
				}
			}
			
			px = tx;
			py = ty;
			cnt++;
		}
	}
}

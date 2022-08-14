package forStudy.week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234_인구이동 {

	static int N, L, R;
	static int day; // 인구이동 일 수
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, -1, 1};
	static List<Node> union;
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
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
		
		day = 0;
		
		// 인구이동이 완료될 때까지 계속 반복하면서 모든 노드를 방문해야 함
		while(true) {
			visited = new boolean[N][N];
			int moved = 0;
			// 모든 노드 방문 처리
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					moved += move(i, j);
				}
			}
			// 하루동안 인구이동이 이루어진 뒤 확인 작업
			// -> 하루동안 인구이동이 이루어지지 않았다면 끝난 것
			if(moved == 0) break;
			day++;
		}
		System.out.println(day);
	}

	static int move(int x, int y) {
		union = new ArrayList<>();
		Queue<Node> qu = new ArrayDeque<>();
	
		Node s = new Node(x,y); // 첫 노드에 대한 처리
		qu.offer(s);
		union.add(s);
		visited[x][y] = true;
		int sum = map[x][y]; // 연합의 인원수 누적
		
		// 하나의 연합(조건에 맞는 인접국가들) 구하기
		while( !qu.isEmpty() ) {
			Node node = qu.poll();
			for(int d=0; d<4; d++) {
				int tx = node.x + dx[d];
				int ty = node.y + dy[d];

				if(tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty]) continue;
				int condi = Math.abs(map[node.x][node.y] - map[tx][ty]);
				if(L > condi || condi > R) continue;
				Node tNode = new Node(tx, ty);
				union.add(tNode);
				qu.offer(tNode);
				visited[tx][ty] = true;
				sum += map[tx][ty];
			}
		}
		
		// 구한 연합에 대한 인구이동 진행
		if(union.size() == 1) return 0; // 연합이 이루어지지 않음
		int div = sum / union.size();
		for(Node n : union) {
			map[n.x][n.y] = div;
		}
		return 1;
	}
	
}

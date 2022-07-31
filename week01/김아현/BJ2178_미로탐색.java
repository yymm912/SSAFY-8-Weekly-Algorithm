package forStudy.week01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BJ2178_미로탐색 {

	static int N, M;
	static int[][] miro;	// 미로를 저장할 배열
	static int[][] dist;	// 이동거리를 담을 배열
	static int[] dx = {0, 1, 0, -1}; // 동-남-서-북
	static int[] dy = {1, 0, -1, 0};
	static Queue<Pair> qu = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		miro = new int[N][M];
		dist = new int[N][M];
		for(int i=0; i<N; i++) {
			String line = sc.next();
			for(int j=0; j<M; j++) {
				miro[i][j] = line.charAt(j)-'0';
				dist[i][j] = -1;	// 거리를 -1로 세팅하면, 방문여부 확인 가능
			}
		}
		
		qu.offer(new Pair(0,0)); // 시작좌표
		dist[0][0] = 1;		// 시작점도 이동거리에 포함되므로 1로 설정
		
		while(!qu.isEmpty()) {
			Pair p = qu.poll();
			
			for(int i=0; i<4; i++) {
				int tx = p.x + dx[i];
				int ty = p.y + dy[i];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;	// 좌표이탈확인
				if(miro[tx][ty] == 0 || dist[tx][ty] != -1) continue;	// 벽이거나 방문했으면 패스
				
				qu.offer(new Pair(tx, ty));
				dist[tx][ty] = dist[p.x][p.y] + 1;	// 이전 위치보다 1칸 이동
			}
		}
		
		System.out.println(dist[N-1][M-1]); // 목적지 좌표의 이동거리값 출력
		sc.close();
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
}

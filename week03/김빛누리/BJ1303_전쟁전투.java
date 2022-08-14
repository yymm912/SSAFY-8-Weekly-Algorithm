package week03;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
 * 메모리: 17564 KB
 * 시간: 172 ms
 * */
public class BJ1303_전쟁전투 {
	static int N, M, wPower, bPower;	// wCnt: 아군, bCnt: 적군
	static char[][] map;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new char[M][];
		visit = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(visit[i][j]) continue;
				char color = map[i][j];
				
				int cnt = bfs(i, j, color);	// y, x 순서
				
				// 아군, 적군 위력 계산 (인원수의 제곱)
				if(color == 'W') wPower += cnt*cnt;
				else bPower += cnt*cnt;
			}
		}
		
		// 출력
		System.out.println(wPower + " " + bPower);
		
	}
	static int bfs(int y, int x, char color) {
		Queue<Point> q = new ArrayDeque<>();	// 위치정보 넣을 큐
		
		// 초기 세팅
		q.add(new Point(x,y));
		visit[y][x] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			x = p.x;
			y = p.y;
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (ny < 0 || nx < 0 || ny >= M || nx >= N || visit[ny][nx] || map[ny][nx] != color)
					continue;
				
				// 같은 색 방문하며 인원수++, 큐에 넣기
				q.add(new Point(nx, ny));
				visit[ny][nx] = true;
				cnt++;
				
			}
		}
		return cnt;
	}
}
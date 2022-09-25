import java.io.*;
import java.util.*;

// 시간당 1의 거리를 움직임
// 탈주범이 있을 수 있는 위치의 개수를 계산
// 맨홀뚜껑은 시작점
// 다음 갈곳 탐색 후 answer++
public class SW1953_탈주범검거 {
	static int T, N, M, R, C, L, answer;
	static int[][] map;
	
	static class P{
		int y;
		int x;
		int d;
		public P (int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			bfs();
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
    // 파이프마다 이동할 수 있는 델타 배열
    // 1 ~ 7
	static int[][] dy = {{0}, {1, -1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
	static int[][] dx = {{0}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
	static void bfs() {
		Queue<P> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		
		q.offer(new P(R, C, 1));
		v[R][C] = true;
		
		while(!q.isEmpty()) {
			P now = q.poll();

			answer++;                           // 이동 count++
			if (now.d == L) continue;			
						
			int pipe = map[now.y][now.x];       // 현재 위치 파이프 번호
			
			for (int i = 0; i < dy[pipe].length; i++) {     // 현재 위치 파이프에서 갈 수 있는 경로 다 탐색
				int nextY = now.y + dy[pipe][i];
				int nextX = now.x + dx[pipe][i];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX]) continue;
				if (map[nextY][nextX] == 0) continue;
				if (!check(now.y, now.x, nextY, nextX)) continue;       // 다음 파이프와 이어져있는지 확인 -> 이어지지 못하면 패스
				
				q.offer(new P(nextY, nextX, now.d + 1));
				v[nextY][nextX] = true;                                 // 방문 찍고
			}
		}
	}
	
    // 다음 파이프 연결가능여부 확인함수
	static boolean check(int y, int x, int ny, int nx) {
		int pipe = map[ny][nx];		
		for (int i = 0; i < dy[pipe].length; i++) {
			int nextY = ny + dy[pipe][i];
			int nextX = nx + dx[pipe][i];
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
			if (nextY == y && nextX == x) return true;		
		}
		return false;
	}
}
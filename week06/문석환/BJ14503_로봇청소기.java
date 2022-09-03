package BOJ.구현.로봇청소기_14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호 : 14503
// 난이도 : 골드 5
// 제목 : 로봇청소기
// https://www.acmicpc.net/problem/14503

// NxM 직사각형의 map
// 각각의 칸은 벽 또는 빈 칸
// 청소기는 동,서,남,북 중 한 곳을 바보고 있음
// 로봇 청소기 작동 원리
// 1. 현재 위치를 청소
// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행 (현재 위를 보고 있으면 왼쪽으로)
// 2.1 왼쪽 방향에 청소 하지 않은 공간 있다면 그 방향으로 회전 -> 1칸 전진 -> 2번부터 다시 반복
// 2.2 왼쪽 방향 청소되어 있으면, 그 방향으로 회전만하고 2번부터 다시 반복
// 2.3 네 방향 모두 청소 되어 있거나, 벽인 경우에는 , 바라보는 방향을 유지한 채로 한칸 후진 하고 2번부터 다시 반복
// 2.4 네방향 모두 청소 되어 있거나, 벽인 경우 + 바라보는 방향을 기준으로 후진 하는 곳도 벽이면, 작동을 멈춤 (기저 조건)
public class Main {
	static int R,C,ans;
	static int[][] map;
	static int cy,cx,cd;
	static boolean[][] visit;
	static Cleaner c;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 북 , 동 , 남 , 서
	// 북 -> 서 -> 남 -> 동 -> 북 -> 서 -> 남
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		cy = Integer.parseInt(st.nextToken());
		cx = Integer.parseInt(st.nextToken());
		cd = Integer.parseInt(st.nextToken());
		c = new Cleaner(cy,cx,cd);
		map = new int[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// map에서 0이면 빈칸 , 1이면 벽
		// map의 가장자리는 모두 항상 벽
		// 0 : 북쪽 , 1 : 동쪽 , 2 : 남쪽 , 3 : 서쪽
		ans = 1; // 현재 위치는 항상 청소 할 수 있는 칸이기 때문에
		visit  = new boolean[R][C];
		visit[c.y][c.x] = true; // 청소 완료
		pro();

		System.out.println(ans);
	}

	static void pro() {
		int cnt = 0;
		while(true) {
			if(cnt == 4) {
				// 네방향 다 체크 했는데 못가는 경우
				// 후진
				// 현재 보고 있는 곳 반대 방향이 0이고 방문하지 않은 곳이면 후진 가능 (방향은 유지)
				int nDir = c.dir;
				if(nDir > 1) {
					nDir -= 2;
				}else {
					nDir += 2;
				}

				int ny = c.y + dir[nDir][0];
				int nx = c.x + dir[nDir][1];

				if(map[ny][nx] == 0) {
					// 후진 가능
					c.y = ny;
					c.x = nx;
					cnt = 0;
					continue;
				}else {
					break;
				}
				// 아니면 후진 불가
			}
			c.dir = c.dir - 1 < 0 ? 3 : c.dir - 1; // 왼쪽으로 회전
			int ny = c.y + dir[c.dir][0];
			int nx = c.x + dir[c.dir][1];
			if(map[ny][nx] == 1 || visit[ny][nx]) {
				cnt++;
				continue;
			}
			// 갈 예정인 좌표
			// 갈 수 있으면
			c.y = ny;
			c.x = nx;
			visit[c.y][c.x]= true;
			ans++;
			cnt = 0;

		}
	}



	static class Cleaner{
		int y,x,dir;
		public Cleaner(int y,int x,int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
}

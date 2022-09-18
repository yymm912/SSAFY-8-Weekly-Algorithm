package SWEA.모의SW역량테스트.탈주범검거_1953;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호   : 1953
// 난이도 : 모의 SW 역량테스트
// 제목   : 탈주범 검거
// N x M의 map
// (R,C)의 위치에서 시작
// L : 탈출 후 소요된 시간
public class Solution {
	static int T,N,M,R,C,L,ans;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dist;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상우하좌
	static int[][] tunnel = {
			{}, // dummy
			{0,1,2,3}, // 1
			{0,2}, // 2
			{1,3}, // 3
			{0,1}, // 4
			{1,2}, // 5
			{2,3}, // 6
			{0,3}, // 7
	};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visit = new boolean[N][M];
			dist = new int[N][M];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = -1;
				}
			}

			// 초기화
			ans = 0;
			dist[R][C] = 1;

			bfs();
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(dist[i][j] + " ");
//				}
//				System.out.println();
//			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		} // TC END

		System.out.println(sb);
	}

	static void bfs() {
		Queue<Pos> Q = new ArrayDeque<Pos>();
		Q.offer(new Pos(R,C));
		visit[R][C] = true;

		while(!Q.isEmpty()) {
			Pos pos = Q.poll();
			if(dist[pos.y][pos.x] == L + 1) {
				break;
			}
			ans++;
			switch(map[pos.y][pos.x]) {
			case 1:
				for(int i = 0;i<tunnel[1].length;i++) {
					int ny = pos.y + dir[tunnel[1][i]][0];
					int nx = pos.x + dir[tunnel[1][i]][1];

					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[1][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 2:
				for(int i = 0;i<tunnel[2].length;i++) {
					int ny = pos.y + dir[tunnel[2][i]][0];
					int nx = pos.x + dir[tunnel[2][i]][1];

					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[2][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 3:
				for(int i = 0;i<tunnel[3].length;i++) {
					int ny = pos.y + dir[tunnel[3][i]][0];
					int nx = pos.x + dir[tunnel[3][i]][1];
					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[3][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 4:
				for(int i = 0;i<tunnel[4].length;i++) {

					int ny = pos.y + dir[tunnel[4][i]][0];
					int nx = pos.x + dir[tunnel[4][i]][1];
					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[4][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 5:
				for(int i = 0;i<tunnel[5].length;i++) {

					int ny = pos.y + dir[tunnel[5][i]][0];
					int nx = pos.x + dir[tunnel[5][i]][1];
					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[5][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 6:
				for(int i = 0;i<tunnel[6].length;i++) {

					int ny = pos.y + dir[tunnel[6][i]][0];
					int nx = pos.x + dir[tunnel[6][i]][1];
					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[6][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			case 7:
				for(int i = 0;i<tunnel[7].length;i++) {

					int ny = pos.y + dir[tunnel[7][i]][0];
					int nx = pos.x + dir[tunnel[7][i]][1];
					if(ny < 0 || nx < 0 || ny>=N || nx>=M)continue;
					if(visit[ny][nx])continue;
					if(map[ny][nx] == 0)continue;
					if(!isConnected(ny,nx,(tunnel[7][i] + 2)%4))continue;
					visit[ny][nx] = true;
					Q.offer(new Pos(ny,nx));
					dist[ny][nx] = dist[pos.y][pos.x] + 1;
				}
				break;
			}
		}
	}
	static boolean isConnected(int y,int x,int direction) {
		int curDir = map[y][x];
		for(int d : tunnel[curDir]) {
			if(d==direction) {
				return true;
			}
		}
		return false;
	}

	static class Pos{
		int y,x;
		public Pos(int y,int x) {
			this.y = y;
			this.x = x;
		}
	}
}

import java.io.*;
import java.util.*;

class Main{
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int cheese = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheese++;
			}
		}
		/**
		 * bfs -> 0,0에서 시작해서 처음으로 닿는 부분을 -1로 만든다.
		 * 	-1이 된 개수를 반환한다.
		 * 	치즈 개수 - 없어진 개수 = 현재 개수 -> 0이 되면 return. 0되기 직전 치즈 개수를 출력함.
		 * bfs가 끝나고 난 후, -1이 된 부분을 0으로 만든다.
		 * */
		int hour = 0;
		while(true) {
			hour++;
			int melt = bfs();
			if(cheese - melt <= 0) break; // 이번 시간에 모두 녹았으면 종료
			// 녹은 부분(2)을 0으로 표시
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 2) map[i][j] = 0;
				}
			}
			// 남은 부분 계산
			cheese -= melt;
		}
		System.out.println(hour);
		System.out.println(cheese);
	}
	static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int[][] visit = new int[N][M];
		
		q.add(new int[] {0,0});
		visit[0][0] = 1;
		int melt = 0;
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			
			for(int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if( nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if(visit[ny][nx] == 1) continue;
				if(map[ny][nx] == 1) { // 치즈 겉 부분 -> melt표시, 더 진행하지 않음
					visit[ny][nx] = 1;
					map[ny][nx] = 2;
					melt++;
					continue;
				}
				visit[ny][nx] = 1;
				q.add(new int[] {nx, ny});
			}
		}
		return melt;
	}
}
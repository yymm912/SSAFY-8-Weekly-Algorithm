import java.io.*;
import java.util.*;

class Main{
	static int N, M;
	static int[][] map;
	static List<int[]> virusPos;
	/**
	 * 3개 모든 경우 다 고려하기
	 * 
	 * 영역 구하는 bfs => map을 clone해서 사용
	 * */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virusPos = new ArrayList<>();
		
		int safezone = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 1) safezone++;
				if(map[i][j] == 2) {
					virusPos.add(new int[] {j, i}); // x, y
				}
			}
		}
		
		// 벽 세울 곳 3곳 정함
		int[] p1, p2, p3;
		p1 = new int[2]; // y, x
		p1[1]=-1; // 0,0을 포함하기 위해
		p2 = new int[2];
		p3 = new int[2];
		
		int maxSafeZone = 0;
		while(getNext(p1[0],p1[1],p1)) {
			p2[0] = p1[0]; p2[1] = p1[1];
			
			while(getNext(p2[0],p2[1],p2)) {
				p3[0] = p2[0]; p3[1] = p2[1];
				
				while(getNext(p3[0],p3[1],p3)) {
					// 벽 막음
					map[p1[0]][p1[1]] = 1;
					map[p2[0]][p2[1]] = 1;
					map[p3[0]][p3[1]] = 1;
					
					// 바이러스 확산된 크기
					int virusSum = 0;
					int[][] visit = new int[N][M];
					
					for(int[] pos : virusPos) {
						if(visit[pos[1]][pos[0]] == 1) continue;
						int tmp = bfs(pos[0], pos[1], visit);
						virusSum += tmp;
					}
					
					// 안전지역 크기 (safezone - 3 - virus)
					int curSafeZone = safezone - 3 - virusSum;
					if(maxSafeZone < curSafeZone) {
						maxSafeZone = curSafeZone;
					}
					
					// 벽 되돌림
					map[p1[0]][p1[1]] = 0;
					map[p2[0]][p2[1]] = 0;
					map[p3[0]][p3[1]] = 0;
					
				}
			}
		}
		System.out.println(maxSafeZone);
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int bfs(int x, int y, int[][] visit) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.add(new int[] {x, y});
		visit[y][x] = 1;
		int virus = 0;
		while(!q.isEmpty()) {
			int[] cur= q.remove();
			virus++;
			for(int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if( nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if(visit[ny][nx] == 1 || map[ny][nx] == 1) continue; // 벽 or 이미 방문
				q.add(new int[] {nx, ny});
				visit[ny][nx] = 1;
			}
		}
		return virus;
	}
	static boolean getNext(int py, int px, int[] pos) { // px, py 이후 map==0인 위치를 pos에 담음	
		for(int x=px+1; x<M; x++) { // py 행
			if(map[py][x] == 0) {
				pos[0] = py; pos[1] = x; return true;
			}
		}
		for(int y=py+1; y<N; y++) {	// py 이후 행
			for(int x=0; x<M; x++) {
				if(map[y][x] == 0) {
					pos[0] = y; pos[1] = x; return true;
				}
			}
		}
		return false;
	}
}
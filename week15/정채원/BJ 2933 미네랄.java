import java.io.*;
import java.util.*;

class Main{
	static int R, C, N;
	/*
	 * 1. 막대기로 부순 곳 기준, 4방향을 포함하는 클러스터 찾기 (bfs)
	 * 2. 클러스터 뭉탱이 찾음
	 * 		2-1. visit에 방문 & 클러스터 표시
	 * 		2-2. queue에 클러스터를 저장 -> 저장된대로 떨어지는 거 구할 때 참고
	 * 3. 클러스터 최소 떨어지는 거리 구함
	 * 4. 이동
	 */
	static char[][] cave;
	
	static int[] throwCnt;
	static Queue<int[]> cluster;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cave = new char[R][C];
		for(int i=0; i<R; i++) cave[i] = br.readLine().toCharArray();
		
		N = Integer.parseInt(br.readLine());
		throwCnt = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int th=0; th<N; th++) {
			int y = R-Integer.parseInt(st.nextToken());
			int x = throwStick(y, th & 1);
			
			if(x == -1) continue;
			cave[y][x] = '.';
			// 부서진 곳 주변
			int[][] visit = new int[R][C];
			int clusterId = 1;
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx < 0 || nx >= C || ny < 0 || ny >= R || cave[ny][nx] == '.') continue;
				if(visit[ny][nx] > 0) continue;
				
				bfs(visit, nx, ny, clusterId); // 클러스터 구함 -> visit에 체크
				int minDropDist = findMinDropDist(visit, clusterId); // 클러스터가 떨어지는 최소 거리 구함
				clusterId++;
				
				if(minDropDist == 0) continue;
				drop(minDropDist); // cave에 클러스터 떨어지는 거 표시

				break;
			}
		}
		print();
	}
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}
	static void drop(int dropDist) {
		for(int[] p : cluster) {
			cave[p[1]][p[0]] = '.';			
		}
		for(int[] p : cluster) {
			cave[p[1]+dropDist][p[0]] = 'x';
		}
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int throwStick(int y, int delta) {
		if(delta == 0) { // L -> R
			for(int i=0; i<C; i++) {
				if(cave[y][i] == 'x') return i;
			}
			return -1;
		} else { // R -> L
			for(int i=C-1; i>=0; i--) {
				if(cave[y][i] == 'x') return i;
			}
			return -1;
		}
	}
	static void bfs(int[][] visit, int x, int y, int cId) { // 클러스터 떨어지는 최소 거리를 반환
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {x, y});
		visit[y][x] = cId;
		
		cluster = new ArrayDeque<>();
		cluster.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			for(int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if( nx < 0 || nx >= C || ny < 0 || ny >= R || cave[ny][nx] == '.') continue;
				if(visit[ny][nx] > 0) continue;
				
				visit[ny][nx] = cId;
				q.add(new int[] {nx, ny});
				cluster.add(new int[] {nx, ny});
			}
		}
		
		return;
	}
	static int findMinDropDist(int[][] visit, int cId) {
		int min = 9999;
		for(int[] p : cluster) {
			int x = p[0], y = p[1];
			min = Math.min(min, findDropDist(visit, x, y, cId));
		}
		return min;
	}
	
	static int findDropDist(int[][] visit, int x, int y, int cId) {
		int dist=0;
		for(int i=1; y+i<R; i++) {
			if(cave[y+i][x] == '.') dist++; 
			else if(cave[y+i][x] == 'x' && visit[y+i][x] == cId) return 9999;
			else break;
		}
		return dist;
	}
}
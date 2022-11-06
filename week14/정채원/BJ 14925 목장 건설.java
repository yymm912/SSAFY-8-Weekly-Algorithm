import java.io.*;
import java.util.*;

class Main{
	static int N, M;
	static int[][] map;
	static int[] dx = {1, 1, 0}; // 오, 오아, 아
	static int[] dy = {0, 1, 1}; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		List<int[]> noEmpty = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] dp = new int[N][M];
		/*
		 * dp에는 현재 (x, y)를 왼쪽 위 시작점으로 하는 정사각형 최대 크기를 저장함.
		 * 시작 : 오른쪽 아래 끝 지점에서 시작.
		 * 오늘쪽, 오른쪽 아래, 아래 3방향 검사 -> 바운더리 초과 또는 장애물이 있으면 : 1, 아니면 주변 dp중 가장 작은 값 + 1
		 * 내 주위가 다 1 이상 이면 -> 난 세곳 중 가장 작은 거 + 1임. (다 1이면 난 2가 되느거)
		 */
		int max = 0;
		for(int i=N-1; i>=0; i--) {
			for(int j=M-1; j>=0; j--) {
				if(map[i][j] != 0) continue;
				int min = 9999;
				for(int d=0; d<3; d++) {
					int ny = i + dy[d], nx = j + dx[d];
					if(nx < 0 || nx >= M || ny < 0 || ny >= N || map[ny][nx] != 0) min = 0;
					else {
						min = Math.min(dp[ny][nx], min);
					}
				}
				dp[i][j] = min + 1;
				max = Math.max(dp[i][j], max);
			}
		}
		System.out.println(max);
		
	}
	
}
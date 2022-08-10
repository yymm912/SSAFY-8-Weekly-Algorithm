import java.util.ArrayDeque;
import java.util.Queue;

public class Question1 {

/*
 * 미로찾기
 * 막힌 벽은 0, 갈 수 있는 길을 1, 출발점을 2, 도착점을 3이라고 했을 때
 * 출발점에서 도착점까지 가는데 걸리는 가장 짧은 거리는 얼마인가?
 * 
 */
	static int [][] map =  {
			{0,  0,  0,  0,  0,  0,  0}, // row 0 dummy
	        {0, 2, 1, 1, 1, 1, 1}, // row 1
	        {0, 1, 0, 0, 1, 0, 0}, // row 2
	        {0, 1, 0, 1, 1, 0, 0},
	        {0, 1, 0, 1, 0, 0, 0},
	        {0, 0, 0, 1, 1, 1, 0},
	        {0, 1, 1, 1, 0, 1, 3},
	        // 1    2    3    4    5    6 // 0 dummy
	};
	// delta : 상 - 하 - 좌 - 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	// visit 재방문 체크
	static boolean[][] visit;
	static int COUNT, min;
	public static void main(String[] args) {
		
		// dfs 초기화	
		visit = new boolean[7][7];
		COUNT = 0;
		min = 9999;
		dfs(1, 1);
		
		System.out.println("최소거리 : " + min);
		
	}
	
	static void dfs(int y, int x) {
		// 초기화 x 메소드 밖에서 초기화
		visit[y][x] = true; // visit
		COUNT++;
		
		// 4방 방문 시도
		for ( int d = 0; d<4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			
			// range, visit
			if (nx>=1 && ny>= 1 && nx<7 && ny<7) {
				if (map[ny][nx] == 1 && !visit[ny][nx]) {
					dfs(ny, nx);
					COUNT--; // 빠져나오면서 COUNT값과 방문한 기록을 제거
					visit[ny][nx] = false;
				}
				if ( map[ny][nx] == 3 ) {
					min = Math.min(min, COUNT);
					return;
				}
			}
		}
	}
}

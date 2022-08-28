import java.io.*;
import java.util.*;

public class Solution{
	/**
	 * 1. 삼각형, 사각형, 벽을 만났을 때 이동방향 바뀜 & 점수++ 
	 * 		각 삼각, 사각형 이동방향 바뀌는 함수
	 * 		점수 얻음
	 * 2. 웜홀 위치 이동 -> int[][] worm에 쌍으로 저장함
	 * 3. 블랙홀 만났을 때, 출발위치 끝남
	 * */
	static int T, N;
	static int[][] map;
	static int[][] block; // [block 번호][현재방향] = 다음방향 
	static int[][][] worm; // [웜홀번호][2개 쌍][isFill,x,y]
	static int score;
	static int sx, se;
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		block = new int[6][];
		// 방향 : 서, 북, 동, 남
		block[1] = new int[] {1, 3, 0, 2}; //서>북, 북>남, 동>서, 남>동
		block[2] = new int[] {3, 2, 0, 1}; //서>남 북>동 동>서 남>북
		block[3] = new int[] {2, 0, 3, 1}; //서>동 북>서 동>남 남>북
		block[4] = new int[] {2, 3, 1, 0}; //서>동 북>남 동>북 남>서
		block[5] = new int[] {2, 3, 0, 1}; //반대로
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			worm = new int[11][2][3];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 5) { // 웜홀 정보 저장
						int wn = map[i][j];
						if(worm[wn][0][0] == 0) { // 첫번째 칸이 비어있으면
							worm[wn][0][0] = 1;
							worm[wn][0][1] = j; // x
							worm[wn][0][2] = i; // y
						} else {
							worm[wn][1][0] = 1;
							worm[wn][1][1] = j;
							worm[wn][1][2] = i;
						}
					}
				}
			}
			
			int maxScore = 0;
			for(int sy=0; sy<N; sy++) {
				for(int sx=0; sx<N; sx++) {
					// 출발지점이 막히면 X
					if(map[sy][sx] != 0) continue;
					// 시뮬레이션 & 점수
					// 4방향에 대해 모두 플레이해봄
					for(int d=0; d<4; d++) {						
						int score = play(sx, sy, d);
						maxScore = Math.max(score, maxScore);					
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, maxScore);			
		}
	}
	static int[] dx = {-1, 0, 1, 0}; // 왼 위 오 아래 
	static int[] dy = {0, -1, 0, 1};
	static int play(int sx, int sy, int d) {
		// 시작지점 or 블랙홀을 만날때까지 계속
		int x = sx + dx[d], y = sy + dy[d];
		int score = 0;
		while(true) {
			// 벽인지 확인
			if(x < 0 || x >= N || y < 0 || y >= N) {
				d = block[5][d]; // 반대로
				x += dx[d];	y += dy[d];
				score++;
				continue;
			}
			if(x == sx && y == sy) break; // 시작지점
			if(map[y][x] == -1) break; // 블랙홀
			
			if(map[y][x] >= 1 && map[y][x] <= 5) { // 블록
				d = block[map[y][x]][d];
				score++;
			}
			else if(map[y][x] >= 6 && map[y][x] <= 10) { // 웜홀
				int wormN = map[y][x];
				int pairWorm = getPairWormIdx(wormN, x, y);
				x = worm[wormN][pairWorm][1];
				y = worm[wormN][pairWorm][2];
			}
			// 이동
			x += dx[d]; y += dy[d];			
		}	
		return score;
	}
	static int getPairWormIdx(int wormN, int x, int y) {
		if(worm[wormN][0][1] == x && worm[wormN][0][2] == y) return 1;
		else return 0;
	}
}
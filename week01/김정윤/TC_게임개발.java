package week1.김정윤;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TC_게임개발 {
	private static int N, M, visitCnt, cnt;
	private static int[][] board;
	private static boolean[][] isVisit;
	private static Queue<int[]> q = new LinkedList<>();
	
    // 서남동북으로 기본값을 세팅, 북쪽(0)을 기준으로 왼쪽으로 회전
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("TC_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		isVisit = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		// 초기 위치와 방향 설정
		q.add(new int[] {x, y, d});
		
		// 게임보드 세팅
		setGameBoard(br);
		
		// 게임 시작
		System.out.println(gameStart());
	}

	private static void setGameBoard(BufferedReader br) throws IOException {
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static int gameStart() {
		while(!q.isEmpty()) {
			// 현재 위치, 방향 초기화
			int[] current = q.poll();
			int cX = current[0];
			int cY = current[1];
			int cD = current[2];
			
            // 플레이어 회전
			cnt = turnLeft(cX, cY, cD, 1);
			
			// 4방 모두 이동 불가 시
			if (!canGo(cX, cY, cD, cnt)) break;			
		}
		return visitCnt;
	}

	private static boolean canGo(int cX, int cY, int cD, int cnt) {
		if(cnt == 4) {
			int direction = (cD+2)%4; // 현재 방향의 반대쪽 == 뒤쪽 방향
			int nX = cX + dx[direction];
			int nY = cY + dy[direction];
			if(board[nX][nY] != 1) { // 육지일 경우 뒤로 한칸 이동
				q.add(new int[] {nX, nY, direction});
			}else { // 4방이 바다일 경우 종료
				return false;
			}
		}
		return true;
	}

	private static int turnLeft(int cX, int cY, int cD, int cnt) {
		for (int i = 0; i < 4; i++) {
			// 플레이어가 바라보는 위치에서 왼쪽 방향을 바라보도록 위치 지정
			int direction = (cD + i) % 4;
			int nX = cX + dx[direction];
			int nY = cY + dy[direction];
			
			if(nX >= 0 && nX < N && nY >= 0 &&  nY < N) { // 보드 사이즈를 넘지 않으면서
				if(!isVisit[nX][nY] && board[nX][nY] != 1) { // 방문한적 없는 육지일 경우
					isVisit[nX][nY] = true; // 방문한 상태로 설정
					visitCnt++; // 이동횟수 추가
					q.add(new int[] {nX, nY, direction}); // 현재 위치와 방향 설정
				} else {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
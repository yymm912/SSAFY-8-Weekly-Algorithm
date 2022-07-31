package forStudy.week01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제>
 * N*M 크기의 직사각형의 장소에
 * 캐릭터의 위치 A, B, d가 주어진다.
 * 캐릭터는 왼쪽 방향의 상황을 토대로 움직인다.
 * 왼쪽 회전 후 가보지 않은 칸이 있다면 한칸 전진, 아니면 그대로 유지
 * 네 방향 모두 가본 칸이거나 바다라면, 바라보는 방향을 유지한 채로 한 칸 뒤로 이동 후 다시 왼쪽 탐색
 * 만약 뒤쪽 방향이 바다라 뒤로 갈 수 없다면 움직임을 멈춘다.
 * 
 * 입력 : 첫 줄에 N M 둘째줄에 A B d 다음 줄부터 장소정보
 * 출력 : 이동한 칸 수를 출력
 **/

public class TC_게임개발 {
	static int N, M;
	static int cA, cB, cd;
	static int[][] map;		// 장소 정보를 담을 배열
	static int result, turnCnt;	// 이동 칸 수와 사방탐색 여부 체크를 위한 카운트변수
	static int[] dx = {-1, 0, 1, 0}; // 북-동-남-서
	static int[] dy = {0, 1, 0, -1 };
	
	static void turnLeft() {
		cd--;
		if(cd == -1) cd = 3;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		cA = sc.nextInt(); // 캐릭터 x좌표
		cB = sc.nextInt(); // 캐릭터 y좌표
		cd = sc.nextInt(); // 캐릭터 바라보는 방향
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		map[cA][cB] = 1;  // 현재 좌표 방문 처리
		result = 1;
		turnCnt = 0;
		
		while(true) {
			turnLeft();
			int tx = cA + dx[cd];
			int ty = cB + dy[cd];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
			
			if(map[tx][ty] == 0) { // 육지이면서 가보지 않은 곳
				map[tx][ty] = 1;
				cA = tx;
				cB = ty;
				result++;
				turnCnt = 0;
				continue;
			} else {  // 바다거나 가본 곳
				turnCnt++;	// 한 방향 skip한 것 체크
			}
			if(turnCnt == 4) { // 사방이 가본 곳이거나 바다라면
				tx = cA - dx[cd];
				ty = cB - dy[cd];
				if(map[tx][ty] == 1) break;
				cA = tx;
				cB = ty;
				turnCnt = 0;
			}
		}
		System.out.println(result);
	}

}

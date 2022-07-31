package study;

import java.util.Scanner;

public class game {

	static int d;
	static int[] dy = {-1, 0, 1, 0}; // 북 동 남 서 방향으로 방향 델타 배열 생성
	static int[] dx = {0, 1, 0, -1};
	static int[][] cpymap; // map에서 방문했는지 판단하는 2차원 배열 생성
	
	public static void turn() { // 왼쪽으로 회전하는 메서드
		d --;
		if(d<0) d += 4; // 0 미만이라면 다시 3부터 시작해 보는 방향을 나타냄
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //정사각 배열의 크기
		int M = sc.nextInt(); //
		int Y = sc.nextInt(); // 서있는 Y축 위치
		int X = sc.nextInt(); // 서있는 X축 위치
		d = sc.nextInt(); // 현재 바라보는 위치
		
		// 게임의 맵과 방문 판단맵 선언
		int[][] map = new int[N][M];
		int[][] cpymap = new int[N][M];

		
		//맵 생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M ; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 회전한 횟수와 밟은 발판 개수 카운트 선언 및 초기화
		int turn = 0;
		int count = 1;
		
		while(true)
		{
			turn(); // 왼쪽으로 회전
			
			//이동할 위치 nx, ny => 바라보는 방향에 따라 값설정
			int nx = X +dx[d];
			int ny = Y +dy[d];
			
			
			if(map[ny][nx] == 0 && cpymap[ny][nx] == 0) // 육지이며, 방문하지않은 위치일 때
			{
				cpymap[ny][nx] = 1; //방문함을 체크
				Y = ny; //새로운 위치를 현재 위치 변수에 담음.
				X = nx;
				
				count++; // 밟은 땅 개수 추가
				turn = 0;// 돈 회수 초기화
				continue;
			}
			// 이동할 위치가 바다이거나 이미 갔던 곳이라면
			else if(map[ny][nx] == 1 || cpymap[ny][nx] == 1) {
				ny = Y; //이동할 위치는 현위치로
				nx = X;
				turn = 0;
				continue;
			}
			else { //방문할 곳이 없다면 회전 수 추가 후 넘김
				turn++;
			}
			
			if(turn == 4) //한 바퀴를 다 돌았을 때
			{
				ny = Y -dy[d];
				nx = X -dx[d];
				
				if(cpymap[ny][nx] == 1) { // 바라보는 지형이 이미 방문한 곳이라면 이전 칸으로 다시 이동
					X = nx;
					Y = ny;
				}
					else break;
				turn = 0;
				}

			}
			
		
		System.out.println(count);

	}

}

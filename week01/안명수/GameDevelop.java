package algorithm.TC;

import java.util.Scanner;

public class GameDevelop {

//	// 입력
//	4 4
//	1 1 0
//	1 1 1 1
//	1 0 0 1
//	1 1 0 1
//	1 1 1 1
//
//	// 출력
//	3
	
	public static void main(String[] args) {
		
		// N,M 의 최대 값이 50밖에 안되므로 Scanner 사용
		Scanner sc = new Scanner(System.in);

		// 인덱스 순서대로  { 북 동 남 서 }
		// 반시계 회전을 해야하므로 인덱스를 순서대로 줌
 		int[] dy = {-1, 0, 1,  0};
		int[] dx = { 0, 1, 0, -1};
		
		int n, m;
		n = sc.nextInt();
		m = sc.nextInt();
		

		// 방문판단 배열
		boolean[][] visited = new boolean[n][m];
		int[][] area = new int[n][m];
		
		
		
		// 캐릭터 시작 위치
		int start_y = sc.nextInt();
		int start_x = sc.nextInt();
		
		// 캐릭터가 현재 바라보는 방향
		int nesw = sc.nextInt();
		
		
		// 직사각형 상태 저장
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < m; j++) 
				area[i][j] = sc.nextInt();
			
		
		// 캐릭터의 움직임 판단, 캐릭터 이동횟수, 이동할 좌표
		boolean move = true;
		int count = 0;
		int y = start_y;
		int x = start_x;
		
		// 캐릭터가 이동을 안할때까지
		while(move) {
			// 탈출조건 걸기
			move = false;
			
			// 현재 위치가 아직 방문하지 않은 육지라면 방문 + count증가
			if(!visited[y][x]) {
				visited[y][x] = true;
				count++;
			}
			
			
			for(int i = 0; i < 4; i++) {
				
				// 왼쪽으로 돌기
				nesw = (nesw + 3) % 4;
				int ny = y + dy[nesw];
				int nx = x + dx[nesw];
				
				
				// 범위 벗어나거나
				// 방문했거나
				// 바다라면 패스
				if(ny < 0 || nx < 0 || ny >= n || nx >= m 
						|| visited[ny][nx] || area[ny][nx] == 1) continue;
				
				
				move = true;
				y = ny;
				x = nx;
				break;
			}
			
			
			// 4군데 모두 이동할 곳이 없으면
			if(!move) {
				
				// 북 <=> 남 , 동  <=> 서
				int reverse = ( nesw + 2 ) % 4;
				
				int back_y = y + dy[reverse];
				int back_x = x + dx[reverse];
				
				// 뒤쪽이 바다인지 체크
				if(0 <= back_y && back_y < n && 0 <= back_x && back_x < m &&
						area[back_y][back_x] != 1) {
					
					y = back_y;
					x = back_x;
					
					move = true;
				}
			}
		}
		
		
		System.out.println(count);
		
		// scanner 반납
		sc.close();
	}

}

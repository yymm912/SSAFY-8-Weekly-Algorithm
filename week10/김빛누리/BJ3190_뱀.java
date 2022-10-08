package week10;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ3190_뱀 {
	/* 
	 * 상 우 하 좌  순서
	 * 
	 * 방향전환시 
	 * 좌 -> index--
	 * 우 -> index++
	 */
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static int N;
	static int K;
	static char[][] map;		// a: apple, s: snake
	
	static int L; 
	static int[] turnMin;		// 방향전환 초 저장
	static char[] turnDir;		// 방향전환 저장 ( L : 왼, D : 오 )
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new char[N+1][N+1];	// 0행, 0열  -> dummy
		
		for(int i = 0; i < K; i++) {
			String[] str = br.readLine().split(" ");
			map[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 'a';	// a -> apple
		}
		
		L = Integer.parseInt(br.readLine());
		turnMin = new int[L];
		turnDir = new char[L];
		
		for(int i = 0; i < L; i++) {
			String[] tmp = br.readLine().split(" ");
			turnMin[i] = Integer.parseInt(tmp[0]);
			turnDir[i] = tmp[1].charAt(0);	// L : 왼, D : 오
		}
	
		logic();

	}
	
	public static void logic() {
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(new Point(1,1)); // 뱀 초기 위치(1,1)
		map[1][1] = 's';	// map에 뱀 위치 써주기
		
		//int snakeSize = 1;
		int time = 0;
		int currDirIndex = 1;		// 현재 방향, 처음에 뱀은 오른쪽 향한다
		int nextTurnIndex = 0;	// 다음 방향전환 index ( turnMin, turnDir)
		
		while(true) {
			
			// 방향 전환 
			if(nextTurnIndex < L && time == turnMin[nextTurnIndex]) {
				if(turnDir[nextTurnIndex] == 'L') currDirIndex--;
				else currDirIndex++;
				
				///현재방향 범위 벗어나는 경우 
				if(currDirIndex > 3) currDirIndex = 0;
				else if(currDirIndex < 0) currDirIndex = 3;
				
				nextTurnIndex++;
			}
			
			// 머리 이동할 위치 
			Point head = snake.getFirst();
			int ny = head.y + dy[currDirIndex];
			int nx = head.x + dx[currDirIndex];
			
			time++;
			
			// 게임 오버 (벽 또는 자기자신의 몸과 부딪힘)
			// 뱀이 벽에 부딫힘(범위 체크), 뱀이 자기자신의 몸에 부딫힘 
			if(ny <= 0 || nx <= 0 || ny > N || nx > N 
					||(map[ny][nx] == 's')) break;
			
			// 이동한 머리 deque에 넣기 
			head = new Point(nx, ny);
			snake.addFirst(head);
			
			// 꼬리 이동 (맨 뒤 deque 제거)
			// 뱀이 사과 안먹었을 경우에만 수행
			if(map[ny][nx] != 'a') {
				Point tail = snake.removeLast();
				map[tail.y][tail.x] = 0;	// map에서 snake 꼬리 없애기
			}
			
			// 맵에 머리위치 기록
			map[ny][nx] = 's';
			
		}		
		System.out.println(time);
	}

}
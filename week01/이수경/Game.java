package d;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game {

	static int dy[] = {-1, 0, 1, 0}; //북 동 남 서
	static int dx[] = { 0, 1, 0, -1};
	static int N, M;
	static int map[][];
	static boolean isVisited[][];
	//map이 1인 경우, 바다
	//isVisited가 true인 경우, 이미 가본 칸
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("Game.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isVisited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int start_y = Integer.parseInt(st.nextToken());
		int start_x = Integer.parseInt(st.nextToken());
		int start_direction = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력 완료
		
		int px = start_x; int py = start_y; int d = start_direction;
		int x = 0; int y = 0;
		int cnt = 1;
		int exit = 0;
		isVisited[py][px] = true;
		
		while(true) {
			if(exit == 4) { // 3. 네 방향 모두 이미 가본 칸 또는 바다인 경우
				exit = 0;
				// 이미 가본 칸일 경우 뒤로 한 칸 이동 후, 1단계 반복
				py = py - dy[d];
				px = px - dx[d]; 
				if(map[py][px] == 1) break; //바다일 경우 멈춤
			}
			
			// 1. 현재 위치에서 현재 방향 기준 반시계 방향 갈 곳 정하기
			d--;
			if(d < 0) d = 3; // 북 -> 서 -> 남 -> 동 -> 북 -> 으로 이동
			y = py + dy[d];
			x = px + dx[d];
			if(y < 0 || y >= N || x < 0 || x >= M || map[y][x] == 1 || isVisited[y][x]) {
				exit++;
				continue;
			}
			isVisited[y][x] = true; //방문한 곳 체크
			cnt++; exit = 0;
			
			// 2. 좌표 이동
			py = py + dy[d];
			px = px + dx[d];
		}
		
		System.out.println(cnt);
	}

}

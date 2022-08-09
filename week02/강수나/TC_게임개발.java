package algo_study.study8월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 델타
 * N X M 직사각형, 1:바다, 0:육지, 2:지나온자리
 * 왼쪽방향부터
 * 시간 : 0.011 ms
 * */
public class TC_게임개발2 {

	static int N, M, dir, X, Y, cnt; //가로, 세로, 방향, X좌표, Y좌표, 방향 몇번돌았는지 카운트
	static int ans; //지나온 자리(2)의 개수 카운트
	static int[][] map;
	static int[] dir_y = {1, 0, -1, 0}; //북 동 남 서
	static int[] dir_x = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		//입력부
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) { //지도 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long start = System.nanoTime(); //실행 시작
		//탐색
		map[Y][X] = 2; //시작점은 방문하고 시작
		ans = 1;
		while (true) {
			dir--; //방향 바뀜
			if (dir < 0) dir = 3; //방향 사이클
			
			//육지면 이동
			if (map[Y + dir_y[dir]][X + dir_x[dir]] == 0) { 
				Y += dir_y[dir]; 		//다음 좌표로 이동
				X += dir_x[dir];
				map[Y][X] = 2; 			//지나온 좌표는 2로 저장
				ans++;					//지나온 좌표 개수 카운트 (2가 몇개인지)
				cnt = 0;				//cnt:해당 좌표에서 방향 회전한 횟수, 다음 지점갈 때 초기화
			}
			else { //바다면 방향만 바뀜
				cnt++; //cnt == 3면 네 방향 다 돌았는데도 갈 수 있는 곳이 없다
				//갈 수 있는 곳이 없으니 뒤로 가겠다
				if (cnt == 3) {
					//뒤가 바다일 때 - 멈춤
					if (map[Y - dir_y[dir]][X - dir_x[dir]] == 1) break;
					//뒤가 바다가 아닐 때 - 이전 위치로 돌아감, 방향 유지
					else {
						Y -= dir_y[dir];
						X -= dir_x[dir];
					}
				}
			}
		}
		long end = System.nanoTime();
		System.out.println("시간 : " + (end - start) * 0.000_001 + " ms");
		System.out.println(ans);
	}

}


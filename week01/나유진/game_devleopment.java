package al.jul;

import java.util.Scanner;

public class game_devleopment {
	static int N, M, X, Y, D; // 배열크기, x좌표, y좌표, 방향
	static int[][] map; // 지도. 바다=1 , 육지 =0 , 다녀간 곳 =-1;
	static int[] dx = { -1, 0, 1, 0 }; //북, 서, 남, 동
	static int[] dy = { 0, 1, 0, -1 };
	static int walk = 1;
	static int turn = 0;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();
		D = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		map[X][Y] = -1;

		while (true) {
			D = (D + 1) % 4; // 왼쪽 회전하는 코드 
			int nx = X + dx[D];
			int ny = Y + dy[D];			
			if (map[nx][ny] == 0) {
				X = nx;
				Y = ny;
				walk++;				
				map[X][Y] = -1;
				turn=0;
				continue;
			} else {
				turn++;
			}

			if (turn == 4) {
				nx = X - dx[D];
				ny = Y - dy[D];
				if (map[nx][ny] == -1) {
					X = nx;
					Y = ny;
					turn = 0;
				} else
					break;
			}

		}
		System.out.println(walk);

		sc.close();
	}

}

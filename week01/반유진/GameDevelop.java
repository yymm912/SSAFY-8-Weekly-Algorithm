import java.util.Scanner;

public class GameDevelop {

	// 상-하-좌-우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int dir;
	static int[][] d = new int[50][50];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int x = sc.nextInt();
		int y = sc.nextInt();
		dir = sc.nextInt();
		d[x][y] = 1;

		int[][] map = new int[n][m];

		// 맵 입력 받기 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int turn_time = 0;
		int cnt = 1;

		while (true) {
			turn_left(); // 왼쪽으로 회전 

			int ny = y + dy[dir];
			int nx = x + dx[dir];

			if (d[nx][ny] == 0 && map[nx][ny] == 0) {
				// 회전하고 정면에 가보지 않은 칸이 존재하는 경우 이동하기 
				d[nx][ny] = 1;
				x = nx;
				y = ny;
				cnt += 1;
				turn_time = 0;

				continue;
			} else {	
				// 회전하고 정면에 가보지 않은 칸이 없거나 바다인 경우  
				turn_time += 1;
			}
			if (turn_time == 4) {
				// 네 방향 모두 갈 수 없는 경우 
				nx = x - dx[dir];
				ny = y - dy[dir];
				if (map[nx][ny] == 0) {
					// 뒤로 갈 수 있으면 이동
					x = nx;
					y = ny;
				} else {
					// 뒤가 바다로 막혔을 때 
					break;
				}
				turn_time = 0;
			}

		}
		System.out.println(cnt);

	}

	public static void turn_left() {	// 왼쪽으로 회전 
		dir -= 1;
		if (dir == -1) {
			dir = 3;
		}
	}

}

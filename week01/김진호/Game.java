package ps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game {
	static int arr[][]; // 입력 배열
	static int visited[][]; // 방문 체크 배열
	static int n, m;
	static int direction; // 방향 체크 
	static int cnt;
	static int dy[] = { -1, 0, 1, 0 }; // delta 
	static int dx[] = { 0, 1, 0, -1 }; // delta
	static int ny, nx;
	static int answer = 1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("game.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];

		st = new StringTokenizer(br.readLine());

		ny = Integer.parseInt(st.nextToken());
		nx = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());

		visited[ny][nx] = 1;// 처음 시작 지점 체크

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//////////////////////////////////////////////// 입력 끝 ////////////////////////////////////////////////
		while (true) {
			if (direction == 3) // 북 서 남 동를 반복
				direction = 0;
			else
				direction++;
			ny = ny + dy[direction];
			nx = nx + dx[direction];
			if (arr[ny][nx] == 0 && visited[ny][nx] == 0) { // 가보지 않은 칸이 존재, 
				visited[ny][nx] = 1; // 방문한 곳 표시
				answer++;
				cnt = 0;

			} else {
				if (cnt == 4)
					break;
				cnt++;
				ny -= dy[direction]; // 이동하지 못하는 곳이면 이동을 취소
				nx -= dx[direction]; // 이동하지 못하는 곳이면 이동을 취소
			}

		}
		System.out.println(answer);

	}

}

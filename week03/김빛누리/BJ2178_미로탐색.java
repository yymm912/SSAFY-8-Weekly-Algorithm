package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
 * 메모리: 14472 KB
 * 시간: 136 ms
 * */
public class BJ2178_미로탐색 {
	static int N, M, result;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static Queue<Node> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new char[N][];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		q.add(new Node(0, 0, 1));
		visited[0][0] = true;
		bfs();

		System.out.println(result);

	}

	static void bfs() {

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;

			// N,M 도착 (배열 에서 인덱스는 N-1, M-1 )
			if (y == N - 1 && x == M - 1) {
				result = node.depth;
			}

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx])
					continue;

				if (map[ny][nx] == '0')	// 이동할 수 없는 칸인 경우
					continue;

				visited[ny][nx] = true;
				q.add(new Node(nx, ny, node.depth + 1));	// 탑색한 깊이+1

			}
		}
	}

}

class Node {
	int x;
	int y;
	int depth;

	public Node(int x, int y, int depth) {
		super();
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
}
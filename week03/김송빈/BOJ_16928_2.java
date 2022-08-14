package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928_2 {
	static int n, m;
	static int[] map;
	static int[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[101];
		visited = new int[101];

		for (int i = 1; i < 101; i++) {
			map[i] = i;//모든 수의 위치? 좌표?
		}

		//입력 받기
		for (int i = 0; i < n + m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y] = x;//얘는 자기자신의 값을 가지지 않고 사다리나 뱀으로 가는 수
		}

		//bfs
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);//시작값
		visited[1] = 0;

		while (!q.isEmpty()) {
			int k = q.poll();
			for (int i = 1; i <= 6; i++) {//주사위 굴림
				int nk = k + i;
				if (nk <= 100 && visited[map[nk]] == 0) {
					visited[map[nk]] = visited[k] + 1;//visited로 수 세기 
					q.offer(map[nk]);
				}
			}
		}
		System.out.println(visited[100]);
	}
}

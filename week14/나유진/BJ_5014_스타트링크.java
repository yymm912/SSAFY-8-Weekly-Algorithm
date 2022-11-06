package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014_스타트링크 {
	static int F, S, G, U, D;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// F : 건물의 총 층 수, S : 현재 있는 층, G : 내가 가야할 층, U: 위로 올라갈 수 있는 층 수, D: 아래로 갈 수 있는 층 수

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// # 전략
		// # 함수 : dfs()

		// dfs 하며 floor 가 F(건물의 전체 층) 을 넘기면 queue에 담지 않음
		// floor + U, floor - D 하며 G를 갈 수 있는 지 탐색.
		// visit 배열을 사용해서 이미 간 층은 다시 가지 않도록 prunning

		if (dfs()) {
			System.out.println("use the stairs");
		} else {
			System.out.println(cnt);
		}

	}

	static boolean dfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[1000001];
		queue.add(S);
		cnt = 0;
		boolean flag = true;

		while (!queue.isEmpty() && flag) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int floor = queue.poll();

				if (floor == G)
					flag = false;
				visit[floor] = true;
				if (floor + U <= F && !visit[floor + U]) {
					queue.offer(floor + U);
					visit[floor + U] = true;
				}

				if (floor - D >= 1 && !visit[floor - D]) {
					queue.offer(floor - D);
					visit[floor - D] = true;
				}
			}
			if (!flag)
				break;
			cnt++;
		}

		return flag;

	}
}

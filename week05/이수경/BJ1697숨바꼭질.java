package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ1697숨바꼭질 {
	static int N, K;
	static boolean[] isVisited = new boolean[100001]; // 방문 체크
	static Deque<Integer> q = new ArrayDeque<>();
	static boolean flag;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		q.add(N);
		isVisited[N] = true;
		int time = 0;
		
		while (!q.isEmpty()) {
			if (flag) break;
			
			int q_size = q.size();
			time++;
			
			for (int i = 0; i < q_size; i++) {

				int top = q.peek();
				q.remove();

				if (top == K) {
					flag = true;
					break;
				}
				// top + 1 < 100001 조건이 먼저 와야함 
				if (top + 1 < 100001 && !isVisited[top + 1]) { 
					q.add(top + 1);
					isVisited[top + 1] = true;
				}
				if (top - 1 >= 0 && !isVisited[top - 1]) {
					q.add(top - 1);
					isVisited[top - 1] = true;
				}
				if (top * 2 < 100001 && !isVisited[top * 2]) {
					q.add(top * 2);
					isVisited[top * 2] = true;
				}

			}

		}

		System.out.println(time - 1);
	}

}

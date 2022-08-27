// BOJ 1697번 숨바꼭질 

package BOJ.DfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1697 {

	static int N, K, ans;
	static Queue<Integer> q;
	static boolean[] checked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		q = new ArrayDeque<>();
		checked = new boolean[100001];

		ans = 0;

		if (N == K) {	// 처음부터 같은 위치가 입력될 경우 
			System.out.println(0);
		} else {
			bfs();
			System.out.println(ans);
		}
	}

	static void bfs() {
		q.add(N);
		checked[N] = true;

		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				int current = q.poll();
				if (current + 1 == K || current - 1 == K || current * 2 == K) {
					q.clear();
					break;
				}

				if (!checked[current + 1]) {
					q.add(current + 1);
					checked[current + 1] = true;
				}

				if (!checked[current - 1]) {
					q.add(current - 1);
					checked[current - 1] = true;
				}

				if (!checked[current * 2]) {
					q.add(current * 2);
					checked[current * 2] = true;
				}
			}
			ans++;
		}
	}
}

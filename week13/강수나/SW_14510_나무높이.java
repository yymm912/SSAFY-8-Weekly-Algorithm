package a22_10_25;

import java.util.*;
import java.io.*;

public class SW_14510_나무높이 {

	static Queue<Integer> even_q, odd_q;
	static int N, ans, max;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N];
			max = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tree[i]);
			} // 입력 끝

			ans = 0;
			solution();
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void solution() {
		even_q = new ArrayDeque<>();
		odd_q = new ArrayDeque<>();

		for (int num = 0; num < N; num++) {
			if (tree[num] == max) continue;

			int n = max - tree[num];
			// 초기화
			for (int i = 0; i < n / 2; i++) {
				even_q.offer(2);
			}
			if (n % 2 != 0) odd_q.offer(1);
		}

		// 계산
		if (even_q.size() >= odd_q.size()) {
			while (true) {
				if (even_q.size() <= odd_q.size()) {
					ans = even_q.size() + odd_q.size();
					break;
				}
				even_q.poll();
				odd_q.offer(1);
				odd_q.offer(1);
			}
		} else if (even_q.size() < odd_q.size()) {
			ans += even_q.size() * 2;
			int gap = odd_q.size() - even_q.size();
			ans += gap + (gap - 1);
		}
	}
}

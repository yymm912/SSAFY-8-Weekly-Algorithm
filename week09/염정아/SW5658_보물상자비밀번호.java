package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;


public class SW5658_보물상자비밀번호 {
	static int ans, N, K, T;

	static HashSet<Integer> set = new HashSet<>();
	static Deque<Character> q = new ArrayDeque<>();

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = -1;
			q.clear();
			set.clear();
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				q.offer(line.charAt(i));
			}

			// 시뮬레이션
			int R = N / 4;
			for (int r = 0; r < R; r++) {
				char ch = q.pollLast();
				q.addFirst(ch);
				check();
			}

			List<Integer> tempSet = new ArrayList<>(set);
			Collections.sort(tempSet, Collections.reverseOrder());
			ans = tempSet.get(K - 1);

			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb.toString());

	} // end main


	// 한변씩 끊어 16진수 -> 10진수 변환하여 set에 추가하는 함수
	private static void check() {
		Object[] tempQ = q.toArray();

		for (int i = 0; i < N; i += (N / 4)) {
			String hex = "";
			for (int j = 0; j < N / 4; j++) hex += tempQ[i + j];
			set.add(Integer.parseInt(hex, 16));
		}

	} // end check
}

package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1182_부분수열의합 {
	static int N, S;
	static int[] arr;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		subset(0);

		if (S == 0)
			System.out.println(answer - 1);
		else
			System.out.println(answer);

	}

	static void subset(int cnt) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					sum += arr[i];
			}
			if (sum == S)
				answer++;
			return;

		}
		visited[cnt] = true;
		subset(cnt + 1);
		visited[cnt] = false;
		subset(cnt + 1);
	}

}

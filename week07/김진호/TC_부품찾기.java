import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
5
8 3 7 9 2
3
5 7 9
 */

public class TC_부품찾기 {
	static int N;
	static int[] input;
	static int M;
	static int[] target;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

		// 이분탐색 정렬된 상태에서 진행
		Arrays.sort(input);
		for (int i = 0; i < M; i++) {
			System.out.print(bs(target[i], 0, N - 1) + " ");
		}

	}

	static String bs(int target, int start, int end) {

		// 값을 찾지 못했을 때
		if (start > end) {
			return "no";
		}
		int mid = (start + end) / 2;
		if (input[mid] == target)
			return "yes";

		// target값이 현재 mid의 값보다 작으면 작은쪽으로 이동
		if (input[mid] > target)
			return bs(target, start, mid - 1);
		// target값이 현재 mid의 값보다 크다면 큰쪽으로 이동
		else
			return bs(target, mid + 1, end);

	}

}

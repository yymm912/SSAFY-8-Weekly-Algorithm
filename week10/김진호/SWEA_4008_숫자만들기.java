import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008_숫자만들기 {
	static int T;
	static int N;
	static int[] arr;
	static int answer;
	static int max;
	static int min;
	static int[] operator;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			operator = new int[4];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			dfs(arr[0], 1, operator[0], operator[1], operator[2], operator[3]);
			System.out.println("#" + t + " " + (max - min));
		}

	}

	static void dfs(int result, int count, int plus, int minus, int multiple, int divide) {

		if (count == N) {
			if (result < min) {
				min = result;
			}

			if (result > max) {
				max = result;
			}
		}

		if (plus > 0) {
			dfs(result + arr[count], count + 1, plus - 1, minus, multiple, divide);
		}
		if (minus > 0) {
			dfs(result - arr[count], count + 1, plus, minus - 1, multiple, divide);
		}
		if (multiple > 0) {
			dfs(result * arr[count], count + 1, plus, minus, multiple - 1, divide);
		}
		if (divide > 0) {
			dfs(result / arr[count], count + 1, plus, minus, multiple, divide - 1);
		}

	}

}

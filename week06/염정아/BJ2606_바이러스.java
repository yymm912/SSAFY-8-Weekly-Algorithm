package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// N: 컴퓨터 수 
// K: 연결되어있는 컴퓨터의 수 
// ans: 웜 바이러스에 걸리게 되는 컴퓨터의 수 
public class BJ2606_바이러스 {
	static int ans, N, K;
	static int[][] matrix;
	static boolean[] visit;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		matrix = new int[N + 1][N + 1]; // 0 dummy
		visit = new boolean[N + 1];

		// 인접 행렬
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[from][to] = 1;
			matrix[to][from] = 1;
		}

		// for (int i = 1; i <= N; i++) System.out.println(Arrays.toString(matrix[i]));

		dfs(1);

		System.out.println(ans - 1);

	} // end main


	private static void dfs(int v) {
		// 기저 조건

		// 할일
		visit[v] = true;
		ans++;

		for (int i = 1; i <= N; i++) {
			if (!visit[i] && matrix[v][i] == 1) dfs(i);
		}
		// 다음 재귀 방문

	} // end dfs
}

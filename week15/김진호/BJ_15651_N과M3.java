import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15651_N과M3 {
	static int N, M;
	static int src[];
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();

		src = new int[M];
		perm(0);
		System.out.println(sb.toString());
	}

	static void perm(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(src[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {

			src[cnt] = i;
			perm(cnt + 1);
			src[cnt] = 0;
		}
	}

}

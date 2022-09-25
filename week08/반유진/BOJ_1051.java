// BOJ 1051번 숫자 정사각형  

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1051 {

	static int N, M, result = 1;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j] - '0';
			}
		}

		int len = Math.min(N, M) - 1;

		outer: for (int l = len; l > 0; l--) {
			for (int i = 0; i + l < N; i++) {
				for (int j = 0; j + l < M; j++) {
					if (map[i][j] == map[i + l][j] && map[i + l][j] == map[i][j + l]
							&& map[i][j + l] == map[i + l][j + l]) {
						l += 1;
						result = l * l;
						break outer;
					}
				}
			}
		}

		System.out.println(result);
	}

}
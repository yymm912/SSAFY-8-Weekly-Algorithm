import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2630_색종이만들기 {

	static int N;
	static int map[][];
	static int white, blue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, N);

		System.out.println(white);
		System.out.println(blue);

	}

	static void divide(int y, int x, int size) {

		if (!check(y, x, size)) {
			
			divide(y, x, size / 2);
			divide(y, x + size / 2, size / 2);
			divide(y + size / 2, x, size / 2);
			divide(y + size / 2, x + size / 2, size / 2);
		} else {
			if (map[y][x] == 0)
				white++;
			else
				blue++;
			return;
		}
	}

	static boolean check(int y, int x, int size) {
		int paper = map[y][x];
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (map[i][j] != paper)
					return false;
			}
		}
		return true;

	}
}

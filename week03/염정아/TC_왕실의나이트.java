package problem.TC;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//a1
//-> 2

//c2
//-> 6


// 구현 문제 
public class TC_왕실의나이트 {
	static int initY, initX, ans;
	static int[][] map = new int[9][9]; // 0 dummy

	static int[] dy = { -1, -1, 1, 1, -2, -2, 2, 2 };
	static int[] dx = { -2, 2, -2, 2, -1, 1, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] loc = br.readLine().toCharArray();
		initY = loc[1] - '0';
		initX = loc[0] - 'a' + 1;

		// System.out.println(initY + " " + initX);

		for (int d = 0; d < 8; d++) {
			int ny = initY + dy[d];
			int nx = initX + dx[d];

			if (ny < 1 || ny >= 9 || nx < 1 || nx >= 9) continue;

			ans++;
		}

		System.out.println(ans);

	}
}

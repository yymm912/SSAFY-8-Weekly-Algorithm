// BOJ 1343번 폴리오미노 

package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_1343 {

	static String board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = br.readLine();

		board = board.replaceAll("XXXX", "AAAA");
		board = board.replaceAll("XX", "BB");

		if (board.contains("X"))
			System.out.println(-1);
		else
			System.out.println(board);
	}

}

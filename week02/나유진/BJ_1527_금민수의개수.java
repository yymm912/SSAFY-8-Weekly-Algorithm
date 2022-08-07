package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_1527_geum_min_su {

	static ArrayList<Long> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int ans = 0;

		rec(4);
		rec(7);
		
		for (long n : arr) {
			if(n>=A&&n<=B)ans++;
		}
		
		System.out.println(ans);
	}

	static void rec(long n) {
		if (n > 1000000000)
			return;
		else {
		arr.add(n);
		rec(n * 10 + 4);
		rec(n * 10 + 7);
		}
	}

}
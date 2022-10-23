package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2661 {
	static int N;
	static String ans = "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		m(0, "");
		System.out.println(ans);
	}

	public static boolean m(int cnt, String val) {
		if (cnt == N) {
			if (isOK(val)) {
				if (ans.isEmpty())ans = val;
				for (int i = 0; i < N; i++) {
					if (val.charAt(i) < ans.charAt(i)) {
						ans = val;
						break;
					} else if (val.charAt(i) > ans.charAt(i))break;
				}
				return true;
			}
			return false;
		}

		if (isOK(val)) {
			if(m(cnt + 1, val + "1")) return true;
			if(m(cnt + 1, val + "2")) return true;
			if(m(cnt + 1, val + "3")) return true;
		}
		
		return false;
	}

	public static boolean isOK(String str) {
		int even = 2;
		int len = str.length();
		while (even <= len) {
			String subStr = str.substring(len - even, len - even + even / 2);
			String subStr2 = str.substring(len - even + even / 2);
			if (subStr.equals(subStr2))
				return false;
			even += 2;
		}

		return true;
	}
}

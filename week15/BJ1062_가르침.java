import java.io.*;
import java.util.*;

public class Main {
	static int N, K, max = Integer.MIN_VALUE;
	static String[] words;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			str = str.replace("anta", "");
			str = str.replace("tica", "");

			words[i] = str;			
		}
		
		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		}
		
		v = new boolean[26];
		v['a' - 'a'] = true;
		v['n' - 'a'] = true;
		v['t' - 'a'] = true;
		v['i' - 'a'] = true;
		v['c' - 'a'] = true;
		
		teach(0, 0);
		System.out.println(max);
	}
	static void teach(int depth, int len) {
		if (len == K - 5) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				boolean isRead = true;
				for (int j = 0; j < words[i].length(); j++) {
					if (!v[words[i].charAt(j) - 'a']) {
						isRead = false;
						break;
					}
				}
				if (isRead) cnt++;
			}
			max = Integer.max(max, cnt);
		}
		for (int i = depth; i < 26; i++) {
			if (!v[i]) {
				v[i] = true;
				teach(i, len+1);
				v[i] = false;
			}
		}
	}
}

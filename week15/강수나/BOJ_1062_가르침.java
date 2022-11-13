package a22_11_10;

import java.io.*;
import java.util.*;

public class BOJ_1062_가르침 {

	static int N, K, ans;
	static boolean[] visited = new boolean[26];
	static List<String> words = new ArrayList<> ();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 5;
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			s = s.substring(4, s.length()-4);
			words.add(s);
		} //입력 끝
		if (K<0) {
			System.out.println(0);
			return;
		}
		else if (K==21) {
			System.out.println(N);
			return;
		}
		
		visited['a'-'a'] = true;
		visited['n'-'a'] = true;
		visited['t'-'a'] = true;
		visited['i'-'a'] = true;
		visited['c'-'a'] = true;
		
		comb(0,0);
		System.out.println(ans);
	}
	
	static void comb( int idx, int start ) {
		if (idx == K) {
			int res = getCnt();
			ans = Math.max(ans, res);
			return;
		}
		
		for (int i=start; i<26; i++) {
			if (visited[i] == true) continue;
			
			visited[i] = true;
			comb(idx+1, i+1);
			visited[i] = false;
		}
	}
	
	static int getCnt() {
		int cnt = 0;

		for (int i=0; i<N; i++) { //남극의 단어들
			boolean isCan = true;
			int size = words.get(i).length();
			for (char c=0; c<size; c++) {
				if (visited[words.get(i).charAt(c)-'a'] == false) {
					isCan = false;
					break;
				}
			}
			if (isCan == true) cnt++;
		}

		return cnt;
	}
}

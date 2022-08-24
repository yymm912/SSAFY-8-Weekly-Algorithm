package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1759암호만들기 {

	static int L, C;
	static char src[];
	static List<Character> tgt = new ArrayList<>();
	static char m[] = { 'a', 'e', 'i', 'o', 'u' };
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		src = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			src[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(src); // 오름차순 정렬

		comb(0, 0);

	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == L) {
			cnt = 0;

			for(int i=0;i<m.length;i++) {
				if(tgt.contains(m[i])) cnt++;
			}
			// 전체 개수 - 모음 개수 = 자음 개수
			if (cnt < 1 || L - cnt < 2)
				return; // 모음 개수 1개미만이거나 자음 개수 2개미만이면 안됌

			for (int i = 0; i < L; i++) {
				System.out.print(tgt.get(i));
			}
			System.out.println();
			return;
		}

		if (srcIdx == src.length)
			return;

		tgt.add(src[srcIdx]);
		comb(srcIdx + 1, tgtIdx + 1);
		tgt.remove((Character)src[srcIdx]);
		comb(srcIdx + 1, tgtIdx);


	}
}

/*
4 6
a t c i s w
 * 
 */

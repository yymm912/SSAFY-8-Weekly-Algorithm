package forStudy.week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// dfs + 가지치기
public class BJ2309_난쟁이 {
	
	static int B = 9, A = 7; // before, after 난쟁이 수
	static int[] src = new int[B];
	static int[] tgt = new int[A];
	static boolean isEnd = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<B; i++) {
			src[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
	}
	
	static void comb(int srcIdx, int cnt, int sum) {
		if(isEnd) return;
		if(sum > 100) return;
		
		if(cnt == A) {
			if(sum == 100) {
				Arrays.sort(tgt);
				for(int i=0; i<A; i++) {
					System.out.println(tgt[i]);
				}
				isEnd = true;
			}
			return;
		}
		
		if(srcIdx == B) return;
		
		tgt[cnt] = src[srcIdx];
		comb(srcIdx + 1, cnt + 1, sum + src[srcIdx]);
		comb(srcIdx + 1, cnt, sum);
	}

}

package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10974모든순열 {

	static int N;
	static boolean select[];
	static int tgt[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		select = new boolean[N+1];
		tgt = new int[N+1];
		
		perm(1);
		
	}
	
	
	static void perm(int tgtIdx) {
		
		if( tgtIdx == N+1 ) {
			for(int i=1;i<=N;i++) {
				System.out.print(tgt[i] + " ");
			}
			
			System.out.println();
			return;
		}
		
		for(int i=1;i<=N;i++) {
		
			if(select[i]) continue;
			tgt[tgtIdx] = i;
			select[i] = true;
		
			perm(tgtIdx + 1);
			select[i] = false;
			
		}
		
	}

}

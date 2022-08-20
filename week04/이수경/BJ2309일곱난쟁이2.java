package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2309일곱난쟁이2 {

	static int dwarf[] = new int[9]; // 입력 
	static int tgt[] = new int[7];
	static int sum;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<9;i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(dwarf);
		comb(0, 0);
		
		for (int t : tgt) {
			System.out.println(t);
		}
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if( tgtIdx == 7 ) {
			sum = 0;
			for(int i=0;i<7;i++) {
				sum += tgt[i];
			}
			
			if( sum == 100 )flag = true;
			return;
		}
		
		if(srcIdx == 9) return;
		
		tgt[tgtIdx] = dwarf[srcIdx];
		comb( srcIdx + 1, tgtIdx + 1);
		if(flag) return;
		comb( srcIdx + 1, tgtIdx );
		
	}
}

/*
합이 100
20
7
23
19
10
15
25
8
13

*/
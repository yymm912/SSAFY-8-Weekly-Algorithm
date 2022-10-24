

import java.io.*;
import java.util.*;

public class SW_2115_벌꿀채취 {

	static int[][] map;
	static int N, M, C, ans, honeyMax0, honeyMax1;
	static int[] tgt;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			tgt = new int[2];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력 끝
			
			ans=0;
			comb(0,0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void comb(int tgtIdx, int srcIdx) {
		if (srcIdx==N*N) return;
		if (tgtIdx==2) {
			
			if ( tgt[0]+M-1 >= tgt[1] ) return;
			if ( ( tgt[0]/N != (tgt[0]+M-1)/N ) || ( tgt[1]/N != (tgt[1]+M-1)/N ) ) return;
			
			int res = getMaxHoney();
			ans = Math.max(ans, res);
			
			return;
		}
		tgt[tgtIdx] = srcIdx;
		comb(tgtIdx+1, srcIdx+1);
		comb(tgtIdx, srcIdx+1);
	}
	
	static int getMaxHoney() {
		honeyMax0 = 0;
		isSelected = new boolean[N*N];
		subset(0, tgt[0], tgt[0]+M, 0, 0);
		honeyMax1 = 0;
		isSelected = new boolean[N*N];
		subset(1, tgt[1], tgt[1]+M, 0, 0);
		
		return honeyMax0 + honeyMax1;
	}
	
	static void subset(int mode, int idx, int end, int sum, int powSum) {
		if (sum > C) return;
		if (idx == end) {
			if (mode==0) {
				honeyMax0 = Math.max(honeyMax0, powSum);
			} else if (mode == 1) {
				honeyMax1 = Math.max(honeyMax1, powSum);
			}
			return;
		}
		
		int y = idx/N;
		int x = idx%N;
		
		isSelected[idx] = true;
		subset(mode, idx+1, end, sum+map[y][x], powSum+map[y][x]*map[y][x]);
		isSelected[idx] = false;
		subset(mode, idx+1, end, sum, powSum);
		
	}
}

package a22_11_20.study;

import java.io.*;
import java.util.*;

public class SW_2112_보호필름 {

	static int D, W, K, ans;
	static int[][] map, cmap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			cmap = new int[D][W];
			for (int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cmap[i][j] = map[i][j];
				}
			} //입력 끝
			
			ans = Integer.MAX_VALUE;
			dfs(0,0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void dfs(int row, int cnt) {
		if (cnt >= ans) return;
		if (row == D) {
			
			if (check()==true) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		dfs(row+1, cnt);
		
		A(row);
		dfs(row+1, cnt+1);
		
		B(row);
		dfs(row+1, cnt+1);
		
		restore(row);
	}
	
	static void A(int row) {
		for (int i=0; i<W; i++) {
			cmap[row][i] = 0;
		}
	}
	
	static void B(int row) {
		for (int i=0; i<W; i++) {
			cmap[row][i] = 1;
		}
	}
	
	static void restore(int row) {
		for (int i=0; i<W; i++) {
			cmap[row][i] = map[row][i];
		}
	}
	
	static boolean check() {
		if (K==1) return true;
		int last=0, now=0, cnt=0;
		boolean colCan = false;
		
		for (int i=0; i<W; i++) {
			cnt=1;
			last = cmap[0][i];
			colCan=false;
			for (int j=1; j<D; j++) {
				now = cmap[j][i];
				if (last == now) cnt++;
				else cnt=1;
				if (cnt==K) {
					colCan = true;
					break;
				}
				last = now;
			}
			if (colCan == false) return false;
		}
		return true;
	}
	
}

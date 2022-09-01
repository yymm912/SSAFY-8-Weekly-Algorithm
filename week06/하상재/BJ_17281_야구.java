package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17281_야구 {
	
	static int N, ans, tmp, turn;
	static int[][] point;
	static int[] tgt;
	static boolean[] visited, base;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		tgt = new int[9];
		tgt[3] = 1;
		visited = new boolean[10];
		
		point = new int[N+1][10];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<10; j++) point[i][j] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		
		System.out.println(ans);
		
	}
	
	static void perm(int depth) {
		
		if(depth==3) {
			perm(depth+1);
			return;
		}
		
		if(depth==9) {
			turn = 0;
			base = new boolean[3];
			tmp = 0;
			for(int i=0; i<N; i++) {
				dfs(i,0,0,0);
			}
			ans = Math.max(ans, tmp);
			return;
		}
		
		for(int i=2; i<10; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			tgt[depth] = i;
			perm(depth+1);
			visited[i] = false;
		}
		
		
	}
	
	static void dfs(int game, int outCnt, int sum, int bit) {
		if(outCnt==3) {
			tmp += sum;
			return;
		}
		int this_point = 0;
		
		switch(point[game][tgt[turn]]) {
		
		case 1:
			bit = bit << 1;
			bit |= 1;
			if( (bit & 1 << 3) != 0 ) this_point++;
			bit = bit & 7;
			turn = (turn+1) % 9;
			dfs( game, outCnt, sum+this_point, bit);
			break;
		case 2:
			bit = bit << 2;
			bit |= 2;
			if( (bit & 1 << 3) != 0 ) this_point++;
			if( (bit & 1 << 4) != 0 ) this_point++;
			bit = bit & 7;
			turn = (turn+1) % 9;
			dfs( game, outCnt, sum+this_point, bit);
			break;
		case 3:
			bit = bit << 3;
			bit |= 4;
			if( (bit & 1 << 3) != 0 ) this_point++;
			if( (bit & 1 << 4) != 0 ) this_point++;
			if( (bit & 1 << 5) != 0 ) this_point++;
			bit = bit & 7;
			turn = (turn+1) % 9;
			dfs( game, outCnt, sum+this_point, bit);
			break;
		case 4:
			this_point++;
			if( (bit & 1 << 0) != 0 ) this_point++;
			if( (bit & 1 << 1) != 0 ) this_point++;
			if( (bit & 1 << 2) != 0 ) this_point++;
			bit = bit & 0;
			turn = (turn+1) % 9;
			dfs( game, outCnt, sum+this_point, bit);
			break;
		default :
			turn = (turn+1) % 9;
			dfs( game, outCnt+1, sum, bit);
			break;
		}
		
	}
	
}
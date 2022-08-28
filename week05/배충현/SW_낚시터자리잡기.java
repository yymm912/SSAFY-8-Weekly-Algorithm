package ssafy;

import java.io.*;
import java.util.*;

public class SW_낚시터자리잡기 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, ans, total, INF = 919191919;
	static int[] enters, nums, perm, board, input;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			enters = new int[3];
			nums = new int[3];
			perm = new int[] {0, 1, 2};
			total = 0;
			
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				enters[i] = Integer.parseInt(stk.nextToken());
				nums[i] = Integer.parseInt(stk.nextToken());
				total += nums[i];
			}
			
			ans = Integer.MAX_VALUE;
			
			do {
				board = new int[N+1];	// 0부터 N-1까지 낚시터 자리
				visited = new boolean[N+1];
				input = new int[total];
				int idx = 0;
				for (int i = 0; i < 3; i++) {
					Arrays.fill(input, idx, idx+nums[perm[i]], enters[perm[i]]);
					idx += nums[perm[i]];
				}
				solve(0, 0);
			} while(np());
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb);
		
		
	}
	
	static void solve(int idx, int sum) {
		if (idx == total) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if (sum > ans) return;
		
		int l = 0, r = 0, ld = 1, rd = 1;
		for (int np = input[idx]; np > 0; np--) {
			if (visited[np]) {
				if (np == 1) ld = INF;
				ld++;
			}
			else {
				l = np;
				break;
			}
		}
		for (int np = input[idx]; np <= N; np++) {
			if (visited[np]) {
				if (np == N) rd = INF;
				rd++;
			}
			else {
				r = np;
				break;
			}
		}
		if (ld < rd) {
			visited[l] = true;
			solve(idx+1, sum+ld);
			visited[l] = false;
		} else if (rd < ld) {
			visited[r] = true;
			solve(idx+1, sum+rd);
			visited[r] = false;
		} else if (rd == ld) {
			if (idx < total-1 && input[idx] != input[idx+1]) {
				visited[l] = true;
				solve(idx+1, sum+ld);
				visited[l] = false;
				visited[r] = true;
				solve(idx+1, sum+rd);
				visited[r] = false;
			} else {
				visited[r] = true;
				solve(idx+1, sum+rd);
				visited[r] = false;
			}
		}
		
	}
	
	static boolean np() {
		int i = perm.length-1;
		while(i>0 && perm[i-1] > perm[i]) i--;
		if (i == 0) return false;
		
		int j = perm.length-1;
		while(perm[i-1] > perm[j]) j--;
		
		int tmp = perm[j];
		perm[j] = perm[i-1];
		perm[i-1] = tmp;
		
		int k = perm.length-1;
		while (i < k) {
			tmp = perm[k];
			perm[k] = perm[i];
			perm[i] = tmp;
			k--; i++;
		}
		
		return true;
	}
	
}

package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {
	
	static int N, L, answer;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		answer = 0;
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rowCheck(0);
		colCheck(0);
		System.out.println(answer);
		
	}
	
	
	
	static void rowCheck(int depth) {
		if(depth==N) return;
		
		boolean[] hill = new boolean[N];
		int i = 1;
		int before = map[depth][0];
		boolean out = false;
		
		// 진행방향 오른쪽 기준
		for( i=1; i<N; i++) {
			if(out) break;
			// 내리막 길
			if(map[depth][i] < before) {
				if(before > map[depth][i]+1) {
					out = true;
					break;
				}
				for(int j=0; j<L; j++) {
					if(i+j == N) {
						out = true;
						break;
					}
					if (map[depth][i+j] == map[depth][i]) {
						hill[i+j] = true;
					}
					else {
						out = true;
						break;
					}
				}
				
				if(out) break;
				before = map[depth][i+L-1];
			}
			// 오르막길
			else if(map[depth][i]>before) {
				
				if(before+1 < map[depth][i]) {
					out = true;
					break;
				}
				
				for(int j=1; j<=L; j++) {
					if(i-j < 0) {
						out = true;
						break;
					}
					
					if (!hill[i-j] && map[depth][i-j] == before) {
						hill[i-j] = true;
					}
					else {
						out = true;
						break;
					}
				}
				
				if(out) break;
				before = map[depth][i];
				
			}
			else {
				before = map[depth][i];
			}
		}
		
		if(!out) answer++;
		rowCheck(depth+1);
	}
	
	static void colCheck(int depth) {
		if(depth==N) return;
		
		boolean[] hill = new boolean[N];
		int i = 1;
		int before = map[0][depth];
		boolean out = false;
		
		// 진행방향 아래쪽 기준
		for( i=1; i<N; i++) {
			if(out) break;
			// 내리막 길
			if(map[i][depth] < before) {
				if(before > map[i][depth]+1) {
					out = true;
					break;
				}
				for(int j=0; j<L; j++) {
					if(i+j == N) {
						out = true;
						break;
					}
					if (map[i+j][depth] == map[i][depth]) {
						hill[i+j] = true;
					}
					else {
						out = true;
						break;
					}
				}
				
				if(out) break;
				before = map[i+L-1][depth];
			}
			// 오르막길
			else if(map[i][depth]>before) {
				
				if(before+1 < map[i][depth]) {
					out = true;
					break;
				}
				for(int j=1; j<=L; j++) {
					if(i-j < 0) {
						out = true;
						break;
					}
					if (!hill[i-j] && map[i-j][depth] == before) {
						hill[i-j] = true;
					}
					else {
						out = true;
						break;
					}
				}
				
				if(out) break;
				before = map[i][depth];
				
			}
			else {
				before = map[i][depth];
			}
		}
		
		if(!out) answer++;
		colCheck(depth+1);
	}
	
}
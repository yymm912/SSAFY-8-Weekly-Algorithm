package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17143 {
	static int R, C, M;
	static int sum;
	
	static int[][] sharkRoom;
	static SHARK[] shark; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		sharkRoom = new int[R][C];
		for(int i = 0; i < R; i++)
			Arrays.fill(sharkRoom[i], -1);
		
		shark = new SHARK[M];
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(stk.nextToken()) - 1;
			int c = Integer.parseInt(stk.nextToken()) - 1;
			int s = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			int z = Integer.parseInt(stk.nextToken());
			
			sharkRoom[r][c] = i;
			shark[i] = new SHARK(r,c,s,d,z);
			
		}
		
		int king = -1;
		
		while(king < C - 1) {
			// 낚시왕 이동 
			king++;
			
			// 낚시왕이 상어 잡음
			for(int i = 0; i < R; i++) {
				if(sharkRoom[i][king] != -1) {
					sum += shark[sharkRoom[i][king]].z;
					
					shark[sharkRoom[i][king]] = null;
					sharkRoom[i][king] = -1;
					break;
				}
			}
			
			// 상어가 이동
			sharkMove();
		}
		
		System.out.println(sum);
	}
	
	static void sharkMove() {
		for(int i = 0; i < R; i++)
			Arrays.fill(sharkRoom[i], -1);
		
		for(int i = 0; i < M; i++) {
			if(shark[i] == null) continue;
			SHARK s = shark[i];
			
			int moveCnt = 0;
			if(s.d <= 2) { //위 아래
				moveCnt = s.s;
				if(s.r == R - 1) s.d = 1;
				else if(s.r == 0) s.d = 2;
				
				if(s.d == 2) {					
					moveCnt -= (R - 1) - s.r;
					s.r = R - 1;
					s.d = 1;
					
					if(moveCnt < 0) {
						s.r += moveCnt;
						s.d = 2;
					}
				}else {
					moveCnt -= s.r;
					s.r = 0;
					s.d = 2;
					
					if(moveCnt < 0) {
						s.r -= moveCnt;
						s.d = 1;
					}
				}
				
				if(moveCnt > 0) {
					int n = R - 1;
					moveCnt %= (n*2);
					
					if(moveCnt <= n) {
						if(s.d == 2) s.r += moveCnt;
						else s.r -= moveCnt;
					}else {
						if(s.d == 2) {
							s.r += (n * 2) - moveCnt;
							s.d = 1;
						}
						else {
							s.r -= (n * 2) - moveCnt;
							s.d = 2;
						}
					}
				}
			}else { // 오른쪽 왼쪽
				moveCnt = s.s;
				if(s.c == C - 1) s.d = 4;
				else if(s.c == 0) s.d = 3;
				
				if(s.d == 3) {					
					moveCnt -= (C - 1) - s.c;
					s.c = C - 1;
					s.d = 4;
					
					if(moveCnt < 0) {
						s.c += moveCnt;
						s.d = 3;
					}
				}else {
					moveCnt -= s.c;
					s.c = 0;
					s.d = 3;
					
					if(moveCnt < 0) {
						s.c -= moveCnt;
						s.d = 4;
					}
				}
				
				if(moveCnt > 0) {
					int n = C - 1;
					moveCnt %= (n*2);
					
					if(moveCnt <= n) {
						if(s.d == 3) s.c += moveCnt;
						else s.c -= moveCnt;
					}else {
						if(s.d == 3) {
							s.c += (n * 2) - moveCnt;
							s.d = 4;
						}
						else {
							s.c -= (n * 2) - moveCnt;
							s.d = 3;
						}
					}
				}
			}
			
			if(sharkRoom[s.r][s.c] == -1) {
				sharkRoom[s.r][s.c] = i;
				continue;
			}
			
			int there = sharkRoom[s.r][s.c];
			if(shark[there].z > s.z) s = shark[i] = null;
			else {
				shark[there] = null;
				sharkRoom[s.r][s.c] = i; 
			}
		}
	}
	
	static class SHARK{
		int r, c, s, d, z;

		public SHARK(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
}

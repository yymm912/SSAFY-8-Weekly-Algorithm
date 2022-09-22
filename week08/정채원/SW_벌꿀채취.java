import java.io.*;
import java.util.*;
public class Solution {
	/**
	 하나 최대 선택 -> 다음 최대 선택하면 X : 둘 다 최선이 아닐때 결과는 최선이 될수 있다. (그리디 X)
	 => 완전탐색.
	 */
	/*
	 1. 한 행당 선택할 수 있는 벌통 시퀀스 경우의수 미리 구해둠 -> 제곱 합
	 	한 행당 N-M+1개가 나옴
	 2. 2개 선택할 수 있는 경우 -> s1이 있을 떄, s1+M=s2가 됨, s2+M < N이면 같은 행에 존재 가능, 아니면 다른행. 
	 * */
	static int T, N, M, C;
	static int[][] honey;
	static int[][] honeyPowSeq;
	static int x1, y1, x2, y2; // M 시작 위치
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honey = new int[N][N];
			honeyPowSeq = new int[N][N-M+1];
			x1=-1; y1=0; x2=0; y2=0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 벌통 시퀀스의 C이하 합 & 제곱합 미리 구하기
			for(int y=0; y<N; y++) {
				for(int x=0; x<N-M+1; x++) {
					getMaxHoney(y, x, x+M, 0, 0); // 조합으로 M개중 최선인 벌통 시퀀스 구함
				}
			}
			
			int maxHoney = 0;
			while(findM1()) { // x1, x2, y1, y2 결정
				x2 = x1+M-1; y2 = y1;
				while(findM2()) {
					maxHoney = Math.max(honeyPowSeq[y1][x1] + honeyPowSeq[y2][x2], maxHoney);
				}
			}
			System.out.println("#" + tc + " " + maxHoney);
		}
	}
	static boolean findM1() {
		x1++;
		if(x1-1 + M < N) return true; // 현재 행 선택할 곳 있음
		if(y1 == N-1 && x1-1 + (M<<1) >= N) return false; // m2 올 자리 고려
		y1++; x1 = 0; // 다음 행
		return true;
	}
	static boolean findM2() {
		x2++;
		if(x2-1 + M < N) return true; // 현재 행 선택할 곳 있음
		if(y2 == N-1 && x2-1 + M >= N) return false; // 마지막까지 완료
		y2++; x2 = 0; // 다음 행
		return true;
	}
	static void getMaxHoney(int y, int x, int xlim, int sum, int powSum) {
		if(x == xlim) {
			honeyPowSeq[y][xlim-M] = Math.max(powSum, honeyPowSeq[y][xlim-M]);
			return;
		}
		for(int i=x; i<xlim; i++) {
			int h = honey[y][i];
			if(sum+h <= C) {
				getMaxHoney(y, i+1, xlim, sum+h, powSum + h*h); // 현재 꿀 선택, 다음 꿀 ㄱ
			} 
			getMaxHoney(y, i+1, xlim, sum, powSum); // 현재 꿀 x, 다음 꿀 ㄱ				
		}
	}
}

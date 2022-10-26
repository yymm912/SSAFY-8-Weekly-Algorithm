package a22_10_26;

import java.io.*;
import java.util.*;

public class BOJ_17779_게리멘더링2 {

	static int[] people;
	static int[][] map;
	static int N, ans, total;
	static boolean[][] line; //경계선
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		} //입력 끝
		
		ans = Integer.MAX_VALUE;
		solution();
		System.out.println(ans);
	}
	
	static void solution() {
		//모든 좌표에 대해서 시도
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				
				for (int d1=1; d1<N; d1++) {
					for (int d2=1; d2<N; d2++) {
						if ((i==1&&j==1) || (i==N&&j==1) || (i==N&&j==1) || (i==N&&j==N)) continue;
						if (j-d1 >= 1 && j+d2 <= N && i+d1+d2 <= N) { //경계선 만들 수 있을 때
							int res = solution(i, j, d1, d2);
							ans = Math.min(ans, res);
						}
						
					}
				}
				
			}
		}
	}
	
	static int solution(int sy, int sx, int d1, int d2) {
		
		people = new int[6];
		line = new boolean[N+1][N+1];
		int res = 0;
		
		//경계선 구함
		for (int d = 0; d<=d1; d++) {
			line[sy+d][sx-d] = true;
		}
		for (int d = 0; d<=d2; d++) {
			line[sy+d][sx+d] = true;
		}
		for (int d = 0; d<=d2; d++) {
			line[sy+d1+d][sx-d1+d] = true;
		}
		for (int d = 0; d<=d1; d++) {
			line[sy+d2+d][sx+d2-d] = true;
		}
		
		//1번구역
		for (int y=1; y<sy+d1; y++) {
			for (int x=1; x<=sx; x++) {
				if (line[y][x]) break;
				people[1] += map[y][x];
			}
		}
		
		//2번구역
		for (int y=1; y<=sy+d2; y++) {
			for (int x=N; x>=sx+1; x--) {
				if (line[y][x]) break;
				people[2] += map[y][x];
			}
		}
		
		// 3번구역
		for (int y = N; y >=sy+d1 ; y--) {
			for (int x =1; x <sx-d1+d2 ; x++) {
				if (line[y][x]) break;
				people[3] += map[y][x];
			}
		}
		
		// 4번구역
		for (int y = N; y >= sy+d2+1; y--) {
			for (int x = N; x >= sx - d1+d2; x--) {
				if (line[y][x]) break;
				people[4] += map[y][x];
			}
		}

		//5번구역
		int sum=0;
		for (int i=1; i<=4; i++) {
			sum+=people[i];
		}
		people[5] = total - sum;
		
		//max, min 찾기
		int max=0;
		int min=Integer.MAX_VALUE;
		for (int i=1; i<=5; i++) {
			max = Math.max(max, people[i]);
			min = Math.min(min, people[i]);
		}
		return max-min;
	}
	
}

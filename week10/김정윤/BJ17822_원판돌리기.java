package week10.김정윤;

import java.io.*;
import java.util.*;

public class BJ17822_원판돌리기 {
	static int N, M, T, x, d, k;
	static int[][] disk;
	static HashSet<int[]> set = new HashSet<>(); 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		disk = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				disk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 원판 배수 번호
			d = Integer.parseInt(st.nextToken()); // 방향(0: 시계, 1: 반시계)
			k = Integer.parseInt(st.nextToken()); // 회전할 칸의 개수
			
			int tmp = x;
			while (tmp <= N) {
				for (int j = 0; j < k; j++) {
					// 원판 회전
					rotate(tmp);
				}
				tmp += x;
			}
			// 원판에서 인접하면서 같은 수를 모두 지운다.
			delete();
			
			if (set.size()!= 0) {
				for (int[] s : set) {
					disk[s[0]][s[1]] = 0;
				}
			} else {
				// 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
				calculate();
			}
		}
		// 원판의 합 구하기
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				ans += disk[i][j];
			}
		}
		
		System.out.println(ans);
	}
	static void calculate() {
		double sum = 0, cnt = 0, avg = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (disk[i][j] != 0) {
					sum += disk[i][j];
					cnt++;
				}
			}
		}

		if (cnt != 0) {
			avg = sum / cnt; // 평균
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (disk[i][j] == 0) continue;
				// 평균보다 작은 경우 +1
				if (avg > disk[i][j]) disk[i][j]++;
				// 평균보다 큰 경우 -1
				else if (avg < disk[i][j]) disk[i][j]--;
			}
		}
	}
	
	static void delete() {
		set.clear();
		// 같은 원판 내 인접
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < M; j++) {
				if (disk[i][j] == 0) continue;
				if (j == 1) { // (i, M)은 (i, M-1), (i, 1)과 인접함
					if (disk[i][1] == disk[i][M]) {
						set.add(new int[] {i, 1});
						set.add(new int[] {i, M});
					}
				} 
				if (disk[i][j] == disk[i][j+1]) { // (i, j)는 (i, j-1), (i, j+1)과 인접
					set.add(new int[] {i, j});
					set.add(new int[] {i, j+1});
				}
			}
		}
		// 다른 원판과 인접
		for(int i = 1; i < N; i++) {
            for(int j = 1; j <= M; j++) {
                if(disk[i][j] == 0) continue;
                if(disk[i][j] == disk[i+1][j]) { // (i, j)는 (i-1, j), (i+1, j)와 인접
                	set.add(new int[] {i, j});
					set.add(new int[] {i+1, j});
                }
            }
        }
	}
	static void rotate(int i) {
		int tmp;
		if (d == 0) { // 시계방향 회전
			tmp = disk[i][M];
			for (int j = M; j > 1; j--) {
				disk[i][j] = disk[i][j-1];
			}
			disk[i][1] = tmp;
		} 
		
		else { // 반시계방향 회전
			tmp = disk[i][1];
			for (int j = 1; j < M; j++) {
				disk[i][j] = disk[i][j+1];
			}
			disk[i][M] = tmp;
		}
	}
}

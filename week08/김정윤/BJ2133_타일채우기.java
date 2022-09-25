package week8.김정윤;

import java.io.*;
import java.util.*;

public class BJ2133_타일채우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] wall = new int[N+1];
		int ans = 0;
		
		// N이 홀수인 경우 타일을 채울 수 없음
		if (N%2 == 1) {
			ans = 0;
		} else {
			wall[0] = 1; // 벽이 존재하지 않음 = 타일이 모두 채워져 있음
			wall[2] = 3; // 3X2 크기 벽을 채울 수 있는 모든 경우의 수
			// N = 4 이상의 타일부터 예외타일이 2개씩 존재함 =>
			// wall[N] = (wall[N-2] * wall[2]) + (wall[N-4] * 2) + (wall[N-6] * 2) + ... + (wall[0] * 2)
			for (int i = 4; i <= N; i+=2) {
				wall[i] = wall[i-1] * wall[2];
				for (int j = i-4; j >= 0; j-=2) {
					wall[i] += wall[j] * 2;
				}
			}
			
			ans = wall[N];
		}
		System.out.println(ans);
		
	}

}

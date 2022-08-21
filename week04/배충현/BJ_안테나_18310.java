package ssafy.algorithm.study.w04;

import java.io.*;
import java.util.*;

public class BJ_안테나_18310 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static long dist, tmpDist;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(stk.nextToken());
			dist += input[i];
		}
		Arrays.sort(input);

		if (N == 1) {
			System.out.println(input[0]);
			return;
		}
		
		// 첫번째 집에 있을 때 총 거리가 얼마인가? dist
		tmpDist = dist -= input[0];
		ans = 100001;
		
		int lh = 0;						// 왼쪽에 있는 집 개수
		int diff = 0;
		
		// 다음 집 넘어가면 어떻게 변하는가? hCnt 만큼 diffH 증가. N-hCnt 만큼 diffH 감소
		for (int h = 1; h < N; h++) {
			lh++;
			diff = input[h] - input[h-1];
			dist += diff * lh;
			dist -= diff * (N-lh);
			// 거리가 줄어들면 ans 를 바꾼다.
			// 오름차순 정렬이라도 같은 집이 계속 나오면 초기값이 출력된다.
			if (tmpDist > dist) {
				ans = input[h];
				tmpDist = dist;
			} else if (tmpDist == dist) {
				ans = Math.min(ans, input[h]);
			}
		}
		
		System.out.println(ans);
		
	}
}

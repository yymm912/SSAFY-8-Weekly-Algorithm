package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_벌꿀채취_2115 {
	
	static int T, N, M, C, ans, sum1, sum2;
	static int[][] map;
	
	static boolean[][] checked;
	static boolean[] select;
	
	// 조합
	static int[] src;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			checked = new boolean[N][N];
			
			ans = 0;
			
			sum1 = 0;
			sum2 = 0;
			
			
			src = new int[N * N];
			tgt = new int[2];
			
			for (int i = 0; i < N * N; i++) {
				src[i] = i;
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 조합을 사용해 N * N 중 2가지 경우를 구하고
			comb(0, 0);
			// 답을 찾는다.
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 2) {
			// complete code
			
			// 첫번째 일꾼
			int x1 = tgt[0] / N;
			int y1 = tgt[0] % N;
			
			// 두번째 일꾼
			int x2 = tgt[1] / N;
			int y2 = tgt[1] % N;
			
			// 일꾼이 일을 시작하는 시작점 + M이 map의 끝보다 길면 return
			if (y1 + M > N || y2 + M > N) return;
			
			// 일꾼 1과 일꾼 2의 일터가 겹쳐도 return
			if (x1 == x2 && (y1 + M - 1 >= y2 || y2 + M - 1 >= y1)) return;
			
			// 일단 위 조건을 모두 pass 했다면 꿀을 채취한다.
			sum1 = 0;
			sum2 = 0;
			
			int[] s1 = new int[M];
			for (int i = 0; i < M; i++) {
				s1[i] = map[x1][y1 + i];
			}
			
			select = new boolean[M];
			subset(0, s1, 1);
			
			int[] s2 = new int[M];
			for (int i = 0; i < M; i++) {
				s2[i] = map[x2][y2 + i];
			}
			
			Arrays.fill(select, false);
			
			subset(0, s2, 2);
			
			ans = Math.max(ans, sum1 + sum2);
			
			return;
		}
		
		if (srcIdx == N * N) return;
		
		tgt[tgtIdx] = src[srcIdx];
		
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static void subset(int srcIdx, int[] arr, int num) {
		if (srcIdx == M) {
			// complete code
			// 1번 일꾼 -> sum1에 저장
			int sum = 0;
			int squaredSum = 0;
			for (int i = 0; i < M; i++) {
				if (select[i]) { 
					sum += arr[i];
					squaredSum += (int) Math.pow(arr[i], 2);
				}
				
				// 통의 꿀이 C보다 크면 안된다.
				if (sum > C) return;
			}
			
			if (num == 1) sum1 = Math.max(sum1, squaredSum); 
			else sum2 = Math.max(sum2, squaredSum);
			
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1, arr, num);
		
		select[srcIdx] = false;
		subset(srcIdx + 1, arr, num);
	}
}

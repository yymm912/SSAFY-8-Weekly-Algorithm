package week5.김정윤;

import java.io.*;
import java.util.*;

// 메모리 초과
public class BJ1916_최소비용구하기 {
	static int N, M, start, end, min;
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from][to] = map[to][from] = cost;
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		find(start, 0); // 시작지점부터
		System.out.println(min);
	}
	static void find(int s, int sum) {
		v = new boolean[N+1][N+1];
		
		// 기저조건
		if (s == end) { // 끝지점을 만나면 종료
//			System.out.println(sum);
			min = Math.min(min, sum); // 비용 합의 최소값 리턴
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (s < i && map[s][i] != 0 && !v[s][i]) { // 시작 지점보다 큰 숫자이면서 0이 아니고 아직 방문한적 없는 경우
				v[s][i] = v[i][s] = true; // 방문 완료
//				System.out.println(s+" "+i + " : " + map[s][i]);
				find(i, sum + map[s][i]); // 다음 시작점으로 재탐색(끝지점 만날때까지)
			}
		}

	}

}

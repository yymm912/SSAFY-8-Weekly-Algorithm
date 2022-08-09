package week3.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {
	static int N, K, seekTime = Integer.MAX_VALUE;
	static boolean[] isVisit = new boolean[100001];
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		hideAndseek(N);
		System.out.println(seekTime);
	}
	
	static void hideAndseek(int start) {
		q.offer(new int[] {start, 0}); // 현재 위치, 찾는 시간
		
		while(!q.isEmpty()) {
			int current[] = q.poll();
			int X = current[0]; // 현재 위치
			int thisTime = current[1]; // 찾는 시간
			isVisit[X] = true; // 현재 위치 방문 확인
			if (X == K)
				seekTime = Math.min(thisTime, seekTime); // 동생을 찾는 최소 시간
			
			int next;
			next = X * 2; // 순간이동 - 최우선
			isWalk(next, thisTime); // 순간이동의 경우 0초 소요
			
			next = X - 1; // 걷기
			isWalk(next, thisTime+1); // 1초 소요
			
			next = X + 1; // 걷기
			isWalk(next, thisTime+1); // 1초 소요
		}
	}
	
	static void isWalk(int next, int thisTime) {
		if (next >= 0 && next < 10001 && !isVisit[next]) {
			isVisit[next] = true; // 다음 방문할 위치 방문 확인
			q.offer(new int[] {next, thisTime}); // 현재 위치 <- 다음 방문할 위치
		}
	}
}

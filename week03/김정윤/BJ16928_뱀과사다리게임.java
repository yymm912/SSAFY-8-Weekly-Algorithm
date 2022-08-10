package week3.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16928_뱀과사다리게임 {
	static int N, M; // N - 사다리 개수, M - 뱀 개수
	static int[] rst = new int[101]; // 주사위 굴린 횟수
	static int[] LadderOrSnake = new int[101]; // 사다리 Or 뱀 목적지 정보
	static boolean[] isVisit = new boolean[101]; // 방문여부 확인
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			LadderOrSnake[from] = to;
		}
		playGame();
	}
	static void playGame() {
		q.offer(1);
		rst[1] = 0;
		isVisit[1] = true;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			// 기저 조건
			if (current == 100) { // 게임 종료
				System.out.println(rst[current]); // rst - 주사위 굴린 횟수 저장
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int next = current + i; // 현재 위치 + 주사위 눈금 횟수
				if (100 < next || isVisit[next]) continue; // 다음 방문할 위치가 100 이상이거나 이미 방문한 경우 pass 
				
				if (LadderOrSnake[next] != 0) { // 사다리 혹은 뱀 위치일 경우
					if (next < N) // 뱀을 탈 때 도착한 칸을 방문처리 x <= 뱀을 타고 내려가서 더 빠르게 도착하는 경우가 있기 때문
						isVisit[LadderOrSnake[next]] = true;
					rst[LadderOrSnake[next]] = rst[current] + 1; // 방문할 위치에 현재 위치의 값(주사위 굴린 횟수) + 1
					q.offer(LadderOrSnake[next]); // 사다리나 뱀의 목적지 위치
				} else { // 사다리나 뱀의 위치가 아닐 경우
					isVisit[next] = true;
					rst[next] = rst[current] + 1; // 목적지에 주사위 굴린횟수 + 1
					q.offer(next); // 목적지 위치
				}
			}
			
		}
	}

}

package week6.김정윤;

import java.io.*;
import java.util.*;

public class BJ5014_스타트링크 {
	static int F, S, G, U, D, min = Integer.MAX_VALUE;
	static int[] direction = new int[2];
	static int[] v ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); // 총 건물 층수
		S = Integer.parseInt(st.nextToken()); // 시작 지점
		G = Integer.parseInt(st.nextToken()); // 도착 지점
		direction[0] = Integer.parseInt(st.nextToken()); // 위로가는 층수
		direction[1] = -Integer.parseInt(st.nextToken()); // 아래로 가는 층수(-로 설정)
		
		// 1층부터 F층 까지. 0은 dummy
		v = new int[F+1];
		bfs(S, 0);
	}
	static void bfs(int start, int sum) {
		Queue<Integer> q = new ArrayDeque<>();
		v[start] = 1; // 시작 층수 방문 확인
		q.add(start);
		
		while (!q.isEmpty()) {
			int current = q.poll();
			if (current == G) { // 목적지에 도착한 경우
				System.out.println(v[current]-1); // 버튼 누른 횟수 출력(시작 층수 포함되어 있기 때문에 -1)
			}
			for (int i = 0; i < 2; i++) { // 위 or 아래
				int next = current + direction[i]; // 다음으로 갈 층수
				if (next <= F && next >= 1 && v[next] == 0) { // 1층 이상, F층 이하, 아직 방문하지 않은 층수일 경우
					v[next] = v[current]+1; // 다음 층 방문 완료
					q.add(next);
				}
			}
		}
		// 반복문 이후로도 목적지에 방문하지 못했을 경우
		if (v[G] == 0) System.out.println("use the stairs");
	}

}

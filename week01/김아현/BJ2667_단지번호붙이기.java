package forStudy.week01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * <문제>
 * N*N의 지도가 있을 때, 1은 집이 있는곳 0은 집이 없는 곳을 나타낸다.
 * 지도를 탐색하면서 집이 모여있는 모임을 단지로 보고,
 * 단지가 몇 개인지, 단지 별로 집 수는 몇 채인지 카운트한다.
 * 
 * 입력 : 첫 줄에 N, 다음 줄부터 지도정보
 * 출력 : 단지 수와 단지 별 집 수를 한 줄 씩 띄워 출력
 **/

public class BJ2667_단지번호붙이기 {

	static int N;
	static int[][] map;		// 지도를 담을 배열
	static int[][] visited;	// 방문 확인을 위한 배열
	static ArrayList<Integer> cntList;	// 단지 별 집 수를 담는 리스트
	static int tmpCnt;		// 단지 별 집 수 카운터
	static int[] dx = {0, 0, 1, -1}; // 동-서-남-북
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String line = sc.next();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		visited = new int[N][N];
		cntList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && visited[i][j] == 0) {
					visited[i][j] = 1;
					tmpCnt = 1;
					DFS(i,j);
					cntList.add(tmpCnt);
				}
			}
		}
		
		// 결과 출력
		System.out.println(cntList.size());
		if(cntList.size() != 0) {
			Collections.sort(cntList);
			for (int cnt : cntList) {
				System.out.println(cnt);
			}
		}
		sc.close();
	}
	
	static void DFS(int x, int y) {
		for(int i=0; i<4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N) continue;
			if(map[tx][ty] == 1 && visited[tx][ty] == 0) {
				visited[tx][ty] = 1;
				tmpCnt++;
				
				DFS(tx, ty);
			}
		}
	}

}

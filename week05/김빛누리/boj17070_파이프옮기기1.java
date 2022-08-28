package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 풀이시간: 50분
 * 참고: X
 * 
 * 17540KB	320ms
 * 
 * <풀이방식>
 * - 파이프는 현재 지점에서 오른쪽, 아래쪽, 오른쪽아래로 끝점이 이동될 수 있다
 * - 현재 파이프 상태에 따라 다음 갈 수 있는 방향이 바뀌므로 dfs에 현재 위치(x, y) 와 파이프 상태(가로, 세로, 대각선)을 매개변수로 함
 * - 가로: 아래쪽 제외, 세로: 오른쪽 제외
 * - delta로 순회
 * - 오른쪽 또는 아래로만 이동하므로 visit 처리는 필요X
 * 
 * 
 * <삽질목록>
 * - 파이프 상태를 dfs에 안넣어서 꼬임
 * 
 * */

public class boj17070_파이프옮기기1 {
	static int N, result;	// map 크기, 결과
	static int[][] map;
	
	// 우 하 우하
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 0, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0);
		System.out.println(result);
	}

	// 파이프 놓인 모양
	// 0: 가로, 1: 세로, 2: 대각선
	static void dfs(int y, int x, int pipe) {
		if(y==N-1 && x==N-1) {
			result++;
			return;
		}
		for(int d = 0; d < 3; d++) {
			
			/*
			 * 파이프 가로(0) -> 아래(d[1]) 못감
			 * 세로(1) -> 오른쪽(d[0]) 못감 
			 * */
			if((pipe==0 && d==1)||(pipe==1 && d==0)) continue;
			
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(nx<0||ny<0||nx>=N||ny>=N||map[ny][nx] == 1) continue;
			
			if(d==2) {
				if(nx-1>=0 && map[ny][nx-1] == 1) continue;
				if(ny-1>=0 && map[ny-1][nx] == 1) continue;				
			}
			
			dfs(ny,nx, d);
		}
	}
}

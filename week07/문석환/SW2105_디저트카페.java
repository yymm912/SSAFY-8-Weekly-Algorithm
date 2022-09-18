package SWEA.모의SW역량테스트.디저트카페_2105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 번호 : 2105
// 난이도 : 모의문제
// 제목 : 디저트 카페
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
// 조건
// 1. 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 함 , 맵을 벗어나서는 안댐
// 2. 투어중에 같은 디저트를 파는 가게를 방문해서는 안댐
// 3. 서로 다른 디저트를 먹으며 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우 중 가장 많은 디저트를 먹을 수 있는 경로를 찾고 그때의 디저트 수를 출력
// 4. 디저트를 먹을 수 없는 경우 -1

public class Solution {
	static int T,N,ans,sy,sx; // sy : 시작 y , sx : 시작 x
	static boolean[] isAte;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	// 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위
	static int[][] dir = {{1,1},{1,-1},{-1,-1},{-1,1}};
	public static void main(String[] args) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // input End

			ans = -1; // 초기 값

			// 양옆과 밑에 2칸이 있어야 사각형 가능
			for(int i=0;i<N-2;i++) {
				for(int j=1;j<N-1;j++) {
					isAte = new boolean[101]; // 1~100까지의 디저트 숫자를 가지기 때문
					isAte[map[i][j]] = true; // 현재 가게 방문
					// 시작 점
					sy = i;
					sx = j;

					desertPath(i,j,-1,-1,0,0);
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void desertPath(int y,int x,int prevY,int prevX,int cnt, int sd) {
		// 대각선으로 이동
		for(int d = sd;d<4;d++) {
			int ny = y + dir[d][0];
			int nx = x + dir[d][1];

			// 맵을 벗어나는 경우
			if(ny < 0 || nx < 0 || ny >= N || nx >= N)continue;
			// 이전 카페로 돌아가는 경우
			if(ny == prevY && nx == prevX)continue;
			// 시작점으로 도착할 경우
			if(ny == sy && nx == sx) {
				ans = Math.max(ans, cnt+1);
				return;
			}

			// 이미 먹은 디저트인 경우
			if(isAte[map[ny][nx]])continue;

			// 디저트 먹기
			isAte[map[ny][nx]] = true;
			desertPath(ny,nx,y,x,cnt+1,d);
			isAte[map[ny][nx]] = false;
		}
	}
}

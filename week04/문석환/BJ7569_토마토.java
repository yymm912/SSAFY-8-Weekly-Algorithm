package BOJ.그래프탐색.토마토_7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호 : 7569
// 난이도 : 골드 5
// 제목 : 토마토
// https://www.acmicpc.net/problem/7569
// 3차원 배열로 풀 수 있으나 2차원배열로 풀어보고싶어서 2차원배열로 풀이
// bfs continue 조건
// 1. 가장자리 범위 벗어나는 경우
// 2. 방문한 경우
// 3. 토마토가 없는 공간인 경우
// 4. 2차원으로 구현했기 때문에 row에서 앞,뒤이동 했을 때 다음 층으로 넘어가는 경우도 체크해줘야함
// -> 예를 들어 N이 4짜리 인데 , row 3에서 뒤 이동해서 4로 가면 , 4는 다음 층의 토마토 이기 때문에 갈 수 없다.
// -> 이와 같은 부분들을 다 체크해줘야 함
public class Main {
	static int M,N,H,ans = Integer.MIN_VALUE;
	static int[][] map;
	static int[][] dir;
	static int[][] dist;
	static boolean[][] visit;
	// 위,아래,오른쪽,왼쪽,앞,뒤
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로 칸 수 : col
		N = Integer.parseInt(st.nextToken()); // 세로 칸 수 : row
		H = Integer.parseInt(st.nextToken()); // 높이
		map = new int[N*H][M];
		dir = new int[][] {{N,0},{-N,0},{1,0},{-1,0},{0,-1},{0,1}};
		dist = new int[N*H][M];
		visit = new boolean[N*H][M];
		for(int i=0;i<N*H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // read end
		System.out.println(bfs());


	}

	static int bfs() {
		Queue<Integer> Q = new ArrayDeque<Integer>();
		for(int i=0;i<N*H;i++) {
			for(int j=0;j<M;j++) {
				visit[i][j] = false;
				dist[i][j] = -1;
				if(map[i][j] == 1) {
					visit[i][j] = true;
					Q.offer(i);
					Q.offer(j);
					dist[i][j] = 0;
				}
			}
		}
		// 익은 토마토의 좌표를 모두 넣어줌
		while(!Q.isEmpty()) {
			int y = Q.poll();
			int x = Q.poll();
			for(int[] adj : dir) {
				int ny = y + adj[0];
				int nx = x + adj[1];
				// 아래로 이동 했는데 현재 층을 벗어나는 경우
				if(adj[0] == 1 && y / N != ny / N)continue;
				// 위로 이동했는데 현재 층을 벗어나는 경우
				if(adj[0] == -1 && y / N != ny / N)continue;
				if(ny < 0 || nx < 0 || ny >= N*H || nx >= M)continue;
				if(map[ny][nx] == -1)continue;
				if(visit[ny][nx])continue;
				visit[ny][nx] = true;
				Q.offer(ny);
				Q.offer(nx);

				dist[ny][nx] = dist[y][x] + 1;
			}
		}
		int ans = 0;
    // 모든 맵을 돌면서 가장 dist[][]이 큰 값을 구한다.
		for(int i=0;i<N*H;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == -1)continue; // 빈 칸인 경우는 패스 -> 빈 칸인 경우에도 dist는 -1이므로 패스 해줘야 익지 못한 토마토가 존재하는지 체크 가능
				if(dist[i][j] == -1) return -1; // 익지 못한 토마토가 존재하므로 -1 리턴
				ans = Math.max(ans,dist[i][j]); // 모든 배열 돌고나서 리턴이 안됐으면 모두 익은 경우
			}
		}

		return ans;
	}
}

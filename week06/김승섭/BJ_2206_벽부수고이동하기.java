package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
  벽을 부순 상태, 아직 부수지 않은 상태를 구분하는 배열을 따로 넣자니 bfs에서 많이 길을 잃었다.
  방법을 찾아보니 대부분이 3차원 형식의 방문배열을 사용해 각 위치의 방문여부를 확인했다.
  
	기존 BFS 탐색에서 벽을 부쉈는지 아닌지를 구분하는 차원을 추가한다. visited[][][0] or visited[][][1]
	모든 벽에 대해 wall 파괴 or 보존 경우를 나누고 진행한다.
	탐색 진행을 담은 queue가 다 비어 갈 곳이 없으면 -1을 출력한다.
*/
public class 벽부수고이동하기 {
	static int N, M, min;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][][] visited;
	// visited[y][x][break] ==> 3차원 boolean 배열로 y x 좌표에 벽을 부쉈는지 구분
	static Queue<Node> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(N == 0 && M == 0) {
			System.out.println(1);
			return;
		}
		map = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j-1) - '0';
			}
		}
		visited = new boolean[N+1][M+1][2];
		int ans = start(1, 1); // 1,1에서 시작
		System.out.println(ans);
	}
	
	static int start(int y, int x) {
		queue.offer(new Node(y, x, 1, 0));
		visited[y][x][0] = true; // 부쉈든 안부쉈든 두 경우 모두 이곳은 방문했음
		visited[y][x][1] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.y == N && cur.x == M) return cur.cnt;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
				
				if(ny <= 0 || nx <= 0 || ny > N || nx > M) continue;
				if(map[ny][nx] == 0) { // 진행 방향이 길일 때의 경우
					if(!visited[ny][nx][cur.wall]) { // 다음 진행 방향이 방문한 적이 없다면,
						queue.offer(new Node(ny, nx, cur.cnt+1, cur.wall)); // 다음 진행 방향 queue에 추가
						visited[ny][nx][cur.wall] = true;
					}
				}else { // 진행 방향이 벽일 경우
					if(cur.wall == 0 && !visited[ny][nx][1]) { // 다음 방향이 벽이지만 벽을 부순적이 없고 이미 부순후 방문한적이 없다면
						queue.offer(new Node(ny, nx, cur.cnt + 1, 1)); // 다음 방향을 queue에 추가
						visited[ny][nx][1] = true;
					}
				}
			}
		}
		return -1;
		
	}
	static class Node{ // x좌표 y좌표까지 cnt만큼 왔을 때 wall이 부쉈는지.
		int x, y, cnt, wall;
	public Node(int y, int x, int cnt, int wall) { // wall  0: 부순적없음 1: 이미 부숨
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.wall = wall;
    }
	}
}

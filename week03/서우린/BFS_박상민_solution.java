package _3주차;

import java.util.ArrayDeque;
import java.util.Queue;

public class sangmin_미로찾기 {
	 // 상민이는 지금부터 집을 찾아가려고하는데 술을 너무 많이 마셔서 집가는 길을 잊어버렸다.
    // 지금부터 모든 길을 돌아다니면서 집을 찾으려고 한다.
    // 1. 술집 (출발지)은 2로 표시가 되고, 집(도착지)는 3으로 표시가 된다.
    // 2. 갈 수 있는 길은 1로 표시가 되고, 갈수없는 벽은 0으로 표시가 된다.
    // 3. 상민이의 집의 위치와 술집에서 집까지 얼마나 걸리는지 출력해야한다.
    // 4. 1칸을 이동할 때마다 1분이 소요된다고 가정한다.
    // 5. 집까지 가는 길은 하나밖에 없다고 가정한다.
    // 출력예)
    // 1 6 9    (y x t)
    static int[][] map = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 1, 1, 0, 3},
            {0, 1, 0, 1, 0, 0, 1},    
            {0, 1, 1, 1, 1, 1, 1},    
            {0, 1, 0, 1, 0, 1, 0},    
            {0, 1, 0, 1, 0, 1, 0},    
            {0, 1, 0, 1, 0, 1, 1}};
    static int N = 7;
    static int [][] visited;
    static int [] st;
    static int [] ed;
    static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) {
		visited = new int[N][N];
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(map[i][j] == 2)
					st = new int[] {i,j};
				if(map[i][j] == 3)
					ed = new int[] {i,j};
			}
		}
		
		System.out.println(ed[0] + " "+ ed[1] + " " + bfs());
	}
	
	
	
	static class Point{
		int r,c;
		Point(int r,int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean outOfRange(int nr,int nc) {
		return nr<0||nr>=N||nc<0||nc>=N;
	}
	
	static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		visited[st[0]][st[1]] = 1;
		q.add(new Point(st[0],st[1]));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			//방문을 표시하기 위해 처음부터 1로 시작했기 때문에 -1하고 반환
			if(cur.r == ed[0] && cur.c == ed[1])
				return visited[cur.r][cur.c] - 1;
			
			for(int i = 0;i<4;i++) {
				int nr = cur.r + d[i][0];
				int nc = cur.c + d[i][1];
				
				//범위 바깥이거나 벽인 경우
				if(outOfRange(nr,nc) || map[nr][nc] == 0)
					continue;
				
				if(visited[nr][nc] == 0) {
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					q.add(new Point(nr,nc));
				}
			}
			
		}
		return -1;
	}

}

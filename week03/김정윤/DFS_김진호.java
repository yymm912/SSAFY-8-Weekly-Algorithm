package BASIC_BFS_DFS;

public class Pair {
	static boolean[][] visit;
	static int count;
	static int[][] map = { 
	        { 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 11, 12, 13, 14, 15, 16 }, 
	        { 0, 21, 22, 23, 24, 25, 26 },
	        { 0, 31, 32, 33, 34, 35, 36 }, 
	        { 0, 41, 42, 43, 44, 45, 46 }, 
	        { 0, 51, 52, 53, 54, 55, 56 },
	        { 0, 61, 62, 63, 64, 65, 66 }, };
	
	// delta : 상 - 하 - 좌 - 우
	// 짝수의 개수는 몇 개 일까요 ????????????????????????????????????????
	// dfs의 코드에서 추가해주세요 !
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) {
	
	    visit = new boolean[7][7];
	    dfs(1, 1);
	    System.out.println(count);
	
	}
	
	static void dfs(int y, int x) {
	    visit[y][x] = true;
	
	    for (int d = 0; d < 4; d++) {
	        int ny = y + dy[d];
	        int nx = x + dx[d];
	
	
	        if (ny < 1 || nx < 1 || ny >= 7 || nx >= 7 || visit[ny][nx])
	            continue;
	        
//	        if (map[nx][ny] % 2 == 0) 
//	        	count++;
//	        
	        dfs(ny, nx);
	    }
	
	}

}
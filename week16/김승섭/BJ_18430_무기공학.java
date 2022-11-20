import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18430_무기공학 {
	static int N, M, tree[][];
	static boolean makable;
	static boolean[][] visited;
	static int[] dy = {1, 1, -1, -1};
	static int[] dx = {1, -1, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N][M];

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ret = getBoom(0);
		
		System.out.println(ret);
	}
	
	private static int getBoom(int num) {
		int max = 0;
		for (int i = 0; i <N; i++) {
			for (int j = num; j < M; j++) {
				if(check(i, j)) continue;
				for (int d = 0; d < 4; d++) {
					int boom1 = i + dy[d];
					int boom2 = j + dx[d];
					if(check(boom1, j) || check(i, boom2)) continue;
					oppoCheck(i, j, boom1, boom2);
					max = Math.max(max, getBoom(j)+getSize(i, j, boom1, boom2));
					oppoCheck(i, j, boom1, boom2);
				}
			}
		}
		return max;
		
	}
  
	private static int getSize(int y, int x, int boom1, int boom2) {
		int sum = tree[y][x] * 2;
		sum += tree[boom1][x];
		sum += tree[y][boom2];
		return sum;
	}

	private static boolean check(int y, int x) {
		if(y < 0 || y > N-1 || x < 0 || x > N-1) return true;
		else return visited[y][x];
	}

	private static void oppoCheck(int y, int x, int boom1, int boom2) {
		visited[y][x] = (visited[y][x]) ? false : true; 
		visited[boom1][x] = (visited[boom1][x]) ? false : true; 
		visited[y][boom2] = (visited[y][boom2]) ? false : true; 
	}
	
	
	
//	private static void trim(int depth, int cnt) {
//		if(depth == M*N) {
//			max = Math.max(max, cnt);
//			return;
//		}
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				int rowCnt = 0;
//				int rowTotal = 0;
//				for (int d = 0; d < 4; d++) {
//					int ny = i + dy[d];
//					int nx = j + dx[d];
//					
//					if(ny < 0 || nx < 0 || ny >= N-1 || nx >= N-1 || visited[ny][nx]) {
//						rowCnt = 0;
//						rowTotal = 0;
//						continue;
//					}else {
//						rowCnt++;
//						visited[ny][nx] = true;
//						rowTotal += tree[ny][nx];
//						if(rowCnt == 2) {
//							visited[i][j] = true;
//							trim(depth+1, cnt+rowTotal+tree[i][j]);
//							visited[i][j] = false;
//						}else {
//							continue;
//						}
//					}
//					visited[ny][nx] = false;
//					visited[ny - dy[d-1]][nx - dx[d-1]] = false;
//				}
//				trim(depth+1, cnt);
//			}
//		}
//		
//	}

}

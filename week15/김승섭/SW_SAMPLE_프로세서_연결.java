import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_SAMPLE_프로세서_연결 {
	static int N, map[][], max, min, cellSize;
	static ArrayList<Cell> cells = new ArrayList<>();
	static class Cell{
		int y, x;

		public Cell(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			/*********************테스트**********************/
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(i != 0 && j != 0 && i != N-1 && j != N-1) {
						if(map[i][j] == 1) {
							cells.add(new Cell(i, j));
						}
					}
				}
			}
			cellSize = cells.size();
			dfs(0,0,0);
			System.out.println(min);

			/*********************케이스**********************/
		}
	}
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
	private static void dfs(int depth, int cnt, int len) {
		if(depth==cellSize) {
			if(max < cnt) {
				min = len;
				max = cnt;
			}else if(max == cnt) {
				if(len < min) {
					min = len;
				}
			}
			return;
		}
		
		Cell cur = cells.get(depth);
		
		for (int i = 0; i < dx.length; i++) {
			int count = 0;
			int sy = cur.y;
			int sx = cur.x;
			
			int ny = cur.y;
			int nx = cur.x;
		
		while(true) {
			ny = ny + dy[i];
			nx = nx + dx[i];
			
			if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1) break; // 벽으로 가면 경로 완성
			if(map[ny][nx]==1) { //프로세서 혹은 전선이 놓여져있다면 초기 위치로
				count=0;
				break;
			}
			count++;//벽이 아니고 갈 수 있는 Cell 이라면 전선을 놓고 전진
		}
		// 온만큼의 방향과 거리만큼 전선을 그림
		for (int j = 0; j < count; j++) {
			sy = sy + dy[i];
			sx = sx + dx[i];
			
			map[sy][sx] = 1;
		}
		// i방향으로 전선을 연결하지 못하는 코어는 패스
		if(count==0) {
			dfs(depth+1, cnt, len);
		}else { // 연결한 프로세서는 카운트+1, 총 길이 + count하고 다음 코어로
			dfs(depth+1, cnt+1, len+count);
			
			// 다음 경우의 수를 위해 dfs depth를 탈출한 case에 해당 전선을 지워준다
			sy = cur.y;
			sx = cur.x;
			for (int j = 0; j < count; j++) {
				sy += dy[i];
				sx += dx[i];
				
				map[sy][sx] = 0;
			}
		}
			
		}
		
		
		
	}

}

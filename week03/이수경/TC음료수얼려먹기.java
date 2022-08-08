import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC음료수얼려먹기 {
	static int N,M;
	static int ice[][];
	static boolean isVisited[][];
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1,1,  0, 0};
	
	public static void main(String[] args) throws Exception { 
		
		System.setIn(new FileInputStream("음료수얼려먹기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ice = new int[N][M];
		isVisited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				ice[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완
		int cnt = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!isVisited[i][j] && ice[i][j] == 0) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	static void dfs(int i, int j) {
		
		if(isVisited[i][j]) return;
		isVisited[i][j] = true;
		
		for(int d=0;d<4;d++) {
			int py = i + dy[d];
			int px = j + dx[d];
			
			if(py < 0 || py >= N || px < 0 || px >= M ) continue;
			if(!isVisited[py][px] && ice[py][px] == 0) {
				dfs(py, px);
			}
			
		}
		
	}
}

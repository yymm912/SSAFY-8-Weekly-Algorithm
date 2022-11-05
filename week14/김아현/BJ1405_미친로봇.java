import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1405_미친로봇 {

	static int N;
	static double res;
	static double[] percent;
	static boolean[][] visit;
	
	static int[] dx = {0, 0, 1, -1}; // 동서남북
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		percent = new double[4];
		for (int i = 0; i < 4; i++) {
			percent[i] = Double.parseDouble(st.nextToken()) * 0.01;
		} // 입력 끝
		
		// 초기위치로부터 동서남북 최대 14칸 이동 가능
		// 초기위치 (15, 15)로 설정 -> 패딩 주기
		visit = new boolean[30][30];
		res = 0;
		
		dfs(15, 15, 0, 1);
		
		System.out.println(res);
	}

	static void dfs(int x, int y, int cnt, double cal) {
		if(cnt == N) { // N번까지 이동했다면
			res += cal; // 누적해 온 확률값 더하기
			return;
		}
		
		if(cal == 0) return; // 갈 수 없는 확률이라면 return
		
		visit[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= 30 || ty >= 30 || visit[tx][ty]) continue;
			
			visit[tx][ty] = true;
			dfs(tx, ty, cnt+1, cal*percent[d]);
			visit[tx][ty] = false;
		}
	}
}

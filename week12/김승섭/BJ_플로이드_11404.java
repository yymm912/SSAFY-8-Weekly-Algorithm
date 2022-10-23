
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * 도시 A -> B로 가는데 필요한 최소 비용 구하기(플로이드 워셜 이용)
 * INF 값을 지정할 때, MAX_VALUE를 사용했더니 오버플로우가 발생해 INF 비교 연산이 터졌다.(0으로 마지막에 변환 못함)
 * Cost의 범위를 보고 INF값의 설정을 잘 해야겠다.
 */
public class BJ_플로이드_11404 {
	static int[][] drive;
	static final int INF = 987654321;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 도시 수(노드 수)
		int M = Integer.parseInt(br.readLine());	// 버스 수(간선 수)
		
		drive = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == j) drive[i][j] = 0;
				else drive[i][j] = INF;
			}
		}
		
		for (int bus = 0; bus < M; bus++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			drive[start][end] = Math.min(drive[start][end], cost);
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					drive[i][j] = Math.min(drive[i][k]+drive[k][j], drive[i][j]);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(drive[i][j] == INF) drive[i][j] = 0;
				System.out.print(drive[i][j]+ " ");
			}System.out.println();
		}

	}

}

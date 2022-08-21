import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 보고함ㅜ
 * 
 * 풀이시간: 1시간 남짓
 * 참고: 블로그 코드..
 * 
 * <풀이>
 * - 수빈이 위치가 동생보다 클 경우에는 -1로만 이동 가능함, 같을 경우에는 이동x => N-K
 * - 일반적인 경우
 * - bfs, visit 배열에 현재까지 이동 시간 기록
 * - visit 배열이 0이 아닌 경우에만 이동
 * 
 * <삽질목록>
 * - 처음에 dfs로 풀려고 했음
 * - visit를 깜빡함
 * - -1 방향으로 이동할 때 (n - 1) > 0 로 조건을 설정하여 0 위치가 방문되지 않음
 * 
 * */
public class boj1697_숨바꼭질 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if(N>=K) {	// 수빈이가 동생보다 더 큰 위치에 있을 경우 -1로밖에 이동 불가, 따라서 N-K
			System.out.println(N-K);
		}
		else 
			System.out.println(bfs());
	}

	static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();

		q.add(N);
		int[] visit = new int[100_001];
		visit[N] = 1;

		while (!q.isEmpty()) {
			int n = q.poll();
			
			if (n == K) {
				return visit[n]-1;
			}

			if ((n - 1) >= 0 && visit[n - 1] == 0) {
				visit[n-1] = visit[n]+1;
				q.add(n - 1);
			}
			if (n + 1 <= 100_000 && visit[n + 1] == 0) {
				visit[n + 1] = visit[n] + 1;
				q.add(n + 1);
			}

			if (2 * n <= 100_000 && visit[n * 2] == 0) {
				visit[n * 2] = visit[n] + 1;
				q.add(n * 2);
			}

		}
		return -1;
	}

}

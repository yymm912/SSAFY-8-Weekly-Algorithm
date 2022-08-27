package forStudy.week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697_숨바꼭질 {

	static int N, K;
	static int[] dist; // 이동시간을 담을 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new int[100001];
		
		if(N == K) { // 수빈이와 동생이 같은 위치에 있는 경우
			System.out.println(0);
		} else {
			bfs();
		}
	}
	
	static void bfs() {
		Queue<Integer> qu = new ArrayDeque<>();
		qu.offer(N);
		dist[N] = 1;
		
		while(!qu.isEmpty()) {
			int cur = qu.poll();
			for(int i=0; i<3; i++) {
				int next = cur;
				if(i == 0) {
					next *= 2;
				} else if(i == 1) {
					next += 1;
				} else if(i == 2) {
					next -= 1;
				}
				
				if(next == K) {
					System.out.println(dist[cur]);
					return;
				}
				
				if(next < 0 || next > 100000 || dist[next] != 0) continue;
				qu.offer(next);
				dist[next] = dist[cur] + 1;
			}
		}
	}

}

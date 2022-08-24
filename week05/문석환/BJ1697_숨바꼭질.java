import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호 : 1697
// 난이도 : 실버 1
// 제목 : 숨바꼭질
// https://www.acmicpc.net/problem/1697
public class Main {
	static int start,end;
	static int[] dist;
	static boolean[] visit;
	static int[] dir = {1,-1,2}; // +1 , -1 , *2
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dist = new int[100001];
		visit = new boolean[100001];
		bfs(start);
		System.out.println(dist[end]);
	}

	static void bfs(int s) {
		Queue<Integer> Q = new ArrayDeque<Integer>();
		Q.offer(s);
		dist[s] = 0;
		visit[s] = true;

		while(!Q.isEmpty()) {
			s = Q.poll();
			for(int x : dir) {
				int ns;
				if(x == 2) {
					ns = s * x;
				}else {
					ns = s + x;
				}

				if(ns < 0 || ns >100000)continue;
				if(visit[ns])continue;
				Q.offer(ns);
				visit[ns] = true;
				dist[ns] = dist[s] + 1;
			}
		}
	}
}

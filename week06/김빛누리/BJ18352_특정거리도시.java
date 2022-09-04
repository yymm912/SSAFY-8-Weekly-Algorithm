import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 1시간 10분
 * 참고: 다익스트라 코드:(
 * 
 * 258100KB		968ms
 * 
 * 
 * */
public class BJ18352_특정거리도시 {
	static int N, M, K;	// 도시 개수, 도로 개수, 거리 정보
	static List[] adj;

	static int[] dist;
	static LinkedList<Integer> result = new LinkedList<>();
	
	//static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 출발도시
		int X = Integer.parseInt(st.nextToken());
		
		// 최소거리 계산
		dist = new int[N+1];
		Arrays.fill(dist, -1);
		
		// 입력받아서 인접리스트
		adj = new List[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
		}
		
		
		bfs(X);
		
		// 출력
		if (result.isEmpty()) System.out.println(-1);
		else {
			Collections.sort(result);
			
			StringBuilder sb = new StringBuilder();
			while(!result.isEmpty()) {
				sb.append(result.pollFirst()).append("\n");
			}
			System.out.println(sb);
		}
		
	}

	private static void bfs(int x) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(x);
		dist[x] = 0;
		
		int len = 0;
		while(true) {
			if(len == K) {
				while(!q.isEmpty()) {
					int tmp = q.poll();
					if(dist[tmp] == K) {
						result.add(tmp);
					}
				}
				return;
			}
			
			int size = q.size();	
			for (int i = 0; i < size; i++) {
				int city = q.poll();
				
				int adjSize = adj[city].size();
				for (int j = 0; j < adjSize; j++) {
					int nextCity = (int) adj[city].get(j);
					
					if(dist[nextCity] != -1) continue;
					
					dist[nextCity] = len+1;
					q.add(nextCity);
		
				}
			}
			len++;
		}	
	}

}

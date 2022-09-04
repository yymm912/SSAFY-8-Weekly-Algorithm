import java.io.*;
import java.util.*;

public class Main{
	static class P{
		int pre = 0;
		List<Integer> aft = new ArrayList<Integer>();
		int level;
		public P(int level) {
			this.level = level;
		}
	}
	static int N, M;
	static P[] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new P[N];
		for(int i=0; i<N; i++) graph[i] = new P(i);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			graph[A].aft.add(B);
			graph[B].pre++;
		}
	
		StringBuilder ans = new StringBuilder();
		// pre가 0인 것을 넣어줌. (난이도 순으로)
		PriorityQueue<P> pq = new PriorityQueue<>((a, b) -> a.level - b.level);
		for(int i=0; i<N; i++) {
			if(graph[i].pre == 0) pq.add(graph[i]);
		}
		// 사이클이 없다고 가정..?
		while(!pq.isEmpty()) {
			P cur = pq.remove();
			ans.append(cur.level+1).append(" ");
			for(int aft : cur.aft) {
				graph[aft].pre--;
				// 다음으로 간선이 없어진 것들을 pq에 넣음
				if(graph[aft].pre == 0) {
					pq.add(graph[aft]);
				}
			}
		}
		System.out.println(ans);
	}
}
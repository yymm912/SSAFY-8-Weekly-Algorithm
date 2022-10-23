import java.io.*;
import java.util.*;

/**
 * 리프 노드 -> 
 * dfs로 가다가 리프노드를 만났을 때,
 * 	-> 연결된 다리 가중치를 return 함
 * 부모는 그걸 받아서, 자기 다리랑 return 다리 중, 더 작은 걸로. (끊어야 하는 다리를 반환함)
 * 
 * 자식 갈래가 여럿 잇을 경우, min(자기 다리를 끊는, 자식 다리들을 합한 다리를 끊는)
 * 
 * 리프노드가 없는데 방문한 노드이면 사이클-> return 0
 * dfs이므로 visit 체크는 들어갈때마다 새로 해줘야 함
 * 
 * 사이클일 경우,
 * */
class Main{
	static int T;
	static int N, M, D;
	static List<int[]>[] graph; // [startNode].get([endNode, weight])
	static int[] memoi, visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visit = new int[N+1]; // 사이클 체크용
			memoi = new int[N+1]; // 각 노드의 끊는 다리의 가중치 최소 합 저장 (사이클의 경우 : 0일수도 있다)
			for(int i=0; i<N+1; i++) {	memoi[i] = -1;	}
			graph = new ArrayList[N+1];
			for(int i=0; i<N+1; i++) graph[i] = new ArrayList<int[]>();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[s].add(new int[] {e, w});
				graph[e].add(new int[] {s, w});
			}
			
			visit[1] = 1;
			int sum = 0;
			for(int[] child: graph[1]) {
				sum += dfs(child[0], child[1]);
			}
			System.out.println(sum);
		}
	}
	static int dfs(int cur, int curW) { // 리프노드 -> ret w, 부모 노드 -> ret min(w, sum(자식합)), 
		if(memoi[cur] > -1) return memoi[cur];
		if(visit[cur] == 1) return memoi[cur] = 0; // 사이클이면 다리 안 끊으니까 return 0
		if(graph[cur].size() == 1) return memoi[cur] = curW; // 리프 노드
		
		visit[cur] = 1; 
		int childSum = 0;
		for(int[] child : graph[cur]) {
			int e = child[0], w = child[1];
			childSum += dfs(e, w);
		}
		visit[cur] = 0;
		
		// 나와 연결된 다리를 끊는거 vs 자식들과 연결된 다리를 끊는게 나을지
		return memoi[cur] = Math.min(curW, childSum); 
	}
}
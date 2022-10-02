
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이시간: 40분
 * 참고: X
 * 
 * <풀이방식>
 * - 이분탐색, bfs
 * 
 * - 인접한 섬, 옮기는 중량 제한을 입력받음
 * - 최소 무게, 최대 무게를 각각 left, right로 두어
 * - 이분탐색 수행
 * - bfs로 해당 중량의 다리를 start 섬에서 end 섬까지 옮길 수 있는지 확인
 * - result 값 구함
 * 
 * */
public class BJ1939_중량제한 {
	static int N, M;
	static int start, end;	// 시작 섬, 끝 섬
	static ArrayList<int[]>[] adj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new int[] {b, c});
			adj[b].add(new int[] {a, c});
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		
		// 이분탐색
		int left = 0;
		int right = 1_000_000_000;
		int result = 0;
		
		
		while(left<=right) {
			int mid = (left+right)/2;
			
			if(bfs(mid)) {	// mid 중량으로 시작 섬부터 끝 섬까지 도달할 수 있는 경우
				result = mid;	// result 업데이트
				left = mid+1;	// 더 큰 중량 탐색
			}else {	// 도달할 수 없는 경우
				right = mid-1;	// 더 작은 중량 탐색
			}
		}
		
		System.out.println(result);
	}
	
	static boolean bfs(int weight) {
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N+1];
		q.add(start);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			// 무사히 끝 섬에 도달
			if(node == end) return true;
			
			// 해당 섬의 인접 리스트 탐색
			ArrayList<int[]> list = adj[node];
			for (int i = 0; i < list.size(); i++) {
				int[] tmp = list.get(i);
				
				// 이미 방문했거나 중량제한에 걸려 다리 건너갈 수 없는 경우
				if(visit[tmp[0]] || tmp[1] < weight) continue;
				
				q.add(tmp[0]);
				visit[tmp[0]] = true;
			}
			
		}

		// 끝 섬에 도달하지 못하고 종료된 경우
		return false;
	}

}

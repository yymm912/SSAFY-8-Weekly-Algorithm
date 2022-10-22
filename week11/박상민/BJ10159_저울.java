import java.io.*;
import java.util.*;

// 사이클 없음
// 그래프 탐색
// 탐색할 수 없는 노드를 찾아라
public class BJ10159_저울 {

    static int N, M;
    static int[] answer;
    static List<Integer>[] graph, rGraph;
    static boolean[] v;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[N + 1];
        rGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            rGraph[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);        // 단방향
            rGraph[B].add(A);
        }

        answer = new int[N + 1];
        Arrays.fill(answer, 1);
        for (int i = 1; i <= N; i++) {
            bfs(i, graph);
            bfs(i, rGraph);
        }

        for (int i = 1; i <= N; i++){            
            sb.append(N - answer[i]).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void bfs(int start, List<Integer>[] graph){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];

        q.add(start);
        v[start] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]){
                if (v[next]) continue;
                answer[next]++;
                q.add(next);
                v[next] = true;
            }
        }
    }
}

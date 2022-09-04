package studygroup.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라 알고리즘
 */

public class BJ18352_특정_거리의_도시_찾기 {
    static class Edge{
        int v;//정점
        int c;//비용
        public Edge(int v, int c){
            this.v=v;
            this.c=c;
        }
    }
    static int N,M,K,X;
    static List<List<Edge>> vertex = new ArrayList<>();
    static boolean[] visited;
    static int[] cost;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->e1.c - e2.c);
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        cost = new int[N+1];

        for(int i=0; i<=N; i++){
            vertex.add(new ArrayList<Edge>());
            cost[i] = INF;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = 1;
            /**
             * 가중치가 1이라서 다익스트라가 아니어도 풀 수 있다.
             */
            vertex.get(v1).add(new Edge(v2,w));
        }

        dijkstra(X);
        StringBuilder sb =new StringBuilder();
        for (int i = 1; i <= N; i++) {
            //주어진 값에 만족한다면 i를 스트링빌더에 추가
           if(K == cost[i]){
               sb.append(i).append("\n");
           }
        }
        //스트링 빌더 사이즈가 0이란건 값을 찾을 수 없으니 -1
        if(sb.length()==0){
            System.out.println(-1);
        }
        else{
            System.out.println(sb);
        }
    }

    private static void dijkstra(int x) {
        //시작정점 x
        pq.offer(new Edge(x,0));
        // 시작 인덱스를 0으로 초기화
        cost[x] = 0;
        while(!pq.isEmpty()){
            //꺼내면 비용이 가장 적은 것
            Edge e = pq.poll();
            if(visited[e.v]) continue;

            //e.v 정점으로부터 갈 수 있는 다른 정점을 고려
            //고려하는 목적은 cost[] 갱신하기 위해서
            visited[e.v] = true;
            for(Edge ne : vertex.get(e.v)){
                if(!visited[ne.v] && cost[e.v] + ne.c < cost[ne.v]){
                    cost[ne.v] = cost[e.v] + ne.c;
                    pq.offer(new Edge(ne.v, cost[ne.v]));
                }
            }
        }
    }
}

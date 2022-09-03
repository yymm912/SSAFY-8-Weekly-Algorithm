import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11404

/*
    플로이드 와샬

    '거쳐가는 정점' 을 기준으로 최단 거리를 구하는 것

    for (경유할정점)
        for (시작점)
            for (종료점)
        
    ex) 2에서 3으로 가는 경로 중 가장 짧은 경로
        D[2, 3] = Math.min(D[2, 3], D[2, 경유할정점] + D[경유할정점, 3]);
 */

public class BJ11404_플로이드 {    

    static int N, M;
    static int[][] graph;
    static int INF = 100_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                graph[i][j] = INF;              // 시작 도시와 도착 도시가 같은 경우는 없다.
            }
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A][B] = Math.min(graph[A][B], C);     // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
        }

        for (int pass = 1; pass <= N; pass++){          // 경유할 정점
            for (int start = 1; start <= N; start++){   // 시작점
                for (int end = 1; end <= N; end++){     // 종료점
                    // 현재 경유하는 정점을 거쳐가는 경우와 현재 저장되어 있는 최소비용을 비교하여 가장 작은 비용으로 갱신
                    graph[start][end] = Math.min(graph[start][end], graph[start][pass] + graph[pass][end]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                sb.append(graph[i][j] == INF ? 0 : graph[i][j]).append(" ");    // 갈 수 있는 경로가 없는 경우 (INF 인 경우) 0으로 바꿔줘야함 -> 98% 터짐 원인
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

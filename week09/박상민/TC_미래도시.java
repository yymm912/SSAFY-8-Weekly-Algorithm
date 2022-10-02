import java.io.*;
import java.util.*;

public class TC_미래도시 {

    static int N, M, X, K;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++){
            Arrays.fill(cost[i], 1000_000_000);     // cost 배열 최댓값으로 초기화
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cost[a][b] = 1;         // 비용 1로 초기화
            cost[b][a] = 1;         // 비용 1로 초기화
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++){           // 출발정점
            for (int j = 1; j <= N; j++){       // 도착정점
                for (int k = 1; k <= N; k++){   // 경유하는정점
                    if (cost[i][j] + cost[j][k] < cost[i][k]) {     // 경유지를 거치는 경우와 바로가능 경우 중 
                        cost[i][k] = cost[i][j] + cost[j][k];       // 비용이 더 작은 것을 선택
                    }
                }
            }
        }

        System.out.println(cost[1][K] + cost[K][X]);            // 1 -> K -> X : 최소비용 선택
        br.close();
    }
}
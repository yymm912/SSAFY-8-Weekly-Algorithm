import java.io.*;
import java.util.*;

public class boj_거짓말_tableMinPark {

    static int N, M, P, PP, answer;
    static boolean[] know;
    static boolean[][] graph;
    static int[] party;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        
        // 알고있는 사람 저장
        know = new boolean[N + 1];
        for (int i = 0; i < P; i++) {
            int n = Integer.parseInt(st.nextToken());
            know[n] = true;
        }

        graph = new boolean[N + 1][N + 1];
        party = new int[M];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            PP = Integer.parseInt(st.nextToken());
            
            int pre = Integer.parseInt(st.nextToken());
            party[i] = pre;
            for (int j = 1; j < PP; j++){
                int now = Integer.parseInt(st.nextToken());
                graph[pre][now] = graph[now][pre] = true;                
            }
        }

        // 진실공개타임
        for (int i = 1; i <= N; i++){
            if (know[i]) {
                v = new boolean[N + 1];
                dfs(i);
            }
        }

        for (int i = 0; i < M; i++){
            if (!know[party[i]]) answer++;
        }


        System.out.println(answer);
        br.close();
    }

    static boolean[] v;
    static void dfs(int now){
        know[now] = true;
        for (int next = 1; next <= N; next++){
            if (graph[now][next] && !v[next]) {
                v[next] = true;
                dfs(next);
            }
        }
    }
}

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1976
public class TC_여행계획{
    
    static int N, M;
    static boolean answer;
    static int[][] graph;
    static int[] parents;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        makeSet();

        StringTokenizer st;
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) union(i, j);
            }
        }
        
        answer = true;
        st = new StringTokenizer(br.readLine());
        int sParent = findSet(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            if (sParent != findSet(Integer.parseInt(st.nextToken()))){
                answer = false;
                break;
            }
        }

        System.out.println(answer ? "YES" : "NO");
        br.close();
    }

    static void makeSet(){
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;
    }

    static int findSet(int x){
        if (parents[x] == x) return x;
        return parents[x] = findSet(parents[x]);
    }

    static void union(int a, int b){
        a = findSet(a);
        b = findSet(b);
        if (a == b) return;
        if (a > b) parents[a] = b;
        else parents[b] = a;
    }
}
import java.io.*;
import java.util.*;

public class BJ1717_집합의표현 {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        makeSet();

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            // 합집합
            if (c == 0) union(a, b);
            else {
                if (findSet(a) == findSet(b)) sb.append("YES");
                else sb.append("NO");
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    static void makeSet(){
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    static int findSet(int x){
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]);
    }

    static void union(int a, int b){
        a = findSet(a);
        b = findSet(b);
        if (a == b) return;
        if (a > b) parent[b] = a;
        else parent[a] = b;
    }
}
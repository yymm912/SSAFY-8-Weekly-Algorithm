import java.io.*;
import java.util.*;

public class BJ1697_숨바꼭질 {
    
    static int N, K, answer;
    static final int MAX = 100_001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;
        bfs();

        System.out.println(answer);
        br.close();
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        boolean[] v = new boolean[MAX];

        q.add(new Node(N, 0));
        v[N] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if (now.n == K) answer = Math.min(answer, now.c);
            
            int s = now.n - 1;
            int p = now.n + 1;
            int m = now.n * 2;
            // X - 1
            if (s >= 0 && !v[s]) {
                q.add(new Node(s, now.c + 1));
                v[s] = true;
            }

            // X + 1
            if (p < MAX && !v[p]) {
                q.add(new Node(p, now.c + 1));
                v[p] = true;
            }

            // X * 2
            if (m < MAX && !v[m]) {
                q.add(new Node(m, now.c + 1));
                v[m] = true;
            }
        }
    }

    static class Node{
        int n;
        int c;
        public Node(int n, int c){
            this.n = n;
            this.c = c;
        }
    }
}
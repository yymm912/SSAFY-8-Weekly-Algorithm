package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697_숨바꼭질 {

    static int N,K;
    static int[] visited = new int[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(N==K) {
            System.out.println(0);
            return;
        }

        bfs();
        System.out.println(visited[K]);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);
        while(!q.isEmpty()) {
            int n = q.poll();
            if(n==K)break;
            if(n>0 && visited[n-1]==0) {
                q.add(n-1);
                visited[n-1]=visited[n]+1;
            }
            if(n<100000 && visited[n+1]==0) {
                q.add(n+1);
                visited[n+1] = visited[n]+1;
            }
            if(n*2<=100000 && visited[n*2]==0) {
                q.add(n*2);
                visited[n*2] = visited[n]+1;
            }
        }
    }

}

import java.util.*;
import java.io.*;

public class BJ20304_비밀번호제작 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Deque<Integer> q = new ArrayDeque<Integer>();
    static int N, M, ans = 0;
    static int[] arr;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[1000001];
        for(int i = 1; i <= M; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[n] = 1;
            q.add(n);
        }

    }

    static void pro() {
        while(!q.isEmpty()) {
            int x = q.pop();
            for(int i = 0; i < 20; i++) {
                int nx = x^(1<<i);
                if(nx > N || arr[nx] != 0) continue;
                arr[nx] = arr[x]+1;
                q.add(nx);
                ans = arr[nx];
            }
        }
        System.out.println(ans - 1);

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
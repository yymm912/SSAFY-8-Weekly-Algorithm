package study;
import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] parent = new int[501]; 
    static ArrayList<Integer> plan = new ArrayList<>();

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            plan.add(x);
        }

        boolean res = true;

        for (int i = 0; i < M - 1; i++) {
            if (find(plan.get(i)) != find(plan.get(i + 1))) {
                res = false;
            }
        }

        System.out.println(res ? "YES" : "NO");
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

}
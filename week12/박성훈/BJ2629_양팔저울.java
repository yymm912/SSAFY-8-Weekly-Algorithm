import java.io.*;
import java.util.*;

public class BJ2629_양팔저울 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, t;
    static int[] choo;
    static boolean[][] kg;
    static void input() throws IOException{
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        kg = new boolean[n+1][40001];
        choo = new int[n];
        for(int i = 0; i < n; i++) choo[i] = Integer.parseInt(st.nextToken());
        go(0,0);
        t = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < t; i++) sb.append( kg[n][Integer.parseInt(st.nextToken())]  ? 'Y' : 'N' ).append(' ');

        System.out.println(sb.toString());
    }


    static void go(int idx, int num) {
        if(kg[idx][num]) return;
        kg[idx][num] = true;
        if(idx == n) return;
        go(idx+1, num + choo[idx]);
        go(idx+1, Math.abs(num - choo[idx]));
        go(idx + 1, num);


    }
    public static void main(String[] args) throws IOException {
        input();
    }
}

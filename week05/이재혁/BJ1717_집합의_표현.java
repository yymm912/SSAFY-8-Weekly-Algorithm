package baekjoon;

import java.util.*;
import java.io.*;

public class BJ1717_집합의_표현 {
    static int N,M;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        makeSet();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int a =Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());

            if(mode == 0){
                union(a,b);
            }

            else if(mode==1){
                if(findSet(a) == findSet(b)){
                    sb.append("yes").append('\n');
                }
                else{
                    sb.append("no").append('\n');
                }
            }
        }
        System.out.println(sb);
    }
    static void makeSet(){
        parents = new int[N+1];
        for(int i=0; i<=N; i++){
            parents[i] = i;
        }
    }
    static int findSet(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = findSet(parents[x]);
    }

    static boolean union(int x, int y){
        int px = findSet(x);
        int py = findSet(y);

        if(px == py){
            return false;
        }

        if(px<py){
            parents[py] = px;
        }
        else{
            parents[px] = py;
        }
        return true;
    }
}

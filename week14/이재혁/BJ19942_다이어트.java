package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ19942_다이어트 {
    static int N,ans=Integer.MAX_VALUE;
    static int map[][];
    static boolean[] target;
    static int[] base;
    static ArrayList<String> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][5];
        target = new boolean[N];
        base = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            base[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
             st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        list = new ArrayList<>();
        comb(0);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
            Collections.sort(list);
            System.out.println(list.get(0));
        }
    }

    private static void comb(int depth) {
        if(depth==N){
            check();
            return;
        }
        target[depth] = true;
        comb(depth+1);
        target[depth] = false;
        comb(depth+1);
    }

    private static void check() {
        int[] tmp = new int[5];
        for(int i=0; i<N; i++){
            if(target[i]){
                for(int j=0; j<5;j ++){
                    tmp[j]+=map[i][j];
                }
            }
        }

        for(int i=0; i<4; i++){
            if(tmp[i] < base[i]){
                return;
            }
        }
        if(ans >= tmp[4]){
            StringBuilder sb= new StringBuilder();
            for(int i=0; i<N; i++){
                if(target[i]){
                    sb.append(i+1+" ");
                }
            }
            if(ans == tmp[4]){
                list.add(sb.toString());
            }
            else{
                list.clear();
                list.add(sb.toString());
                ans = tmp[4];
            }
        }
    }
}

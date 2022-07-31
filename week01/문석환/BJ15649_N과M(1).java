package NandM2;

import java.io.*;
import java.util.StringTokenizer;

// N과M (1) : 시간복잡도를 낮추기 위해서 for문을 한번만 사용한 풀이
// https://www.acmicpc.net/problem/15649
public class BOJ15649_2 {
    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static int[] selected,used;

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
        used = new int[N+1];
    }

    static void rec_func(int k){
        if(k == M+1){
            for(int i=1; i<=M; i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        }else{
            for(int cand=1;cand<=N;cand++){
                if(used[cand] == 1){
                    // 사용한 숫자 이므로 다음 cand로 넘어감
                    continue;
                }

                selected[k] = cand;
                used[cand] = 1;
                rec_func(k+1);
                selected[k] = 0;
                used[cand] = 0;
            }

        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong(){
            return Long.parseLong(next());
        }

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }


    }
}

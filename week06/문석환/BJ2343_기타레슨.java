package week06.문석환;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static FastReader sc = new FastReader();
    static int N,M;
    static int[] A;
    static void input(){
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N+1];
        for(int i=1;i<=N;i++){
            A[i] = sc.nextInt();
        }
    }
    static boolean determination(int size){
        int cnt = 1,sum=0;
        for(int i=1;i<=N;i++){
            if(A[i] + sum <= size){
                sum+=A[i];
            }else{
                // 블루레이 사이즈 초과
                cnt++;
                sum = A[i];
            }
        }
        return cnt<=M;
    }
    static void pro(){
        int ans= 0;
        int L=0,R=999900000;
        for(int i=1;i<=N;i++) L = Math.max(L,A[i]);
        while(L<=R){
            int mid = (L+R)/2;
            if(determination(mid)){
                ans = mid;
                R = mid-1;
            }else{
                L = mid+1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s)throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            if(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }

        Double nextDouble(){
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

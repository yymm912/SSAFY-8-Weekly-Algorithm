package study;
import java.util.*;
import java.io.*;

public class Main {
    static int N, res = Integer.MAX_VALUE;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        
        for(int i=0 ; i<N ; i++){
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<N ; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        // 비트 연산으로 power set 구하기
        for(int i=1 ; i<(1<<N-1) ; i++){
            for(int j=0;j<N;j++){
            	// 비트가 1이면 a팀, 0이면 b팀으로 할당 후 브루트포스
                if((i & (1<<j)) > 0) a.add(j);
                else b.add(j);
            }

            int aScore = getScore(a);
            int bScore = getScore(b);

            res = Math.min(res, Math.abs(aScore-bScore));
            a.clear();  
            b.clear();
        }
        
        System.out.println(res);
    }
    
    static int getScore(List<Integer> list){
        int score=0;
        
        for(int i=0 ; i<list.size() ; i++){
            int cur = list.get(i);
            for(int j=i+1 ; j<list.size() ; j++){
                int next = list.get(j);
                score += arr[cur][next] + arr[next][cur];
            }
        }
        return score;
    }
}
package study;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class BJ_1932_정수삼각형{
    public static int n;
    public static Integer[][] dp;
    public static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        dp = new Integer[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++) dp[n-1][i] = arr[n-1][i];

        System.out.println(dp_val(0,0));

    }

    public static int dp_val(int point1, int point2){
        if(point1 == n-1) return dp[point1][point2];

        if(dp[point1][point2] == null){
            dp[point1][point2] = Math.max(dp_val(point1+1,point2),dp_val(point1+1,point2+1))+ arr[point1][point2];
        }

        return dp[point1][point2];
    }

}
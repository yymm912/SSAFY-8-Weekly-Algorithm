package studygroup.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ1463_1로만들기{
    static int n;
    static int dp[];
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        dp[1] = 0;
        if(n==1) {
            System.out.println(dp[n]);
            return;
        }

        for(int i=2; i<=n; i++) {
            if(i%2 == 0 && i%3 == 0) {
                dp[i] = Math.min(dp[i/2]+1, dp[i/3]+1);
            }
            else if(i%2 == 0) {
                dp[i] =  Math.min(dp[i/2]+1, dp[i-1]+1);
            }
            else if(i%3 == 0){
                dp[i] =  Math.min(dp[i/3]+1, dp[i-1]+1);
            }
            else {
                dp[i] = dp[i-1]+1;
            }
        }
        System.out.println(dp[n]);
    }
}
package study;

import java.io.InputStreamReader;
import java.io.BufferedReader;

class BJ_2156_포도주시식{
	
	public static Integer[] dp;
	public static int[] arr;
	
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new Integer[n+1];
        
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        dp[0] = 0;
        dp[1] = arr[1];
        if(n>=2) dp[2] = arr[1]+arr[2];
        
        System.out.print(dp(n));
    }
    public static int dp(int n) {
    	if(dp[n]!=null) {
    		return dp[n];
    	}
    	
    	dp[n] = Math.max(Math.max(dp(n-2),dp(n-3)+arr[n-1])+arr[n],dp(n-1));
    	
    	return dp[n];
    }
    
}
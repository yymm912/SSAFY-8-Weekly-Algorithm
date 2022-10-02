package week08.문석환;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  static int dp[] = new int[1000001];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 1;
    dp[3] = 1;
    dp[4] = 2;

    int n = Integer.parseInt(br.readLine());
    System.out.println(go(n));
  }
  public static int go(int n){
    if(dp[n] == null){
      if(n%6==0)
          dp[n] = 1+Math.min(dp_value(n/3),Math.min(dp_value(n/2),dp_value(n-1)));
      else if (n % 3 == 0)
          dp[n] = 1+Math.min(dp_value(n/3),dp_value(n-1));
      else if (n % 2 == 0)
          dp[n] = 1+Math.min(dp_value(n/2),dp_value(n-1));
      else
          dp[n] = 1+dp_value(n-1);
    }
    return dp[n];
  }
}

package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo03_부울경_3반_하상재 {
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case<=T; test_case++){
            sb.append("#"+test_case+" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n+1][n+1];
            
            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=n; j++){
                    arr[i][j] = arr[i-1][j] + arr[i][j-1]  +  Integer.parseInt(st.nextToken()) - arr[i-1][j-1];
                }
            }
            int max = 0;
            for(int i=m; i<=n; i++){
                for(int j = m; j<=n; j++){
                    max= Math.max((arr[i][j] - arr[i-m][j] - arr[i][j-m] + arr[i-m][j-m]), max);
                }
            }
            
            sb.append(max+"\n");
        }
        System.out.println(sb);
    }

}

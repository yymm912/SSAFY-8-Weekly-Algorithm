import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ25494_단순한문제 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, ans = 0;
    static int[] arr = new int[3];
    static int[] select = new int[3];

    static void input() throws IOException{
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            ans = 0;
            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());
            dfs(0);
            sb.append(ans).append('\n');
            }
        System.out.println(sb.toString());

    }

    static void dfs(int cnt){
        if(cnt == 3){
            if(((select[0] % select[1]) == (select[1] % select[2])) && (select[1]%select[2] == (select[2] % select[0]))){
                ans++;
            }
            return;
        }
        for(int i = 1; i <= arr[cnt]; i++){
            select[cnt] = i;
            dfs(cnt+1);
        }
    }
    public static void main(String[] args) throws IOException {
        input();
    }
}

import java.io.*;
import java.util.*;

public class SWEA1952_수영장 {

    static int T, answer;
    static int[] price, s, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            price = new int[4];
            for (int i = 0; i < 4; i++) price[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = new int[13];
            for (int i = 1; i <= 12; i++) s[i] = Integer.parseInt(st.nextToken());
        
            answer = price[3];
            dfs(1, 0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int month, int sum){
        if (sum > answer) return;
        if (month > 12){
            answer = Math.min(answer, sum);
            return;
        }

        if (s[month] == 0) dfs(month + 1, sum);
        else {
            dfs(month + 1, sum + s[month] * price[0]);  // 1일
            dfs(month + 1, sum + price[1]);             // 1달
            dfs(month + 3, sum + price[2]);             // 3달
        }
    }
}
